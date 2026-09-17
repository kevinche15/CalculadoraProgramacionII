package com.kevincheguen.calculadora.modelo;

import java.util.Objects;

public class Calculadora {

    private double acum;
    private Operacion pend;

    public Calculadora() {
        limpiar();
    }

    public void limpiar() {
        this.acum = 0;
        this.pend = null;
    }

    public double calcular(double numA, double numB, Operacion oper) {
        Objects.requireNonNull(oper, "La operacion no puede ser nula");
        return oper.ejecutar(numA, numB);
    }

    public double calcular(double numA, double numB, String simb) {
        return calcular(numA, numB, FabricaOperaciones.crear(simb));
    }

    public void ingresarOperacion(Operacion oper) {
        this.pend = Objects.requireNonNull(oper, "La operacion no puede ser nula");
    }

    public double ingresarOperando(double num) {
        if (pend != null) {
            acum = pend.ejecutar(acum, num);
            pend = null;
        } else {
            acum = num;
        }
        return acum;
    }

    public double getAcumulador() {
        return acum;
    }

    public boolean hayOperacionPendiente() {
        return pend != null;
    }

    public Operacion getOperacionPendiente() {
        return pend;
    }
}
