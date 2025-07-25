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

    public Inicio() {

        PINICIO.setBackground(null);
        PINICIO.setOpaque(false);
        PINICIO.setBorder(null);


        if(bInicio != null){
            bInicio.setBorder(new RoundedBorder(50, new Color(5,77,8), 5));
            bInicio.setOpaque(false);
            bInicio.setContentAreaFilled(false);  // opcional: hace que el fondo no se pinte
            bInicio.setFocusPainted(false);   // opcional: elimina el borde al enfocarse
        }

        // Eliminar fondo y aplicar borde redondeado al campo de correo
        if (txtCorreo != null) {
            txtCorreo.setOpaque(false);
            txtCorreo.setBackground(new Color(0, 0, 0, 0));
            txtCorreo.setBorder(new RoundedBorder(25, new Color(5, 77, 8), 5)); // Borde verde oscuro
        }

        // Eliminar fondo y aplicar borde redondeado al campo de contraseña
        if (passContra != null) {
            passContra.setOpaque(false);
            passContra.setBackground(new Color(0, 0, 0, 0));
            passContra.setBorder(new RoundedBorder(25, new Color(5, 77, 8), 5));
        }


    }

    public JPanel getRootPanel() {
        FondoInicio fondo = new FondoInicio();
        fondo.setLayout(new BorderLayout());
        fondo.add(PINICIO, BorderLayout.CENTER);
        return fondo;
    }

    public String getCorreo() {
        return txtCorreo.getText().trim();
    }

    public String getContrasena() {
        return new String(passContra.getPassword()).trim();
    }

    // This method allows the Main class to attach a listener to bInicio
    public void addInicioButtonListener(ActionListener listener) {
        if (bInicio != null) {
            bInicio.addActionListener(listener);
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


    //CLASE PARA AJUSTAR EL FONDO DEL JPANEL
    class FondoInicio extends JPanel {
        private Image imagen;


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagen == null) {
                URL url = getClass().getResource("/grafico/Picture/Inicio.png");

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
}
