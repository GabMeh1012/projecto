package grafico;

import javax.swing.*;
import java.awt.*;
import java.net.URL; // Important for loading resources


public class Pedido extends JFrame {
    private JButton BSARIL;
    private JButton BGUANDU;
    private JButton BVolver;
    private JButton BCOMBO;
    private JPanel PPEDIDO;

    private JFrame ventanaAnterior;

    public Pedido(JFrame ventanaAnterior) {

        PPEDIDO.setBackground(null);    // Elimina fondo transparente
        PPEDIDO.setOpaque(false);       // No se pinta a sí mismo
        PPEDIDO.setBorder(null);        // Quita borde

        this.ventanaAnterior = ventanaAnterior;

        setContentPane(getRootPanel()); // Usa el panel con fondo
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //BOTON PARA VOLVER A LA VENTANA DE ATRAS
        BVolver.addActionListener(e -> {
            ventanaAnterior.setVisible(true);
            dispose();
        });

        //BOTON PARA COMPRAR SARIL
        BSARIL.addActionListener(e -> {
            dispose(); // Cierra esta ventana
            Saril ventanaSaril = new Saril(this);// Abre Saril
            ventanaSaril.setContentPane(ventanaSaril.getRootPanel());
            ventanaSaril.pack();
            ventanaSaril.setLocationRelativeTo(null);
            ventanaSaril.setVisible(true);
        });

        // BOTÓN GUANDÚ - Mostrar mensaje de fuera de stock
        BGUANDU.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Lo sentimos, Guandú está fuera de stock.", "Producto no disponible", JOptionPane.INFORMATION_MESSAGE);
        });

    // BOTÓN COMBO - Mostrar mensaje de fuera de stock
        BCOMBO.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Lo sentimos, el Combo está fuera de stock.", "Producto no disponible", JOptionPane.INFORMATION_MESSAGE);
        });

        setVisible(true);
    }

    // Obtener el panel visual completo
    public JPanel getRootPanel() {
        FondoPedido fondo = new FondoPedido();  //Dibuja la imagen de fondo
        fondo.setLayout(new BorderLayout());
        fondo.add(PPEDIDO, BorderLayout.CENTER);
        PPEDIDO.setOpaque(false); // No se pinte a sí mismo

        // Asegura que el panel de fondo tenga también el tamaño correcto
        PPEDIDO.setPreferredSize(new Dimension(800, 600));

        return fondo;
    }

    // Clase interna que permite pintar una imagen de fondo
    class FondoPedido extends JPanel {
        private Image imagen;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagen == null) {
                URL url = getClass().getResource("/grafico/Picture/Pedido.png");    // Aquí se carga la imagen

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
}


