package org.universidad;

import org.universidad.logic.GestorTurnos;
import org.universidad.repository.TurnoRepositorioImpl;
import org.universidad.view.VentanaTurnos;

import javax.swing.*;


public class App 
{
    static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestorTurnos gestor = new GestorTurnos(new TurnoRepositorioImpl());
            new VentanaTurnos(gestor).setVisible(true);
        });
    }}
