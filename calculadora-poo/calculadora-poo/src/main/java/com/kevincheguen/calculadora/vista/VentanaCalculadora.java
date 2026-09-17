package com.kevincheguen.calculadora.vista;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class VentanaCalculadora extends JFrame {

    private static final String[] ETIQS = {
            "C", "+/-", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=", "CE"
    };

    private final JTextField panta = new JTextField("0");
    private final Map<String, JButton> botns = new LinkedHashMap<>();

    public VentanaCalculadora() {
        super("Calculadora POO - Java SE");
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panta.setEditable(false);
        panta.setFocusable(false);
        panta.setHorizontalAlignment(SwingConstants.RIGHT);
        panta.setFont(new Font("SansSerif", Font.BOLD, 30));
        panta.setBackground(Color.WHITE);
        panta.setPreferredSize(new Dimension(320, 60));
        panta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(panta, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 4, 6, 6));
        Font fuent = new Font("SansSerif", Font.PLAIN, 20);

        for (String etiq : ETIQS) {
            JButton boton = new JButton(etiq);
            boton.setActionCommand(etiq);
            boton.setFont(fuent);
            boton.setFocusPainted(false);
            botns.put(etiq, boton);
            panel.add(boton);
        }

        add(panel, BorderLayout.CENTER);

        pack();
        setMinimumSize(new Dimension(360, 440));
        setSize(360, 440);
        setLocationRelativeTo(null);
    }

    public void agregarEscuchador(ActionListener oyent) {
        for (JButton boton : botns.values()) {
            boton.addActionListener(oyent);
        }
    }

    public void mostrar(String texto) {
        panta.setText(texto);
    }

    public String getTextoPantalla() {
        return panta.getText();
    }

    public void mostrarError(String msje) {
        JOptionPane.showMessageDialog(this, msje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
