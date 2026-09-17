package com.kevincheguen.calculadora.modelo;

public class Multiplicacion extends Operacion {

    public static final String SIMB = "*";

    public Multiplicacion() {
        super(SIMB, "Multiplicacion");
    }

    @Override
    public double ejecutar(double numA, double numB) {
        return numA * numB;
    }
}
