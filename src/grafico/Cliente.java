package grafico;

import javax.swing.*;
import java.awt.*;
import java.net.URL; // Important for loading resources


public class Cliente {
    private JPanel PCLIENTE;
    private JButton hacerPedidoB;
    private JButton verPedidoB;
    private JButton hPedidoB;

    // Constructor
    public Cliente() {

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

    // Renamed for clarity to reflect its purpose
    private void setVerPedidoButtonIcon() {
        ImageIcon icon = null;
        try {

            URL imageUrl = getClass().getResource("/grafico/Picture/ver1.png");

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


    // You'll likely need a method to return the root panel for the JFrame/JPanel
    public JPanel getRootPanel() {
        return PCLIENTE;
    }

    // Optional: If you need to add an ActionListener from outside
    public void addHacerPedidoButtonListener(java.awt.event.ActionListener listener) {
        if (hacerPedidoB != null) {
            hacerPedidoB.addActionListener(listener);
        }
    }
}
