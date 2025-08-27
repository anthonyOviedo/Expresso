test

# Expresso — Transpilador a Java 23
Proyecto del curso **EIF400-II-2025 · Paradigmas de Programación (UNA)**  
> Proyecto ágil: sujeto a refinamientos y cambios conforme avance el curso.

> Fuente primaria del enunciado: fileciteturn0file0

---

## 🎯 Propósito
**Expresso** es un *minilenguaje* compacto y expresivo, diseñado para practicar conceptos de programación funcional (tipos algebraicos, funciones y *pattern matching*) y para aprender cómo un **transpilador** convierte ideas de alto nivel en **código Java 23** legible y ejecutable. El objetivo es construir de forma incremental un **tooling CLI** que:
- Transpile código Expresso → **fuentes Java**.
- Compile fuentes Java → **.class**.
- Ejecute el resultado desde el mismo **CLI**.

---

## 🧩 Componentes (MVP → Final)
1. **Parser Expresso** en **ANTLR4** → **AST** propio.  
2. **Minityper** (chequeos semánticos básicos) antes de generar Java.  
3. **Generador de código** (transpilador) Expresso → Java 23+.  
4. **CLI `expressor`** en Java (no *bash* ni *.bat*), con subcomandos:
   - `transpile`: genera `*.java` desde `*.expresso`.
   - `build`: (mock inicial) transpila y compila a `*.class` usando `javax.tools.JavaCompiler`.
   - `run`: (mock inicial) transpila, compila y **ejecuta**.
   
> Nota de empaquetado: investigar **jpackage** y alguna librería de CLI como **picocli** (o similar). fileciteturn0file0

---

## 📦 Requisitos
- **JDK 23+** (desarrollo y *target*).  
- **ANTLR4** (obligatorio para el parser).  
- **Maven o Gradle** (libre elección).  
- **Herramienta de unit testing** (JUnit/Jupiter sugerido).  
- **Git** (repositorio privado del equipo + profesor). **No** se permiten aportes externos.

> Las pruebas/demos se ejecutan **en consola** asumiendo Java 23 instalado. fileciteturn0file0

---

## ▶️ Uso del CLI `expressor`
> El CLI es una aplicación **Java** (no *shell script*). Los siguientes comandos son parte del **Sprint Inicial** como *mocks* funcionales. fileciteturn0file0

```bash
# a) Transpilar
expressor transpile Helloworld.expresso
# -> Genera Helloworld.java (simulación inicial)

# b) Compilar
expressor build Helloworld.expresso
# -> Transpila y compila (Helloworld.class) usando javax.tools.JavaCompiler

# c) Ejecutar
expressor run Helloworld.expresso
# -> Transpila, compila y ejecuta la clase resultante
```
---

## 🧪 Ejemplo mínimo
**Expresso** (idea de tipos algebraicos + recursión):
```expresso
// Números naturales
data nat = { Zero, S(nat) }

fun sum(x:nat, y:nat) =
  match x with
    Zero -> y
    S(z) -> S(sum(z, y))
```
**Java 23+** (posible salida del transpilador):
```java
// Números naturales
sealed interface Nat permits Zero, S {}

record Zero() implements Nat {}
record S(Nat pred) implements Nat {}

static Nat sum(Nat x, Nat y) {
  return switch (x) {
    case Zero z      -> y;
    case S(var pred) -> new S(sum(pred, y));
  };
}
```
> Fragmentos tomados/adaptados del SPEC. fileciteturn0file0

---

## 🗂️ Estructura sugerida del repositorio
```
expresso/
├─ docs/                     # Notas de diseño, SPEC, decisiones
├─ grammar/                  # Gramáticas ANTLR4 (*.g4)
├─ expresso-ast/             # Definiciones del AST propio
├─ expresso-typer/           # Minityper (chequeos semánticos)
├─ expresso-transpiler/      # Generador de código Java 23+
├─ expressor-cli/            # App CLI (Java) con subcomandos
├─ examples/                 # Programas Expresso de ejemplo
├─ tests/                    # Casos de prueba (unit/integration)
└─ build/                    # Artefactos generados (gitignored)
```

---

## 🛠️ Desarrollo
### ANTLR4
- Definir gramática en `grammar/Expresso.g4`.
- Generar *parser/lexer* vía plugin de **Maven**/**Gradle** o tarea dedicada.
- Mapear *parse tree* → **AST** propio (objetos Java inmutables).

### Minityper
- Chequeos mínimos: existencia de símbolos, tipos en *patterns*, exhaustividad básica de `match`, etc.

### Transpilador
- Recorrer el AST y emitir **Java 23** usando:
  - `sealed interfaces`/`records` para **tipos algebraicos**.
  - `switch` con patrones (*pattern matching for switch*).

### CLI
- Subcomandos: `transpile`, `build`, `run` (ver sección **Uso**).
- Empaquetado: `jpackage` para distribuir binarios/instaladores multiplataforma.

---

## 🚀 Build y pruebas
Con **Maven**:
```bash
mvn -q -DskipTests=false clean verify
```
Con **Gradle**:
```bash
./gradlew clean test build
```
- Incluir **tests** para cada etapa (parser, typer, generador y CLI).  
- Las demos serán **en consola** con el entregable subido. fileciteturn0file0

---

## 🗺️ Roadmap (Sprints)
### Sprint **Inicial**
- Esqueleto del **CLI `expressor`** con subcomandos y *mocks* funcionales (`transpile`, `build`, `run`).  
- Repo Git listo y proyecto compilable. fileciteturn0file0

### Sprint **Mediano**
- **Parser** integrado + **Minityper** + **pruebas**. fileciteturn0file0

### Sprint **Final**
- **Generación de código** + integración completa (transpilar/compilar/ejecutar) + **pruebas end‑to‑end**. fileciteturn0file0

---

## 📅 Evaluación y Fechas (tentativas)
- **Inicial** — *5%* — **31/08/2025 12:00 md**  
- **Mediano** — *15%* — **28/09/2025 12:00 md**  
- **Final** — *20%* — **16/11/2025 12:00 md**  
> Sujetas a ajustes por condiciones especiales. Subida al drive y demo en clase. fileciteturn0file0

### Criterios de calificación
- **Función (65%)**: pasa casos de prueba y defensa en demo.  
- **Paradigma (35%)**: aplicación correcta del estilo FP, calidad del código generado (DRY, “Knuth”).  
> El profesor publicará guía de revisión previa a cada demo. fileciteturn0file0

---

## 🔒 Reglas del repositorio y academic honesty
- **VCS**: usar Git/GitHub **privado**; solo equipo y profesor con acceso. Cualquier aporte externo no autorizado se considera **plagio/copia**.  
- **IA**: registrar y **entregar los prompts** usados de forma transparente. Detecciones no reportadas se tratarán como **plagio/copia**. fileciteturn0file0

---

## 🤝 Contribución (interna)
1. Abrir *issue* con descripción y criterios de aceptación.
2. Rama por feature, *PR* con *checks* verdes y evidencias de prueba.
3. Revisión por pares dentro del equipo. **No** se aceptan *PRs* de terceros.


---

## 📧 Contacto
- **Autor del SPEC**: Carlos Loría‑Sáenz — <loriacarlos@gmail.com> (según documento). fileciteturn0file0

---

## 🧾 Licencia
Defina la licencia del **repositorio del equipo** según las políticas del curso (por defecto, privado/educativo).

---

> **Créditos y fuente**: Este README condensa el documento **“SPEC de Expresso: Un minilenguaje muy concentrado (19/08/2025 v0)”**.  
- filecite
- turn0file0

