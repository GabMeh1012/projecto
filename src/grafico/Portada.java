package grafico;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;
import java.util.Objects;

public class Portada {
    private JButton INICIARButton;
    private JPanel PPORTADA;


    public Portada() {
        PPORTADA.setBackground(null);
        PPORTADA.setOpaque(false);
        PPORTADA.setBorder(null);

        PPORTADA.setPreferredSize(new Dimension(1500, 700)); // Forzar tamaño completo

        if(INICIARButton != null) {
            INICIARButton.setBorder(new RoundedBorder(50, new Color(5,77,8), 5));
            INICIARButton.setOpaque(false);
            INICIARButton.setContentAreaFilled(false);  // opcional: hace que el fondo no se pinte
            INICIARButton.setFocusPainted(false);   // opcional: elimina el borde al enfocarse

        }
    }


    public JPanel getRootPanel() {
        FondoPortada fondo = new FondoPortada();
        fondo.setLayout(new BorderLayout());
        fondo.add(PPORTADA, BorderLayout.CENTER);
        return fondo;
    }

    // New method to add an ActionListener to the INICIARButton
    public void addStartButtonListener(ActionListener listener) {
        if (INICIARButton != null) {
            INICIARButton.addActionListener(listener);
        }
    }

    class FondoPortada extends JPanel {
        private Image imagen;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagen == null) {
                URL url = getClass().getResource("/grafico/Picture/Portada.png");

                if (url == null) {
                    System.out.println("⚠️ Image not found: /grafico/Picture/Portada.png");
                } else {
                    imagen = new ImageIcon(url).getImage();
                    System.out.println("✅ Image loaded successfully.");
                }
            }

            if (imagen != null) {
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        }
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


