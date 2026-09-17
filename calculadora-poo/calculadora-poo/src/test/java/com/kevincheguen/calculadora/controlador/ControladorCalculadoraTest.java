package com.kevincheguen.calculadora.controlador;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Formato de resultados en pantalla")
class ControladorCalculadoraTest {

    @ParameterizedTest(name = "{0} se muestra como {1}")
    @CsvSource({
            "5.0, 5",
            "-3.0, -3",
            "2.5, 2.5",
            "0.25, 0.25",
            "0.0, 0"
    })
    void deberiaFormatearElResultado(double valor, String esper) {
        assertEquals(esper, ControladorCalculadora.formatear(valor));
    }
}
