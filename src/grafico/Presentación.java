package grafico;


import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class Presentación {
    private JButton inicioButton;
    private JPanel panelPresentacion;

    public Presentación() {

        panelPresentacion.setBackground(null); // sin color
        panelPresentacion.setBorder(null);     // sin borde
        panelPresentacion.setOpaque(true);     // dejarlo visible
        panelPresentacion.setSize(new Dimension(1000, 700));

        // Asegurar tamaño de pantalla
        //panelPresentacion.setPreferredSize(new Dimension(1920, 1080));

        // Aplicar el borde redondeado con color azul y grosor 3
        if (inicioButton != null) {
            //inicioButton.setBorder(new RoundedBorder(50, new Color(5,77,8), 5));
            inicioButton.setContentAreaFilled(true); // opcional: hace que el fondo no se pinte
            inicioButton.setFocusPainted(false);      // opcional: elimina el borde al enfocarse
        }
    }

    // Método que usará el Main para cambiar a esta vista
    public JPanel getRootPanel() {
        return panelPresentacion;
    }

    // Método para que el Main conecte el botón con la acción de ir a Portada
    public void addInicioButtonListener(ActionListener listener) {
        inicioButton.addActionListener(listener);
    }


    // Clase interna para borde redondeado
    private static class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color borderColor;
        private final int borderThickness;

        public RoundedBorder(int radius, Color borderColor, int borderThickness) {
            this.radius = radius;
            this.borderColor = borderColor;
            this.borderThickness = borderThickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + borderThickness, radius + borderThickness, radius + borderThickness, radius + borderThickness);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(radius + borderThickness, radius + borderThickness, radius + borderThickness, radius + borderThickness);
            return insets;
        }
    }
}

