package com.kevincheguen.calculadora.excepciones;

public class OperacionNoSoportadaException extends RuntimeException {

    public OperacionNoSoportadaException(String simb) {
        super("La operacion '" + simb + "' no esta soportada.");
    }
}
