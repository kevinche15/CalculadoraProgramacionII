package com.kevincheguen.calculadora.excepciones;

public class DivisionPorCeroException extends RuntimeException {

    public DivisionPorCeroException() {
        super("No es posible dividir entre cero.");
    }

    public DivisionPorCeroException(String msje) {
        super(msje);
    }
}
