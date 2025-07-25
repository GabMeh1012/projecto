package grafico;

import javax.swing.*;
import java.awt.*;
import java.net.URL; // Important for loading resources


//PARA QUE LOS BORDERS DE LOS BUTTONS QUEDEN ROUND
import javax.swing.border.AbstractBorder;


public class Cliente {
    private JPanel PCLIENTE;
    private JButton hacerPedidoB;
    private JButton verPedidoB;
    private JButton hPedidoB;

    // Constructor
    public Cliente() {

        //Configuracion del JPanel
        PCLIENTE.setBackground(null);   // Elimina fondo transparente
        PCLIENTE.setOpaque(false);      // No se pinta a sí mismo
        PCLIENTE.setBorder(null);       // Quita borde

        // Tamaño para el panel
        PCLIENTE.setPreferredSize(new Dimension(1000, 700));

        //Se llaman los metodos para cargar la imagen a los botones
        setButtonIcon(hacerPedidoB, "/grafico/Picture/hacerPedido.png", 50, 50);
        setButtonIcon(verPedidoB, "/grafico/Picture/verPedido.png", 50, 50);
        setButtonIcon(hPedidoB, "/grafico/Picture/hist2.2.png", 50, 50);
    }

    //Metodo que devuelve el JPanel completo con imagen de fondo
    public JPanel getRootPanel() {
        FondoCliente fondo = new FondoCliente();
        fondo.setLayout(new BorderLayout());
        fondo.add(PCLIENTE, BorderLayout.CENTER);
        fondo.setPreferredSize(new Dimension(1920, 1080)); // Para asegurar tamaño
        return fondo;
    }

    // Metodos reutilizable para Iconos y Estilo de Bordes
    private void setButtonIcon(JButton button, String imagePath, int width, int height) {
        if (button == null) {
            System.err.println("Botón es null. No se puede aplicar icono.");
            return;
        }

        try {
            URL imageUrl = getClass().getResource(imagePath);

            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                Image image = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(image));
            } else {
                System.err.println("Imagen no encontrada: " + imagePath);
            }

            button.setBorder(new RoundedBorder(50, new Color(5, 77, 8), 5));
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al aplicar imagen al botón: " + imagePath);
        }
    }

    // Métodos públicos para asignar eventos a los botones
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

    public void addHPedidoButtonListener(java.awt.event.ActionListener listener) {
        if (hPedidoB != null) {
            hPedidoB.addActionListener(listener);
        }
    }

    // Clase interna que permite pintar una imagen de fondo
    class FondoCliente extends JPanel {
        private Image imagen;


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagen == null) {
                URL url = getClass().getResource("/grafico/Picture/Cliente.png");   // Aquí se carga la imagen

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
