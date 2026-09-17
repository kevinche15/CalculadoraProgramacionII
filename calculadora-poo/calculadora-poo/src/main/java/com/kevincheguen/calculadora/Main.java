package com.kevincheguen.calculadora;

import com.kevincheguen.calculadora.controlador.ControladorCalculadora;
import com.kevincheguen.calculadora.modelo.Calculadora;
import com.kevincheguen.calculadora.vista.VentanaCalculadora;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                UIManager.getCrossPlatformLookAndFeelClassName();
            }
            Calculadora calc = new Calculadora();
            VentanaCalculadora vista = new VentanaCalculadora();
            ControladorCalculadora ctrl = new ControladorCalculadora(calc, vista);
            ctrl.iniciar();
        });
    }
}
