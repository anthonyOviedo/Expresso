# ######################################################################################### COMO correr el proyecto en docker ?


#  install docker on machine 

sudo apt-get update
sudo apt-get install -y ca-certificates curl gnupg lsb-release

sudo install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg

echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt-get update
sudo apt-get install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

sudo groupadd docker   # (if not exists)
sudo usermod -aG docker $USER
newgrp docker

docker --version
docker run hello-world

# me muevo a la carpeta del proyecto
cd ~/proyectos/Expresso

# run the command to generate the docker image
docker build -t Expressor:v1 .

# run the commmand to run such container
docker run Expressor:v1 

# get inside the docker and run 
docker exec -it Expressor bin/bash/



# ######################################################################################### COMO correr el proyecto en local ?

# descargar maven
sudo apt install maven

# me muevo a la carpeta del proyecto
cd ~/proyectos/Expresso

# empaqueta con maven
mvn -q -DskipTests package

# crear directorios para el symlink
mkdir -p /home/USER/.local/lib/

# crea/actualiza el symlink: ~/.local/lib/expressor.jar -> target/expresso-*-runner.jar
ln -sf "$(readlink -f target/expresso-0.1.0-runner.jar)" ~/.local/lib/expressor.jar

# agrega el alias a tu bashrc (cambiar a ~/.zshrc si usas zsh)
echo "alias expressor='java -jar ~/.local/lib/expressor.jar'" >> ~/.bashrc

# recarga la sesión para tener el alias disponible ahora
source ~/.bashrc

# ejecuto el comando
expressor --help



# #########################################################################################  COMO crear un ejecutable para Windows ?
param(
  [string]$AppName   = $env:APP_NAME,     # e.g. "expressor"
  [string]$MainClass = $env:MAIN_CLASS,   # e.g. "com.tony.expresso.Expressor"
  [string]$JarName   = $env:JAR_NAME,     # e.g. "expresso-0.1.0-runner.jar"
  [string]$Version   = "1.0.0"
)

# --- Install WiX (like the Action) ---
choco install wixtoolset -y

# --- Paths ---
$WixBin = Join-Path $env:WIX "bin"
$RepoRoot = $PWD
$TargetDir = Join-Path $RepoRoot "target"
$OutDir = Join-Path $RepoRoot "out"
$ObjDir = Join-Path $RepoRoot "obj"
$DistDir = Join-Path $RepoRoot "dist"

# --- Resolve JAR if not provided ---
if (-not $JarName) {
  $jar = Get-ChildItem -Path $TargetDir -Filter *.jar | Select-Object -First 1
  $JarName = $jar.Name
}

# --- jpackage -> app-image (CLI-only) ---
New-Item -ItemType Directory -Force -Path $OutDir | Out-Null
jpackage `
  --type app-image `
  --name "$AppName" `
  --input "$TargetDir" `
  --main-jar "$JarName" `
  --main-class "$MainClass" `
  --win-console `
  --dest "$OutDir"

# --- WiX harvest ---
$AppImageDir = Join-Path $OutDir $AppName
& "$WixBin\heat.exe" dir "$AppImageDir" `
  -cg AppFiles `
  -dr INSTALLFOLDER `
  -srd `
  -var var.SourceDir `
  -ag `
  -out "$RepoRoot\harvest.wxs"

# --- Build MSI (candle + light) ---
New-Item -ItemType Directory -Force -Path $ObjDir | Out-Null
& "$WixBin\candle.exe" `
  -arch x64 `
  -dSourceDir="$AppImageDir" `
  -out "$ObjDir\" `
  "$RepoRoot\packaging\windows\product.wxs" `
  "$RepoRoot\harvest.wxs"

New-Item -ItemType Directory -Force -Path $DistDir | Out-Null
$MsiPath = Join-Path $DistDir "$AppName-$Version.msi"
& "$WixBin\light.exe" `
  -ext WixUtilExtension `
  -sval `
  -o "$MsiPath" `
  "$ObjDir\product.wixobj" `
  "$ObjDir\harvest.wixobj"

Write-Host "MSI created at: $MsiPath"


