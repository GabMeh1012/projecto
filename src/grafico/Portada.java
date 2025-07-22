package grafico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Portada {
    private JButton INICIARButton;
    private JPanel PPORTADA;

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
        public void paint(Graphics g) {
            imagen = new ImageIcon(Objects.requireNonNull(getClass().getResource("/grafico/Picture/Portada.png"))).getImage();

            if (imagen == null) {
                System.err.println("Error: Image not loaded for Portada.png!");
                return; // Don't try to draw a null image
            }

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

}


