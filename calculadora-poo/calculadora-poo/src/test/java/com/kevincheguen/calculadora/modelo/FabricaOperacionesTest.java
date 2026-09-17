package com.kevincheguen.calculadora.modelo;

import com.kevincheguen.calculadora.excepciones.OperacionNoSoportadaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Fabrica de operaciones")
class FabricaOperacionesTest {

    @ParameterizedTest(name = "El simbolo {0} produce una {1}")
    @CsvSource({
            "+, Suma",
            "-, Resta",
            "*, Multiplicacion",
            "/, Division"
    })
    void deberiaCrearLaOperacionCorrecta(String simb, String nomEs) {
        Operacion oper = FabricaOperaciones.crear(simb);
        assertEquals(nomEs, oper.getNombre());
        assertEquals(simb, oper.getSimbolo());
    }

    @ParameterizedTest(name = "El simbolo {0} no esta soportado")
    @ValueSource(strings = {"^", "%", "raiz", "="})
    void deberiaRechazarSimbolosDesconocidos(String simb) {
        assertThrows(OperacionNoSoportadaException.class,
                () -> FabricaOperaciones.crear(simb));
    }

    @Test
    @DisplayName("Reconoce los cuatro simbolos soportados")
    void deberiaReconocerLosSimbolosSoportados() {
        assertEquals(4, FabricaOperaciones.simbolosSoportados().size());
        assertTrue(FabricaOperaciones.esOperacion("+"));
        assertTrue(FabricaOperaciones.esOperacion("/"));
        assertFalse(FabricaOperaciones.esOperacion("="));
    }
}
