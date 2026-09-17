package com.kevincheguen.calculadora.modelo;

import com.kevincheguen.calculadora.excepciones.DivisionPorCeroException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Operaciones aritmeticas basicas")
class OperacionesAritmeticasTest {

    private static final double TOLER = 1e-9;

    @Nested
    @DisplayName("Suma")
    class SumaTest {

        private final Operacion suma = new Suma();

        @ParameterizedTest(name = "{0} + {1} = {2}")
        @CsvSource({
                "2, 3, 5",
                "0, 0, 0",
                "-5, 3, -2",
                "-4, -6, -10",
                "2.5, 0.25, 2.75",
                "1000000, 2000000, 3000000"
        })
        void deberiaSumarCorrectamente(double numA, double numB, double esper) {
            assertEquals(esper, suma.ejecutar(numA, numB), TOLER);
        }

        @Test
        @DisplayName("La suma es conmutativa")
        void deberiaSerConmutativa() {
            assertEquals(suma.ejecutar(7, 3), suma.ejecutar(3, 7), TOLER);
        }

        @Test
        @DisplayName("Expone su simbolo y su nombre")
        void deberiaExponerSuIdentidad() {
            assertEquals("+", suma.getSimbolo());
            assertEquals("Suma", suma.getNombre());
        }
    }

    @Nested
    @DisplayName("Resta")
    class RestaTest {

        private final Operacion resta = new Resta();

        @ParameterizedTest(name = "{0} - {1} = {2}")
        @CsvSource({
                "5, 3, 2",
                "3, 5, -2",
                "0, 0, 0",
                "-5, -3, -2",
                "2.75, 0.25, 2.5"
        })
        void deberiaRestarCorrectamente(double numA, double numB, double esper) {
            assertEquals(esper, resta.ejecutar(numA, numB), TOLER);
        }

        @Test
        @DisplayName("Restar cero devuelve el mismo numero")
        void deberiaSerNeutroElCero() {
            assertEquals(9.5, resta.ejecutar(9.5, 0), TOLER);
        }
    }

    @Nested
    @DisplayName("Multiplicacion")
    class MultiplicacionTest {

        private final Operacion multi = new Multiplicacion();

        @ParameterizedTest(name = "{0} * {1} = {2}")
        @CsvSource({
                "3, 4, 12",
                "-3, 4, -12",
                "-3, -4, 12",
                "7, 0, 0",
                "2.5, 4, 10",
                "0.1, 0.2, 0.02"
        })
        void deberiaMultiplicarCorrectamente(double numA, double numB, double esper) {
            assertEquals(esper, multi.ejecutar(numA, numB), TOLER);
        }

        @Test
        @DisplayName("Multiplicar por uno devuelve el mismo numero")
        void deberiaSerNeutroElUno() {
            assertEquals(123.45, multi.ejecutar(123.45, 1), TOLER);
        }
    }

    @Nested
    @DisplayName("Division")
    class DivisionTest {

        private final Operacion divi = new Division();

        @ParameterizedTest(name = "{0} / {1} = {2}")
        @CsvSource({
                "10, 2, 5",
                "-10, 2, -5",
                "-10, -2, 5",
                "7, 2, 3.5",
                "1, 4, 0.25",
                "0, 5, 0"
        })
        void deberiaDividirCorrectamente(double numA, double numB, double esper) {
            assertEquals(esper, divi.ejecutar(numA, numB), TOLER);
        }

        @Test
        @DisplayName("Dividir entre cero lanza DivisionPorCeroException")
        void deberiaLanzarExcepcionAlDividirEntreCero() {
            DivisionPorCeroException e = assertThrows(DivisionPorCeroException.class,
                    () -> divi.ejecutar(8, 0));
            assertTrue(e.getMessage().toLowerCase().contains("cero"));
        }

        @Test
        @DisplayName("Cero dividido cero tambien es invalido")
        void deberiaRechazarCeroEntreCero() {
            assertThrows(DivisionPorCeroException.class, () -> divi.ejecutar(0, 0));
        }
    }
}
