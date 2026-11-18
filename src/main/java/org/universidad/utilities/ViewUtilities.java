package org.universidad.utilities;

import javax.swing.*;
import java.awt.*;

public class ViewUtilities {

    public static final Color NARANJA = new Color(255, 122, 0);
    public static final Color NARANJA_CLARO = new Color(255, 237, 213);
    public static final Color BLANCO = Color.WHITE;
    public static final Color TEXTO = new Color(60, 40, 20);

    private ViewUtilities() {
        // Evita instanciar esta clase de utilidades.
    }

    public static JPanel crearIndicador(String titulo, JLabel valor) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(NARANJA);

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setForeground(BLANCO);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 14));

        valor.setForeground(BLANCO);
        valor.setFont(new Font("SansSerif", Font.BOLD, 28));
        valor.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(lblTitulo);
        panel.add(valor);

        return panel;
    }

    public static JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(NARANJA);
        btn.setForeground(BLANCO);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));

        return btn;
    }

    public static JTable configurarTabla(JTable tabla) {
        tabla.setRowHeight(24);
        tabla.setSelectionBackground(NARANJA_CLARO);
        tabla.setSelectionForeground(TEXTO);

        tabla.getTableHeader().setBackground(NARANJA);
        tabla.getTableHeader().setForeground(BLANCO);
        tabla.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 13)
        );

        return tabla;
    }

    public static JScrollPane crearScrollTabla(JTable tabla) {
        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(NARANJA),
                "Turnos activos"
        ));

        return scroll;
    }

    public static void mostrarAdvertencia(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(
                padre, mensaje, "Atención", JOptionPane.WARNING_MESSAGE
        );
    }

    public static void mostrarError(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(
                padre, mensaje, "Error", JOptionPane.ERROR_MESSAGE
        );
    }

}
