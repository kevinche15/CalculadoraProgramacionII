# Calculadora POO — Java SE

Aplicación de escritorio *stand-alone* que implementa una calculadora con las
cuatro operaciones aritméticas básicas (suma, resta, multiplicación y división),
construida con **Java SE puro** (Swing de la biblioteca estándar), **sin ningún
framework**, aplicando Programación Orientada a Objetos y con **pruebas
unitarias automatizadas** con JUnit 5.

---

## 1. Entorno utilizado

| Componente | Versión |
|---|---|
| Sistema operativo invitado | Lubuntu 25.04 (VirtualBox sobre Windows) |
| JDK | OpenJDK 17 |
| IDE | IntelliJ IDEA Community Edition 2025.2 |
| Gestor de dependencias | Apache Maven |
| Framework de pruebas | JUnit 5 (Jupiter) 5.10.2 |
| Herramienta CASE | Editor DIA |

---

## 2. Arquitectura

Se aplica el patrón **Modelo–Vista–Controlador (MVC)**, de forma que la lógica
aritmética queda completamente aislada de la interfaz gráfica y, por lo tanto,
es comprobable de manera automatizada.

```
com.kevincheguen.calculadora
├── Main.java                      punto de entrada
├── modelo/                        lógica de negocio (sin dependencias de Swing)
│   ├── Operacion.java             clase abstracta
│   ├── Suma.java
│   ├── Resta.java
│   ├── Multiplicacion.java
│   ├── Division.java
│   ├── FabricaOperaciones.java    patrón Factory
│   └── Calculadora.java           motor / acumulador
├── vista/
│   └── VentanaCalculadora.java    JFrame + botones
├── controlador/
│   └── ControladorCalculadora.java  ActionListener que une modelo y vista
└── excepciones/
    ├── DivisionPorCeroException.java
    └── OperacionNoSoportadaException.java
```

### Convenciones de codigo

- El codigo fuente no lleva comentarios de ningun tipo.
- Todos los nombres de variables, atributos y parametros estan en espanol y no
  superan los **5 caracteres**: `acum`, `pend`, `simb`, `nom`, `numA`, `numB`,
  `oper`, `num`, `calc`, `vista`, `entra`, `reini`, `panta`, `botns`, `etiq`,
  `oyent`, `texto`, `msje`, `valor`, `orden`, `evto`, `dig`, `REGIS`, `SIMB`,
  `ETIQS`, `TOLER`, `esper`, `nomEs`, `resul`.
- Los nombres de clases y de metodos si son descriptivos, porque forman la API
  publica y aparecen en el diagrama de clases.

### Pilares de la POO aplicados

- **Abstracción** — `Operacion` define el contrato `ejecutar(double, double)`
  sin decidir cómo se realiza el cálculo.
- **Encapsulamiento** — todos los atributos son `private` y se acceden mediante
  métodos; el estado de `Calculadora` solo se modifica a través de su API.
- **Herencia** — `Suma`, `Resta`, `Multiplicacion` y `Division` extienden
  `Operacion`.
- **Polimorfismo** — `Calculadora` opera sobre referencias de tipo `Operacion`
  y la implementación concreta se resuelve en tiempo de ejecución.

---

## 3. Cómo ejecutar el proyecto

### 3.1 Requisitos en la máquina virtual

```bash
sudo apt update
sudo apt install openjdk-17-jdk maven git dia -y
java -version
mvn -version
```

### 3.2 Abrir en IntelliJ IDEA

1. *File > Open…* y seleccione la **carpeta raíz del proyecto** (donde está
   `pom.xml`). No abra el `pom.xml` como archivo suelto.
2. IntelliJ detecta el proyecto Maven y descarga JUnit automáticamente. Si no
   lo hace, abra el panel *Maven* (lateral derecho) y pulse el botón de
   recargar.
3. *File > Project Structure > Project* → SDK: **17**, Language level: **17**.

### 3.3 Ejecutar la aplicación

Desde IntelliJ: abra `Main.java` y pulse el botón verde de *Run*.

Desde la terminal:

```bash
mvn clean package
java -jar target/calculadora-poo-1.0.0.jar
```

### 3.4 Ejecutar las pruebas unitarias

Desde IntelliJ: clic derecho sobre `src/test/java` → *Run 'All Tests'*.

Desde la terminal:

```bash
mvn test
```

El reporte queda en `target/surefire-reports/`.

---

## 4. Pruebas unitarias automatizadas

| Clase de prueba | Qué cubre |
|---|---|
| `OperacionesAritmeticasTest` | Las cuatro operaciones con valores positivos, negativos, cero y decimales; propiedades (conmutatividad, elementos neutros); división entre cero. |
| `FabricaOperacionesTest` | Que cada símbolo produzca la operación correcta y que los símbolos desconocidos sean rechazados. |
| `CalculadoraTest` | Estado inicial, cálculo directo, encadenamiento de operaciones, propagación de errores y reinicio. |
| `ControladorCalculadoraTest` | Formateo del resultado que se muestra en pantalla. |

Se usan `@ParameterizedTest` con `@CsvSource` para cubrir muchos casos con poco
código, `@Nested` para agrupar por operación y `assertThrows` para los casos de
error.

---

## 5. Diagrama de clases

| Archivo | Descripción |
|---|---|
| `docs/diagrama-de-clases.dia` | Diagrama editable, formato nativo del Editor DIA |
| `docs/diagrama-de-clases.png` | Imagen exportada, vista de implementación |

Incluye las 13 clases del proyecto con su herencia, asociaciones y dependencias.

Las instrucciones para abrirlo, ajustarlo y exportarlo están en
[`docs/README-diagrama.md`](docs/README-diagrama.md).

---

## 6. Uso de la calculadora

| Botón | Función |
|---|---|
| `0`–`9`, `.` | Ingresar el número |
| `+ - * /` | Seleccionar la operación |
| `=` | Resolver |
| `C` | Limpiar todo (reinicia el acumulador) |
| `CE` | Borrar solo el número que se está escribiendo |
| `+/-` | Cambiar el signo |
| `%` | Convertir a porcentaje |

Las operaciones se encadenan: `2 + 3 * 4 =` devuelve `20`, porque cada operador
resuelve lo acumulado antes de registrar la nueva operación.

---

## 7. Autor

Kevin — Proyecto académico de aplicación de escritorio con Java SE.
