package org.universidad.view;

import org.universidad.logic.GestorTurnos;
import org.universidad.model.Turno;
import org.universidad.utilities.ViewUtilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaTurnos extends JFrame {

    private final GestorTurnos gestor;
    private final DefaultTableModel modeloTabla;
    private final JLabel lblPendientes = new JLabel("0");
    private final JLabel lblAtendidos = new JLabel("0");

    /**
     * Construye e inicializa la ventana principal del sistema de gestión de turnos.
     * Configura los componentes visuales, el encabezado, la tabla de turnos activos
     * y el menú de opciones.
     *
     * @param gestor gestor encargado de administrar las operaciones de los turnos
     */

    public VentanaTurnos(GestorTurnos gestor) {
        super("Sistema de Gestión de Turnos – Universidad Compensar");
        this.gestor = gestor;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 550);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ViewUtilities.BLANCO);
        setLayout(new BorderLayout(10, 10));

        JPanel encabezado = new JPanel(new GridLayout(1, 2, 10, 0));
        encabezado.setBackground(ViewUtilities.NARANJA);
        encabezado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        encabezado.add(ViewUtilities.crearIndicador("Turnos pendientes", lblPendientes));
        encabezado.add(ViewUtilities.crearIndicador("Turnos atendidos", lblAtendidos));
        add(encabezado, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(
                new Object[]{"Código", "Estudiante", "Motivo", "Operario", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) { return false; }
        };
        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(24);
        tabla.setSelectionBackground(ViewUtilities.NARANJA_CLARO);
        tabla.setSelectionForeground(ViewUtilities.TEXTO);
        tabla.getTableHeader().setBackground(ViewUtilities.NARANJA);
        tabla.getTableHeader().setForeground(ViewUtilities.BLANCO);
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ViewUtilities.NARANJA), "Turnos activos"));
        add(scroll, BorderLayout.CENTER);

        JPanel panelMenu = new JPanel(new GridLayout(6, 1, 5, 5));
        panelMenu.setBackground(ViewUtilities.BLANCO);
        panelMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] opciones = {
                "1. Solicitar turno",
                "2. Asignar operario",
                "3. Atender turno",
                "4. Actualizar listado",
                "5. Ver historial",
                "6. Salir"
        };
        for (int i = 0; i < opciones.length; i++) {
            JButton btn = ViewUtilities.crearBoton(opciones[i]);
            final int opcion = i + 1;
            btn.addActionListener(e -> ejecutarOpcion(opcion));
            panelMenu.add(btn);
        }
        add(panelMenu, BorderLayout.EAST);

        actualizarVista();
    }

    /**
     * Ejecuta la acción correspondiente a la opción seleccionada en el menú principal.
     * Gestiona las excepciones generadas durante la ejecución de las operaciones.
     *
     * @param opcion número de la opción seleccionada por el usuario
     */
    private void ejecutarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 -> solicitarTurno();
                case 2 -> asignarOperario();
                case 3 -> atenderTurno();
                case 4 -> actualizarVista();
                case 5 -> verHistorial();
                case 6 -> {
                    int confirmar = JOptionPane.showConfirmDialog(this,
                            "¿Desea salir del sistema?", "Salir",
                            JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_OPTION) System.exit(0);
                }
                default -> JOptionPane.showMessageDialog(this, "Opción no válida.");
            }
        } catch (IllegalArgumentException ex) {
            ViewUtilities.mostrarAdvertencia(this, ex.getMessage());
        } catch (Exception ex) {
            ViewUtilities.mostrarError(this, ex.getMessage());
        }
    }

    /**
     * Solicita los datos del estudiante y el motivo de la asesoría para registrar
     * un nuevo turno en el sistema. Posteriormente, actualiza la vista y muestra
     * el código asignado al turno.
     */
    private void solicitarTurno() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del estudiante:");
        if (nombre == null) return;
        String motivo = JOptionPane.showInputDialog(this, "Motivo de la asesoría:");
        if (motivo == null) return;

        Turno turno = gestor.solicitarTurno(nombre, motivo);
        actualizarVista();
        JOptionPane.showMessageDialog(this,
                "Turno registrado con código: " + turno.getCodigo(),
                "Turno creado", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Solicita el nombre del operario y asigna el siguiente turno pendiente.
     * Actualiza la vista y muestra la información del turno asignado.
     */
    private void asignarOperario() {
        String operario = JOptionPane.showInputDialog(this, "Nombre del operario:");
        if (operario == null) return;

        Turno turno = gestor.asignarOperario(operario);
        if (turno == null) {
            ViewUtilities.mostrarAdvertencia(this, "No hay turnos pendientes en la cola.");
            return;
        }
        actualizarVista();
        JOptionPane.showMessageDialog(this,
                "Turno " + turno.getCodigo() + " asignado a " + turno.getOperario() + ".");
    }

    /**
     * Solicita el código de un turno para finalizar su atención.
     * Una vez finalizada, actualiza la vista y muestra un mensaje de confirmación.
     *
     * @throws Exception si ocurre un error durante la finalización de la atención
     */
    private void atenderTurno() throws Exception {
        String codigo = JOptionPane.showInputDialog(this, "Código del turno a finalizar (ej. T0001):");
        if (codigo == null) return;

        Turno turno = gestor.finalizarAtencion(codigo);
        actualizarVista();
        JOptionPane.showMessageDialog(this,
                "Turno " + turno.getCodigo() + " atendido y enviado al historial.");
    }

    /**
     * Consulta y muestra el historial de turnos atendidos, incluyendo su código,
     * nombre del estudiante, motivo y operario asignado.
     * Los turnos se presentan en el orden devuelto por el gestor.
     */
    private void verHistorial() {
        StringBuilder sb = new StringBuilder();
        List<Turno> historial = gestor.listarHistorial();
        if (historial.isEmpty()) {
            sb.append("No hay turnos atendidos.");
        } else {
            for (Turno t : historial) {
                sb.append(t.getCodigo())
                        .append(" | ").append(t.getNombreEstudiante())
                        .append(" | ").append(t.getMotivo())
                        .append(" | Operario: ").append(t.getOperario())
                        .append("\n");
            }
        }
        JTextArea area = new JTextArea(sb.toString(), 12, 50);
        area.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(area),
                "Historial (último atendido primero)", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Actualiza la información visual del sistema, recargando la tabla de turnos
     * activos y los indicadores de turnos pendientes y atendidos.
     */
    private void actualizarVista() {
        modeloTabla.setRowCount(0);
        for (Turno t : gestor.listarActivos()) {
            modeloTabla.addRow(new Object[]{
                    t.getCodigo(),
                    t.getNombreEstudiante(),
                    t.getMotivo(),
                    t.getOperario(),
                    t.getEstado().name()
            });
        }
        lblPendientes.setText(String.valueOf(gestor.getPendientes()));
        lblAtendidos.setText(String.valueOf(gestor.getAtendidos()));
    }

  
}