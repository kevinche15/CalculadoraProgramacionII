package com.kevincheguen.calculadora.modelo;

import com.kevincheguen.calculadora.excepciones.OperacionNoSoportadaException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class FabricaOperaciones {

    private static final Map<String, Operacion> REGIS = new LinkedHashMap<>();

    static {
        registrar(new Suma());
        registrar(new Resta());
        registrar(new Multiplicacion());
        registrar(new Division());
    }

    private FabricaOperaciones() {
    }

    private static void registrar(Operacion oper) {
        REGIS.put(oper.getSimbolo(), oper);
    }

    public static Operacion crear(String simb) {
        Operacion oper = REGIS.get(simb);
        if (oper == null) {
            throw new OperacionNoSoportadaException(simb);
        }
        return oper;
    }

    public static Set<String> simbolosSoportados() {
        return Collections.unmodifiableSet(REGIS.keySet());
    }

    public static boolean esOperacion(String simb) {
        return REGIS.containsKey(simb);
    }
}
