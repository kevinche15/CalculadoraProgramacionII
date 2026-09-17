package com.kevincheguen.calculadora.modelo;

import com.kevincheguen.calculadora.excepciones.DivisionPorCeroException;

public class Division extends Operacion {

    public static final String SIMB = "/";

    public Division() {
        super(SIMB, "Division");
    }

    @Override
    public double ejecutar(double numA, double numB) {
        if (numB == 0) {
            throw new DivisionPorCeroException();
        }
        return numA / numB;
    }
}
