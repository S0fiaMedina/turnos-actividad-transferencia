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

    /**
     * Crea un panel visual para mostrar un indicador con su título y valor.
     *
     * @param titulo texto descriptivo del indicador
     * @param valor etiqueta que muestra el valor del indicador
     * @return panel configurado con el título y el valor centrados
     */
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


    /**
     * Crea un botón con el texto y los estilos visuales establecidos para la aplicación.
     *
     * @param texto texto que se mostrará en el botón
     * @return botón configurado con los estilos definidos
     */
    public static JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(NARANJA);
        btn.setForeground(BLANCO);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));

        return btn;
    }

    /**
     * Configura los estilos visuales de una tabla, incluyendo la altura de las filas,
     * los colores de selección y el encabezado.
     *
     * @param tabla tabla a la que se aplicarán los estilos
     * @return tabla con los estilos configurados
     */
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

    /**
     * Crea un panel de desplazamiento que contiene la tabla de turnos activos,
     * con un borde titulado.
     *
     * @param tabla tabla que se incluirá dentro del panel de desplazamiento
     * @return panel de desplazamiento configurado con la tabla
     */
    public static JScrollPane crearScrollTabla(JTable tabla) {
        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(NARANJA),
                "Turnos activos"
        ));

        return scroll;
    }

    /**
     * Muestra un mensaje de advertencia mediante un cuadro de diálogo.
     *
     * @param padre componente desde el cual se mostrará el mensaje
     * @param mensaje texto de la advertencia
     */
    public static void mostrarAdvertencia(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(
                padre, mensaje, "Atención", JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje de error mediante un cuadro de diálogo.
     *
     * @param padre componente desde el cual se mostrará el mensaje
     * @param mensaje texto del error
     */
    public static void mostrarError(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(
                padre, mensaje, "Error", JOptionPane.ERROR_MESSAGE
        );
    }

}
