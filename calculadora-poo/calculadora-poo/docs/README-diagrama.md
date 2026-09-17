# Diagrama de clases (Editor DIA)

| Archivo | Contenido |
|---|---|
| `diagrama-de-clases.dia` | Diagrama editable, formato nativo del Editor DIA (XML sin comprimir). |
| `diagrama-de-clases.png` | Imagen exportada del diagrama, vista de implementacion. |

## Verificar el archivo .dia en la maquina virtual

```bash
sudo apt update
sudo apt install dia -y
dia docs/diagrama-de-clases.dia
```

Si abre y muestra las 13 clases con sus relaciones, el archivo es correcto.

## Volver a exportar el PNG desde DIA

Menu *Archivo > Exportar...*, tipo **PNG (Portable Network Graphics)**.

Equivalente por terminal:

```bash
dia -e docs/diagrama-de-clases.png -t png docs/diagrama-de-clases.dia
```

Tambien acepta `-t svg`, `-t eps` y `-t png-libart` (este ultimo suele dar mejor
antialiasing).

## Antes de entregar, revisar en DIA

1. **Sentido de las flechas de herencia.** El triangulo hueco debe apuntar a la
   clase padre (`Operacion`, `RuntimeException`). Si alguna quedo invertida:
   seleccionar la linea y usar *Objetos > Invertir direccion*.
2. **Traslapes.** Si alguna caja queda encima de otra, arrastrarla; las lineas
   se reacomodan solas porque tienen el autorruteo activado.
3. **Paquetes.** Para reforzar la vista de implementacion se pueden agregar
   cajas de paquete (*UML > Package*) alrededor de los grupos `modelo`,
   `vista`, `controlador` y `excepciones`.

## Relaciones representadas

| Origen | Relacion | Destino |
|---|---|---|
| `Suma`, `Resta`, `Multiplicacion`, `Division` | Herencia | `Operacion` (abstracta) |
| `DivisionPorCeroException`, `OperacionNoSoportadaException` | Herencia | `RuntimeException` |
| `Main` | Dependencia (crea) | `VentanaCalculadora`, `ControladorCalculadora`, `Calculadora` |
| `ControladorCalculadora` | Asociacion | `VentanaCalculadora`, `Calculadora` |
| `ControladorCalculadora` | Dependencia | `FabricaOperaciones` |
| `Calculadora` | Asociacion (0..1) | `Operacion` |
| `FabricaOperaciones` | Asociacion (1..*) | `Operacion` |
| `Division` | Dependencia (lanza) | `DivisionPorCeroException` |
| `FabricaOperaciones` | Dependencia (lanza) | `OperacionNoSoportadaException` |
