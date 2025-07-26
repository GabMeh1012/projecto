package grafico;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MiPedido extends JPanel {

    private JPanel PMiPedido;
    private JLabel lbCantidad;
    private JLabel lbFecha;
    private JLabel lbFormaRetiro;
    private JButton BVOLVER;
    private JLabel JTotal;

    private JFrame framePrincipal;
    private Cliente pantallaCliente;

    private FondoPedido fondoConImagen; // fondo personalizado reutilizable

    // Constructor
    public MiPedido(JFrame framePrincipal, Cliente pantallaCliente) {
        this.framePrincipal = framePrincipal;
        this.pantallaCliente = pantallaCliente;

        // Crear fondo una sola vez
        fondoConImagen = new FondoPedido();
        fondoConImagen.setLayout(new BorderLayout());
        fondoConImagen.add(PMiPedido, BorderLayout.CENTER); // Inserta tu diseño aquí
        PMiPedido.setOpaque(false); // para que se vea la imagen detrás
        fondoConImagen.setPreferredSize(new Dimension(800, 600));

        // Acción del botón volver
        BVOLVER.addActionListener(e -> volverACliente());
    }

    // Devolver el fondo ya listo con los componentes
    public JPanel getRootPanel() {
        return fondoConImagen;
    }

    private void volverACliente() {
        framePrincipal.setContentPane(pantallaCliente.getRootPanel());
        framePrincipal.setTitle("Cliente");
        framePrincipal.pack();
        framePrincipal.setLocationRelativeTo(null);
        framePrincipal.setVisible(true);
    }

    public void actualizarPedido(String cantidad, String fecha, String formaRetiro) {
        lbCantidad.setText("Cantidad: " + cantidad);
        lbFecha.setText("Fecha: " + fecha);
        lbFormaRetiro.setText("Forma de retiro: " + formaRetiro);

        try {
            int cantidadInt = Integer.parseInt(cantidad);
            double total = cantidadInt * 3.00;
            JTotal.setText(String.format("B/. %.2f", total));
        } catch (NumberFormatException e) {
            JTotal.setText("Total a pagar: B/. --");
            System.err.println("Error al calcular el total: cantidad no válida.");
        }
    }

    // Clase para dibujar el fondo
    class FondoPedido extends JPanel {
        private Image imagen;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagen == null) {
                URL url = getClass().getResource("/grafico/Picture/Pedido.png");
                if (url != null) {
                    imagen = new ImageIcon(url).getImage();
                } else {
                    System.out.println("Imagen de fondo no encontrada.");
                }
            }

            if (imagen != null) {
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}