package grafico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

public class Portada {
    private JButton INICIARButton;
    private JPanel PPORTADA;
    private JPanel jPLogo;

    /*
    public Portada() {

        jPLogo.setLayout(new BorderLayout());

        colocarImagenEscalada();


        jPLogo.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e){
                colocarImagenEscalada();
            }
        });
    }

     */

    private void colocarImagenEscalada() {
        try {
            ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/grafico/Picture/logo.png")));
            int ancho = jPLogo.getWidth();
            int alto = jPLogo.getHeight();

            if (ancho > 0 && alto > 0) {
                Image imagenEscalada = originalIcon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                JLabel imagenLabel = new JLabel(new ImageIcon(imagenEscalada));
                jPLogo.removeAll();  // Limpia contenido previo
                jPLogo.add(imagenLabel, BorderLayout.CENTER);
                jPLogo.revalidate();
                jPLogo.repaint();
            }
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + e.getMessage());
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


