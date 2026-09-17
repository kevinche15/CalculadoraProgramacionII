package com.kevincheguen.calculadora.modelo;

import java.util.Objects;

public abstract class Operacion {

    private final String simb;
    private final String nom;

    protected Operacion(String simb, String nom) {
        this.simb = Objects.requireNonNull(simb, "El simbolo no puede ser nulo");
        this.nom = Objects.requireNonNull(nom, "El nombre no puede ser nulo");
    }

    public abstract double ejecutar(double numA, double numB);

    public String getSimbolo() {
        return simb;
    }

    public String getNombre() {
        return nom;
    }

    @Override
    public String toString() {
        return nom + " (" + simb + ")";
    }
}
