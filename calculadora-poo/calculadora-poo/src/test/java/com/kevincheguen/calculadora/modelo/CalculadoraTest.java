package com.kevincheguen.calculadora.modelo;

import com.kevincheguen.calculadora.excepciones.DivisionPorCeroException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Motor de la calculadora")
class CalculadoraTest {

    private static final double TOLER = 1e-9;

    private Calculadora calc;

    @BeforeEach
    void prepararEscenario() {
        calc = new Calculadora();
    }

    @Test
    @DisplayName("Inicia en cero y sin operacion pendiente")
    void deberiaIniciarLimpia() {
        assertEquals(0, calc.getAcumulador(), TOLER);
        assertFalse(calc.hayOperacionPendiente());
    }

    @Test
    @DisplayName("Calculo directo usando el simbolo")
    void deberiaCalcularPorSimbolo() {
        assertEquals(9, calc.calcular(4, 5, "+"), TOLER);
        assertEquals(20, calc.calcular(4, 5, "*"), TOLER);
    }

    @Test
    @DisplayName("Calculo directo usando una instancia de Operacion")
    void deberiaCalcularPorInstancia() {
        Operacion oper = new Resta();
        assertEquals(-1, calc.calcular(4, 5, oper), TOLER);
    }

    @Test
    @DisplayName("Secuencia 2 + 3 = 5")
    void deberiaResolverUnaOperacionSimple() {
        calc.ingresarOperando(2);
        calc.ingresarOperacion(new Suma());
        assertTrue(calc.hayOperacionPendiente());

        double resul = calc.ingresarOperando(3);

        assertEquals(5, resul, TOLER);
        assertFalse(calc.hayOperacionPendiente());
    }

    @Test
    @DisplayName("Secuencia encadenada 2 + 3 * 4 = 20")
    void deberiaEncadenarOperaciones() {
        calc.ingresarOperando(2);
        calc.ingresarOperacion(new Suma());
        calc.ingresarOperando(3);
        calc.ingresarOperacion(new Multiplicacion());

        assertEquals(20, calc.ingresarOperando(4), TOLER);
    }

    @Test
    @DisplayName("La division entre cero propaga la excepcion")
    void deberiaPropagarLaDivisionPorCero() {
        calc.ingresarOperando(8);
        calc.ingresarOperacion(new Division());

        assertThrows(DivisionPorCeroException.class, () -> calc.ingresarOperando(0));
    }

    @Test
    @DisplayName("limpiar restablece el estado")
    void deberiaLimpiarElEstado() {
        calc.ingresarOperando(15);
        calc.ingresarOperacion(new Resta());

        calc.limpiar();

        assertEquals(0, calc.getAcumulador(), TOLER);
        assertFalse(calc.hayOperacionPendiente());
    }

    @Test
    @DisplayName("Rechaza una operacion nula")
    void deberiaRechazarOperacionNula() {
        assertThrows(NullPointerException.class, () -> calc.ingresarOperacion(null));
    }
}
