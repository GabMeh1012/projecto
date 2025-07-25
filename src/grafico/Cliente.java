package grafico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL; // Important for loading resources
import java.util.Objects;

//PARA QUE LOS BORDERS DE LOS BUTTONS QUEDEN ROUND
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class Cliente {
    private JPanel PCLIENTE;
    private JButton hacerPedidoB;
    private JButton verPedidoB;
    private JButton hPedidoB;

    // Constructor
    public Cliente() {

        PCLIENTE.setBackground(null);
        PCLIENTE.setOpaque(false);
        PCLIENTE.setBorder(null);


        setHacerPedidoButtonIcon();
        setVerPedidoButtonIcon();
        setHPedidoButtonIcon();
    }


    public JPanel getRootPanel() {
        FondoCliente fondo = new FondoCliente();
        fondo.setLayout(new BorderLayout());
        fondo.add(PCLIENTE, BorderLayout.CENTER);
        fondo.setPreferredSize(new Dimension(1920, 1080)); // Para asegurar tamaño
        return fondo;
    }

    // Renamed for clarity to reflect its purpose
    private void setHacerPedidoButtonIcon() {
        ImageIcon icon = null;
        try {
            URL imageUrl = getClass().getResource("/grafico/Picture/hacerPedido.png");

            if (imageUrl != null) {
                icon = new ImageIcon(imageUrl);
                Image image = icon.getImage();

                int desiredWidth = 1;
                int desiredHeight = 1;

                Image scaleImg = image.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);

                ImageIcon scaleIcon = new ImageIcon(scaleImg);

                if (hacerPedidoB != null) {
                    hacerPedidoB.setIcon(icon);

                    hacerPedidoB.setBorder(new RoundedBorder(50, new Color(5,77,8), 5));
                    hacerPedidoB.setContentAreaFilled(false);
                    hacerPedidoB.setFocusPainted(false);
                } else {

                    System.err.println("hacerPedidoB is null. Icon cannot be set.");
                }
            } else {

                System.err.println("Image resource not found: /grafico/Picture/hacerPedido1.png");
            }
        } catch (Exception e) {
            e.printStackTrace();

            System.err.println("Error loading image for hacerPedidoB.");
        }
    }

    private void setVerPedidoButtonIcon() {
        try {
            URL imageUrl = getClass().getResource("/grafico/Picture/verPedido.png");

            if (imageUrl != null) {
                ImageIcon icon = new ImageIcon(imageUrl);
                Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                verPedidoB.setIcon(new ImageIcon(image));

                verPedidoB.setBorder(new RoundedBorder(50, new Color(5,77,8), 5));
                verPedidoB.setContentAreaFilled(false);
                verPedidoB.setFocusPainted(false);
            } else {
                System.err.println("Image not found: ver3.2.png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setHPedidoButtonIcon() {
        try {
            URL imageUrl = getClass().getResource("/grafico/Picture/hist2.2.png");

            if (imageUrl != null) {
                ImageIcon icon = new ImageIcon(imageUrl);
                Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                hPedidoB.setIcon(new ImageIcon(image));

                hPedidoB.setBorder(new RoundedBorder(50, new Color(5,77,8), 5));
                hPedidoB.setContentAreaFilled(false);
                hPedidoB.setFocusPainted(false);
            } else {
                System.err.println("Image not found: hPedido.png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Optional: If you need to add an ActionListener from outside
    public void addHacerPedidoButtonListener(java.awt.event.ActionListener listener) {
        if (hacerPedidoB != null) {
            hacerPedidoB.addActionListener(listener);
        }
    }
    public void addVerPedidoButtonListener(java.awt.event.ActionListener listener) {
        if (verPedidoB != null) {
            verPedidoB.addActionListener(listener);
        }
    }

    public void setHacerPedidoB(java.awt.event.ActionListener listener) {
        if(hacerPedidoB != null) {
            hacerPedidoB.addActionListener(listener);
        }
    }


    class RoundedBorder extends AbstractBorder {
        private int radius;
        private Color borderColor;
        private int borderThickness;

        public RoundedBorder(int radius, Color borderColor, int borderThickness) {
            this.radius = radius;
            this.borderColor = borderColor;
            this.borderThickness = borderThickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(borderColor); // 👉 Color personalizado
            g2.setStroke(new BasicStroke(borderThickness)); // 👉 Grosor personalizado
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
    class FondoCliente extends JPanel {
        private Image imagen;


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagen == null) {
                URL url = getClass().getResource("/grafico/Picture/Cliente.png");

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
