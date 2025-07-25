package grafico;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;


public class Portada {
    private JButton INICIARButton;
    private JPanel PPORTADA;


    //Constructor
    public Portada() {
        //Configuracion del JPanel
        PPORTADA.setBackground(null);   // Elimina fondo transparente
        PPORTADA.setOpaque(false);      // No se pinta a sí mismo
        PPORTADA.setBorder(null);       // Quita borde

        // Tamaño para el panel
        PPORTADA.setPreferredSize(new Dimension(1500, 700));

        // Personalizar boton
        if(INICIARButton != null) {

            //Usa la clase RoundedBorder
            INICIARButton.setBorder(new RoundedBorder(50, new Color(5,77,8), 5));
            INICIARButton.setOpaque(false);
            INICIARButton.setContentAreaFilled(false);  // Hace que el fondo no se pinte
            INICIARButton.setFocusPainted(false);   // Elimina el borde al enfocarse

        }
    }

    // Obtener el panel visual completo
    public JPanel getRootPanel() {
        FondoPortada fondo = new FondoPortada();    //Dibuja la imagen de fondo
        fondo.setLayout(new BorderLayout());
        fondo.add(PPORTADA, BorderLayout.CENTER);
        return fondo;
    }

    // Se conecte el boton con el Main
    public void addStartButtonListener(ActionListener listener) {
        if (INICIARButton != null) {
            INICIARButton.addActionListener(listener);
        }
    }

    // Clase interna que permite pintar una imagen de fondo
    static class FondoPortada extends JPanel {
        private Image imagen;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagen == null) {
                URL url = getClass().getResource("/grafico/Picture/Portada.png");   // Aquí se carga la imagen

                if (url == null) {
                    System.out.println("Imagen no encontrada");
                } else {
                    imagen = new ImageIcon(url).getImage();
                    System.out.println("Imagen encontrada");
                }
            }

            //Imagen se escala al tamaño actual del panel
            if (imagen != null) {
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }


    // Clase Interna RoundedBorder
    //Esta clase permite definir un borde redondeado personalizado para botones.
    private static class RoundedBorder extends AbstractBorder {

        // Atributos
        private final int radius;   //Curvatura
        private final Color borderColor;    // Color del borde
        private final int borderThickness;  //Grosor del borde

        //Constructor
        public RoundedBorder(int radius, Color borderColor, int borderThickness) {
            this.radius = radius;
            this.borderColor = borderColor;
            this.borderThickness = borderThickness;
        }

        //Metodo
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            //Crear una copia del contesto gráfico Graphics2d para no alterar el original
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setColor(borderColor);   //Color del borde
            g2.setStroke(new BasicStroke(borderThickness)); //Grosor
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);  //Dibuja el borde redondo
            g2.dispose();
        }

        //Metodo que define cuánto espacio ocupa el borde alrededor del campo
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + borderThickness, radius + borderThickness, radius + borderThickness, radius + borderThickness);
        }

        //Garantiza que el borde tenga el mismo espacio en los 4 lados (arriba, abajo, izquierda y derecha).
        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(radius + borderThickness, radius + borderThickness, radius + borderThickness, radius + borderThickness);
            return insets;
        }
    }
}


