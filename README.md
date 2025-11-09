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

## Generar ejecutable `.exe` (Windows)

El pipeline de CI usa [`jpackage`](https://docs.oracle.com/en/java/javase/23/jpackage/) para producir un ejecutable de Windows.

### Requisitos
- **Windows 10/11** con PowerShell.
- [Temurin JDK 24](https://adoptium.net/) (incluye `jpackage`).
- [Apache Maven 3.9+](https://maven.apache.org/).

Verifica que `java`, `mvn` y `jpackage` estén en el `PATH` antes de continuar.



### PASOS PARA USO DE EXPRESSO (CMD o PowerShell)

#### Nota: Ubicarse en la Raíz del Proyecto

### CMD
```CMD

# 1. Restaurar dependencias y compilar los módulos

#   1.1. Compilar
mvn -B -DskipTests clean compile

#   1.2. Empaquetar
mvn -B -DskipTests clean package


# 2. Generar Ejecutable de Expresso en carpeta Dist

#    2.1. Generar Runtime  
"%JAVA_HOME%\bin\jlink.exe" ^
  --module-path "%JAVA_HOME%\jmods" ^
  --add-modules java.base,java.logging,java.scripting,java.desktop,jdk.compiler ^
  --output "runtime"

#    2.2. Generar Ejecutable en carpeta Dist
"%JAVA_HOME%\bin\jpackage.exe" ^
  --type app-image ^
  --name expressor ^
  --input "expresso-cli\target" ^
  --main-jar "expresso-cli-0.1.0.jar" ^
  --main-class "com.diezam04.expresso.adapters.cli.Cli" ^
  --app-version 3.1 ^
  --runtime-image "runtime" ^
  --win-console ^
  --dest "dist"




```
### PowerShell

```powershell

# 1. Restaurar dependencias y compilar los módulos

#   1.1. Compilar
mvn -B -DskipTests clean compile

#   1.2. Empaquetar
mvn -B -DskipTests clean package



# 2. Generar Ejecutable de Expresso

#    2.1. Generar Runtime  
& "$env:JAVA_HOME\bin\jlink.exe" `
  --module-path "$env:JAVA_HOME\jmods" `
  --add-modules java.base,java.logging,java.scripting,java.desktop,jdk.compiler `
  --output ".\runtime"

#    2.2. Generar Ejecutable en carpeta Dist
& "$env:JAVA_HOME\bin\jpackage.exe" `
  --type app-image `
  --name "expressor" `
  --input "expresso-cli\target" `
  --main-jar "expresso-cli-0.1.0.jar" `
  --main-class "com.diezam04.expresso.adapters.cli.Cli" `
  --app-version "3.1" `
  --runtime-image ".\runtime" `
  --win-console `
  --dest "dist"


```



El ejecutable generado puede ser invocado con ".\dist\expressor\expressor" desde la raíz del proyecto.


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