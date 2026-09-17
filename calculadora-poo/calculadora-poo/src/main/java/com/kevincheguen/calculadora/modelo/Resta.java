package com.kevincheguen.calculadora.modelo;

public class Resta extends Operacion {

    public static final String SIMB = "-";

    public Resta() {
        super(SIMB, "Resta");
    }

    @Override
    public double ejecutar(double numA, double numB) {
        return numA - numB;
    }
}
