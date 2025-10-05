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

## Instaladores disponibles 
### version 2.1

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

### Script (PowerShell) — Windows nativo
```powershell
pwsh -File .\scripts\package-installers.ps1 -Msi
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
expressor transpile HelloWorld.Expresso
# -> Genera Helloworld.java (simulación inicial)

# b) Compilar
expressor build HelloWorld.Expresso
# -> Transpila y compila (Helloworld.class) usando javax.tools.JavaCompiler

# c) Ejecutar
expressor run HelloWorld.Expresso
# -> Transpila, compila y ejecuta la clase resultante
```
---
#
