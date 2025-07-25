package grafico;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class Inicio {
    private JTextField txtCorreo;
    private JButton bInicio;
    private JPasswordField passContra;
    private JPanel PINICIO;

    //Constructor
    public Inicio() {

        //Configuracion del JPanel
        PINICIO.setBackground(null);    // Elimina fondo transparente
        PINICIO.setOpaque(false);       // No se pinta a sí mismo
        PINICIO.setBorder(null);        // Quita borde

        // Tamaño para el panel
        PINICIO.setPreferredSize(new Dimension(1280, 720));


        //Personalizar boton
        if(bInicio != null){
            bInicio.setBorder(new RoundedBorder(50, new Color(5,77,8), 5));
            bInicio.setOpaque(false);
            bInicio.setContentAreaFilled(false);  // opcional: hace que el fondo no se pinte
            bInicio.setFocusPainted(false);   // opcional: elimina el borde al enfocarse
        }

        // Elimina fondo y aplica borde redondeado al campo de correo
        if (txtCorreo != null) {
            txtCorreo.setOpaque(false);
            //txtCorreo.setBackground(new Color(0, 0, 0, 0));
            txtCorreo.setBorder(new RoundedBorder(25, new Color(5, 77, 8), 5)); // Borde verde oscuro
        }

        // Elimina fondo y aplica borde redondeado al campo de contraseña
        if (passContra != null) {
            passContra.setOpaque(false);
            //passContra.setBackground(new Color(0, 0, 0, 0));
            passContra.setBorder(new RoundedBorder(25, new Color(5, 77, 8), 5));
        }
    }

    // Obtener el panel visual completo
    public JPanel getRootPanel() {
        FondoInicio fondo = new FondoInicio();  //Dibuja la imagen de fondo
        fondo.setLayout(new BorderLayout());

        // Asegura que el panel de fondo tenga también el tamaño correcto
        fondo.setPreferredSize(new Dimension(1280, 720));

        fondo.add(PINICIO, BorderLayout.CENTER);
        return fondo;
    }

    public String getCorreo() {
        return txtCorreo.getText().trim();
    }

    public String getContrasena() {
        return new String(passContra.getPassword()).trim();
    }

    // Se conecta el boton con el Main
    public void addInicioButtonListener(ActionListener listener) {
        if (bInicio != null) {
            bInicio.addActionListener(listener);
        }
    }

    // Clase interna que permite pintar una imagen de fondo
    class FondoInicio extends JPanel {
        private Image imagen;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagen == null) {
                URL url = getClass().getResource("/grafico/Picture/Inicio.png");    // Aquí se carga la imagen

                if (url == null) {
                    System.out.println("Imagen no encontrada");
                } else {
                    imagen = new ImageIcon(url).getImage();
                    System.out.println("Imagen encontrada");
                }
            }
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
