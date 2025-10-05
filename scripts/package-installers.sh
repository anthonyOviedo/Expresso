#!/usr/bin/env bash
set -euo pipefail

usage() {
  cat <<'USAGE'
Usage: package-installers.sh [--deb] [--msi]

Creates distributable installers for the Expresso CLI using jpackage.
When no flags are provided the script picks a default based on the host OS
(DEB on Linux/macOS, MSI on Windows).

The script can bootstrap common prerequisites (JDK 23+, Maven, WiX/fakeroot)
when running on supported platforms (apt on Linux, winget via PowerShell for
Windows).
USAGE
}

repo_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$repo_dir"

want_deb=false
want_msi=false

while [[ $# -gt 0 ]]; do
  case "$1" in
    --deb) want_deb=true ; shift ;;
    --msi) want_msi=true ; shift ;;
    -h|--help) usage ; exit 0 ;;
    *) echo "Unknown argument: $1" >&2 ; usage ; exit 1 ;;
  esac
done

uname_s="$(uname -s)"
if ! $want_deb && ! $want_msi; then
  case "$uname_s" in
    Linux*) want_deb=true ;;
    Darwin*) want_deb=true ;;
    MINGW*|MSYS*|CYGWIN*|Windows_NT) want_msi=true ;;
    *) echo "Unable to determine default installer type for this OS. Specify --deb and/or --msi." >&2 ; exit 1 ;;
  esac
fi

if $want_msi; then
  case "$uname_s" in
    MINGW*|MSYS*|CYGWIN*|Windows_NT) : ;;
    *) echo "[WARN] MSI packaging is only supported on Windows. Skipping." >&2 ; want_msi=false ;;
  esac
fi

if $want_deb; then
  case "$uname_s" in
    Linux*) : ;;
    Darwin*) echo "[WARN] jpackage cannot produce DEB on macOS. Skipping." >&2 ; want_deb=false ;;
    *) echo "[WARN] DEB packaging requires Linux. Skipping." >&2 ; want_deb=false ;;
  esac
fi

ensure_linux_dependencies() {
  local ran_update=false

  apt_install() {
    local pkg="$1"
    if dpkg -s "$pkg" >/dev/null 2>&1; then
      return 0
    fi
    if ! $ran_update; then
      echo "[INFO] Updating apt package index..."
      sudo apt-get update -y
      ran_update=true
    fi
    echo "[INFO] Installing $pkg via apt..."
    sudo apt-get install -y "$pkg"
  }

  if ! command -v jpackage >/dev/null 2>&1; then
    if ! apt_install openjdk-23-jdk; then
      echo "[WARN] openjdk-23-jdk not available, falling back to openjdk-21-jdk" >&2
      apt_install openjdk-21-jdk
    fi
  fi

  if ! command -v mvn >/dev/null 2>&1; then
    apt_install maven
  fi

  for pkg in fakeroot binutils build-essential rpm; do
    apt_install "$pkg"
  done
}

if $want_deb && command -v apt-get >/dev/null 2>&1; then
  ensure_linux_dependencies
fi

# After bootstrapping, ensure required commands exist
command -v mvn >/dev/null || { echo "Maven (mvn) not found on PATH" >&2; exit 1; }
command -v jpackage >/dev/null || { echo "jpackage (JDK 17+) not found on PATH" >&2; exit 1; }

VERSION="$(mvn -q -DforceStdout help:evaluate -Dexpression=project.version)"
APP_NAME="expresso"
MAIN_CLASS="com.diezam04.expresso.adapters.cli.Cli"
INPUT_DIR="$repo_dir/expresso-cli/target"
DIST_DIR="$repo_dir/out/installers"
mkdir -p "$DIST_DIR"

mvn -pl expresso-cli -am -DskipTests package >/dev/null

MAIN_JAR="expresso-cli-${VERSION}-shaded.jar"
if [[ ! -f "$INPUT_DIR/$MAIN_JAR" ]]; then
  echo "Expected shaded jar not found: $INPUT_DIR/$MAIN_JAR" >&2
  exit 1
fi

LICENSE_ARGS=()
if [[ -f "$repo_dir/LICENSE" ]]; then
  LICENSE_ARGS=(--license-file "$repo_dir/LICENSE")
fi

if $want_deb; then
  echo "\n[INFO] Generating DEB installer..."
  jpackage \
    --type deb \
    --name "$APP_NAME" \
    --app-version "$VERSION" \
    --vendor "Expresso Team" \
    --input "$INPUT_DIR" \
    --main-jar "$MAIN_JAR" \
    --main-class "$MAIN_CLASS" \
    --dest "$DIST_DIR" \
    --linux-shortcut \
    --linux-menu-group "Development" \
    --description "Expresso CLI transpiler" \
    "${LICENSE_ARGS[@]}"
fi

if $want_msi; then
  echo "\n[INFO] Generating MSI installer..."
  jpackage \
    --type msi \
    --name "$APP_NAME" \
    --app-version "$VERSION" \
    --vendor "Expresso Team" \
    --input "$INPUT_DIR" \
    --main-jar "$MAIN_JAR" \
    --main-class "$MAIN_CLASS" \
    --dest "$DIST_DIR" \
    --win-console \
    --win-dir-chooser \
    --win-menu \
    --win-menu-group "Expresso" \
    "${LICENSE_ARGS[@]}"
fi

echo "\n[INFO] Installers created under: $DIST_DIR"
