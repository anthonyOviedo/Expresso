# Expresso — Transpilador a Java 23
Proyecto del curso **EIF400-II-2025 · Paradigmas de Programación (UNA)**  
> Proyecto ágil: sujeto a refinamientos y cambios conforme avance el curso.
> Fuente primaria del enunciado: 
- https://github.com/anthonyOviedo/Expresso/blob/main/EIF400-II-2025_Expresso_Anexo_I_Inicial.pdf
### Estudiantes 
- Antony Oviedo Alfaro
- Esteban Francisco Sánchez Sánchez
- Josh Gámez Sánchez
---

## Web Version 
https://expresso.tonyspublic.info/
## Instaladores disponibles 
### version 3.1
> https://github.com/anthonyOviedo/Expresso/releases/tag/v3.1
### version 1.1
> https://github.com/anthonyOviedo/Expresso/releases/tag/v1.1
### version Alpha01
> https://github.com/anthonyOviedo/Expresso/releases/tag/v1.0_alpha

## Propósito
**Expresso** es un *minilenguaje* compacto y expresivo, diseñado para practicar conceptos de programación funcional (tipos algebraicos, funciones y *pattern matching*) y para aprender cómo un **transpilador** convierte ideas de alto nivel en **código Java 23** legible y ejecutable. El objetivo es construir de forma incremental un **tooling CLI** que:
- Transpile código Expresso → **fuentes Java**.
- Compile fuentes Java → **.class**.
- Ejecute el resultado desde el mismo **CLI**.

---

## Generar instaladores (.msi y .deb)

### Requisitos previos
- **JDK 23+** con `jpackage` disponible en el `PATH`.
- **Maven**.
- **Windows / MSI**: [WiX Toolset 3.11+](https://wixtoolset.org/) debe estar instalado y en el `PATH`.
- **Linux / DEB**: instala `fakeroot`, `binutils`, `build-essential`, `rpm` (dependencias empleadas por `jpackage`).

### Script (Bash) — Linux, macOS o Git Bash/WSL
```bash
# Generar .deb (Linux)
./scripts/package-installers.sh --deb

# Generar .msi (Windows desde Git Bash/WSL)
./scripts/package-installers.sh --msi
```

### Script PowerShell — Windows 

```powershell
# --- Install Java 24 (Temurin JDK) ---
Invoke-WebRequest -Uri "https://github.com/adoptium/temurin24-binaries/releases/download/jdk-24.0.2%2B12/OpenJDK24U-jdk_x64_windows_hotspot_24.0.2_12.msi" -OutFile "$env:TEMP\temurin24.msi"
Start-Process msiexec.exe -Wait -ArgumentList '/i', "$env:TEMP\temurin24.msi", '/qn', '/norestart'
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-24.0.2.12-hotspot"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

# --- Verify Java ---
java -version

# --- Install Maven ---
Invoke-WebRequest -Uri "https://archive.apache.org/dist/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip" -OutFile "$env:TEMP\maven.zip"
Expand-Archive -Path "$env:TEMP\maven.zip" -DestinationPath "C:\maven" -Force
$env:PATH += ";C:\maven\apache-maven-3.9.9\bin"

# --- Install WiX Toolset (for MSI packaging) ---
Invoke-WebRequest -Uri "https://github.com/wixtoolset/wix3/releases/download/wix3112rtm/wix311-binaries.zip" -OutFile "$env:TEMP\wix311.zip"
Expand-Archive -Path "$env:TEMP\wix311.zip" -DestinationPath "C:\wix311" -Force
$env:PATH += ";C:\wix311"

# --- Verify Maven + Java are ready ---
java -version
mvn -v

# --- Build all modules ---
mvn -B -DskipTests clean package

# --- Package CLI JAR into MSI installer ---
& "$env:JAVA_HOME\bin\jpackage.exe" `
  --type msi `
  --input "expresso-cli\target" `
  --main-jar "expresso-cli-0.1.0.jar" `
  --name "expressor" `
  --app-version "3.1" `
  --main-class "com.diezam04.expresso.adapters.cli.Cli" `
  --vendor "Expresso Team" `
  --win-console `
  --win-shortcut `
  --win-menu `
  --win-menu-group "Expresso" `
  --win-dir-chooser `
  --dest "dist"

# --- Add to PATH (persistent) ---
setx JAVA_HOME "C:\Program Files\Eclipse Adoptium\jdk-24.0.2.12-hotspot"
setx PATH "%JAVA_HOME%\bin;%PATH%;C:\Program Files\expressor"

# --- Done ---
Write-Host "Installer built in: dist\expressor-3.1.msi"
Write-Host "Run after install: expressor run examples\HelloWorld0.expresso"

```

Ambos scripts verificarán que existan las dependencias mínimas (JDK 23+, Maven y, según la plataforma, WiX o fakeroot/binutils/build-essential/rpm). En Windows se utilizará `winget` (requiere consola con privilegios elevados); en Linux se usarán paquetes `apt` si están disponibles. A continuación compilan (`mvn -pl expresso-cli -am -DskipTests package`) y ejecutan `jpackage`. Los instaladores quedan en `out/installers/`.

> Para generar el `.deb` en Windows utiliza WSL o cualquier shell Bash compatible.

### Ejecutar el instalador generado
- **Windows**: doble clic en el `.msi` generado.
- **Linux (Debian/Ubuntu)**: `sudo apt install ./out/installers/expresso_<version>_amd64.deb`

## Uso basico del CLI `expressor`
> El CLI es una aplicación **Java** (no *shell script*). Los siguientes comandos son parte del **Sprint Inicial** como *mocks* funcionales.

```bash
# a) Transpilar
expressor transpile examples/HelloWorld0.expresso
# -> Genera HelloWorld0.java en el directorio indicado

# b) Compilar
expressor build examples/HelloWorld0.expresso
# -> Transpila y compila (HelloWorld0.class) usando javax.tools.JavaCompiler

# c) Ejecutar
expressor run examples/HelloWorld0.expresso
# -> Transpila, compila y ejecuta la clase resultante
```
---
#
