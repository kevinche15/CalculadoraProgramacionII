package com.kevincheguen.calculadora.controlador;

import com.kevincheguen.calculadora.excepciones.DivisionPorCeroException;
import com.kevincheguen.calculadora.modelo.Calculadora;
import com.kevincheguen.calculadora.modelo.FabricaOperaciones;
import com.kevincheguen.calculadora.vista.VentanaCalculadora;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class ControladorCalculadora implements ActionListener {

    private final Calculadora calc;
    private final VentanaCalculadora vista;

    private final StringBuilder entra = new StringBuilder();
    private boolean reini = false;

    public ControladorCalculadora(Calculadora calc, VentanaCalculadora vista) {
        this.calc = calc;
        this.vista = vista;
        this.vista.agregarEscuchador(this);
    }

    public void iniciar() {
        vista.mostrar("0");
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evto) {
        String orden = evto.getActionCommand();
        try {
            procesar(orden);
        } catch (DivisionPorCeroException e) {
            vista.mostrarError(e.getMessage());
            reiniciarTodo();
        } catch (RuntimeException e) {
            vista.mostrarError("Ocurrio un error: " + e.getMessage());
            reiniciarTodo();
        }
    }

    private void procesar(String orden) {
        if (orden.length() == 1 && Character.isDigit(orden.charAt(0))) {
            agregarDigito(orden);
        } else if (".".equals(orden)) {
            agregarPuntoDecimal();
        } else if (FabricaOperaciones.esOperacion(orden)) {
            aplicarOperacion(orden);
        } else if ("=".equals(orden)) {
            resolver();
        } else if ("C".equals(orden)) {
            reiniciarTodo();
        } else if ("CE".equals(orden)) {
            borrarEntrada();
        } else if ("+/-".equals(orden)) {
            cambiarSigno();
        } else if ("%".equals(orden)) {
            aplicarPorcentaje();
        }
    }

    private void agregarDigito(String dig) {
        if (reini) {
            entra.setLength(0);
            reini = false;
        }
        if (entra.toString().equals("0")) {
            entra.setLength(0);
        }
        entra.append(dig);
        vista.mostrar(entra.toString());
    }

    private void agregarPuntoDecimal() {
        if (reini) {
            entra.setLength(0);
            reini = false;
        }
        if (entra.length() == 0) {
            entra.append("0");
        }
        if (entra.indexOf(".") < 0) {
            entra.append(".");
        }
        vista.mostrar(entra.toString());
    }

    private void aplicarOperacion(String simb) {
        confirmarOperando();
        calc.ingresarOperacion(FabricaOperaciones.crear(simb));
        vista.mostrar(formatear(calc.getAcumulador()));
        reini = true;
    }

    private void resolver() {
        confirmarOperando();
        vista.mostrar(formatear(calc.getAcumulador()));
        reini = true;
    }

    private void confirmarOperando() {
        double num = entra.length() == 0
                ? calc.getAcumulador()
                : Double.parseDouble(entra.toString());
        calc.ingresarOperando(num);
        entra.setLength(0);
    }

    private void cambiarSigno() {
        if (entra.length() == 0) {
            return;
        }
        if (entra.charAt(0) == '-') {
            entra.deleteCharAt(0);
        } else {
            entra.insert(0, '-');
        }
        vista.mostrar(entra.toString());
    }

    private void aplicarPorcentaje() {
        if (entra.length() == 0) {
            return;
        }
        double valor = Double.parseDouble(entra.toString()) / 100.0;
        entra.setLength(0);
        entra.append(formatear(valor));
        vista.mostrar(entra.toString());
    }

    private void borrarEntrada() {
        entra.setLength(0);
        reini = false;
        vista.mostrar("0");
    }

    private void reiniciarTodo() {
        calc.limpiar();
        entra.setLength(0);
        reini = false;
        vista.mostrar("0");
    }

    static String formatear(double valor) {
        if (Double.isNaN(valor) || Double.isInfinite(valor)) {
            return "Error";
        }
        if (valor == Math.rint(valor) && Math.abs(valor) < 1e15) {
            return String.valueOf((long) valor);
        }
        String texto = String.format(Locale.US, "%.10f", valor);
        texto = texto.replaceAll("0+$", "");
        if (texto.endsWith(".")) {
            texto = texto.substring(0, texto.length() - 1);
        }
        return texto;
    }
}
