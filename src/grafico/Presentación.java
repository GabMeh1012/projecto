package grafico;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class Presentación {


    private JButton inicioButton;
    private JPanel panelPresentacion;

    // Constructor
    public Presentación() {

        // Fondo y Estilo de JPanel
        panelPresentacion.setBackground(null); // Sin color de fondo
        panelPresentacion.setBorder(null);     // Sin bordes
        panelPresentacion.setOpaque(true);     // Quede visible
        panelPresentacion.setSize(new Dimension(1000, 700));    // Tamaño fijo

        // Configuración del botón
        if (inicioButton != null) {
            inicioButton.setContentAreaFilled(true); // Color de fondo
            inicioButton.setFocusPainted(false);      // Elimina el borde al enfocarse
        }
    }

    // Metodo que usará el Main para cambiar a esta vista
    public JPanel getRootPanel() {
        return panelPresentacion;
    }



    // Metodo para que el Main conecte el botón con la acción de ir a Portada
    public void addInicioButtonListener(ActionListener listener) {
        inicioButton.addActionListener(listener);
    }

}

