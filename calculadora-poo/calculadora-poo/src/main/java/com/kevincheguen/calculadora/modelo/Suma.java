package com.kevincheguen.calculadora.modelo;

public class Suma extends Operacion {

    public static final String SIMB = "+";

    public Suma() {
        super(SIMB, "Suma");
    }

    @Override
    public double ejecutar(double numA, double numB) {
        return numA + numB;
    }
}
