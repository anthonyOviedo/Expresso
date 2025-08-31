# Expresso — Transpilador a Java 23
Proyecto del curso **EIF400-II-2025 · Paradigmas de Programación (UNA)**  
> Proyecto ágil: sujeto a refinamientos y cambios conforme avance el curso.
> Fuente primaria del enunciado: 
- https://github.com/anthonyOviedo/Expresso/blob/main/EIF400-II-2025_Expresso_Anexo_I_Inicial.pdf
### Estudiantes 
- Antony Oviedo Alfaro
- Esteban Sanchez
- Josh 
---

## Instaladores disponibles 

### version Alpha01
> https://github.com/anthonyOviedo/Expresso/releases/edit/v0.1-alpha

## Propósito
**Expresso** es un *minilenguaje* compacto y expresivo, diseñado para practicar conceptos de programación funcional (tipos algebraicos, funciones y *pattern matching*) y para aprender cómo un **transpilador** convierte ideas de alto nivel en **código Java 23** legible y ejecutable. El objetivo es construir de forma incremental un **tooling CLI** que:
- Transpile código Expresso → **fuentes Java**.
- Compile fuentes Java → **.class**.
- Ejecute el resultado desde el mismo **CLI**.

---

## Requisitos para generar un Installador
- **JDK 23+** (desarrollo y *target*).  
- **Maven**  

# Generar instalador en cmd

```cmd
set "OUT_DIR=%cd%\out"
set "OBJ_DIR=%cd%\obj"
set "DIST_DIR=%cd%\dist"
mkdir "%OUT_DIR%"
mkdir "%OBJ_DIR%"
mkdir "%DIST_DIR%"

jpackage ^
  --type app-image ^
  --name "%APP_NAME%" ^
  --input "target" ^
  --main-jar "%JAR_NAME%" ^
  --main-class "%MAIN_CLASS%" ^
  --win-console ^
  --dest "%OUT_DIR%"

set "SRC=%OUT_DIR%\%APP_NAME%"

"%WIX%\bin\heat.exe" dir "%SRC%" -cg AppFiles -dr INSTALLFOLDER -srd -var var.SourceDir -ag -out harvest.wxs
"%WIX%\bin\candle.exe" -arch x64 -dSourceDir="%SRC%" -out "%OBJ_DIR%\" packaging\windows\product.wxs harvest.wxs
"%WIX%\bin\light.exe" -ext WixUtilExtension -sval -o "%DIST_DIR%\%APP_NAME%-1.0.msi" "%OBJ_DIR%\product.wixobj" "%OBJ_DIR%\harvest.wixobj"
```

---

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
