package grafico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL; // Important for loading resources
import java.util.Objects;


public class Cliente {
    private JPanel PCLIENTE;
    private JButton hacerPedidoB;
    private JButton verPedidoB;
    private JButton hPedidoB;
    private JPanel JPLogo;

    // Constructor
    public Cliente() {
        JPLogo.setLayout(new BorderLayout());

        colocarImagenEscalada();


        JPLogo.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e){
                colocarImagenEscalada();
            }
        });

        setHacerPedidoButtonIcon();
        setVerPedidoButtonIcon();
    }

    // Renamed for clarity to reflect its purpose
    private void setHacerPedidoButtonIcon() {
        ImageIcon icon = null;
        try {
            URL imageUrl = getClass().getResource("/grafico/Picture/hacerPedido1.png");

            if (imageUrl != null) {
                icon = new ImageIcon(imageUrl);
                Image image = icon.getImage();

                int desiredWidth = 1;
                int desiredHeight = 1;

                Image scaleImg = image.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);

                ImageIcon scaleIcon = new ImageIcon(scaleImg);

                if (hacerPedidoB != null) {
                    hacerPedidoB.setIcon(icon);

                    hacerPedidoB.setBorderPainted(false);
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
            URL imageUrl = getClass().getResource("/grafico/Picture/ver2.png");

            if (imageUrl != null) {
                ImageIcon icon = new ImageIcon(imageUrl);
                Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                verPedidoB.setIcon(new ImageIcon(image));

                verPedidoB.setBorderPainted(false);
                verPedidoB.setContentAreaFilled(false);
                verPedidoB.setFocusPainted(false);
            } else {
                System.err.println("Image not found: verPedido.png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setHPedidoButtonIcon() {
        try {
            URL imageUrl = getClass().getResource("/grafico/Picture/hPedido.png");

            if (imageUrl != null) {
                ImageIcon icon = new ImageIcon(imageUrl);
                Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                hPedidoB.setIcon(new ImageIcon(image));

                hPedidoB.setBorderPainted(false);
                hPedidoB.setContentAreaFilled(false);
                hPedidoB.setFocusPainted(false);
            } else {
                System.err.println("Image not found: hPedido.png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // You'll likely need a method to return the root panel for the JFrame/JPanel
    public JPanel getRootPanel() {
        return PCLIENTE;
    }


    private void colocarImagenEscalada() {
        try {
            ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/grafico/Picture/logo.png")));
            int ancho = JPLogo.getWidth();
            int alto = JPLogo.getHeight();

            if (ancho > 0 && alto > 0) {
                Image imagenEscalada = originalIcon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                JLabel imagenLabel = new JLabel(new ImageIcon(imagenEscalada));
                JPLogo.removeAll();  // Limpia contenido previo
                JPLogo.add(imagenLabel, BorderLayout.CENTER);
                JPLogo.revalidate();
                JPLogo.repaint();
            }
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + e.getMessage());
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

}
