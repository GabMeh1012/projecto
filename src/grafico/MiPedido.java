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
    private JButton BFinalizar;

    private JFrame framePrincipal;
    private Cliente pantallaCliente;

    // Constructor modificado
    public MiPedido(JFrame framePrincipal, Cliente pantallaCliente) {
        this.framePrincipal = framePrincipal;
        this.pantallaCliente = pantallaCliente;

        BVOLVER.addActionListener(e -> volverACliente()); // <- este evento es clave

        BFinalizar.addActionListener(e -> {volverACliente();});
    }
    // Método que devuelve el panel con fondo
    public JPanel getRootPanel() {
        FondoPedido fondo = new FondoPedido();
        fondo.setLayout(new BorderLayout());
        fondo.add(PMiPedido, BorderLayout.CENTER);
        PMiPedido.setOpaque(false); // para que se vea el fondo
        PMiPedido.setPreferredSize(new Dimension(800, 600));

        return fondo;
    }

    // Método para volver a la pantalla del cliente
    private void volverACliente() {
        framePrincipal.setContentPane(pantallaCliente.getRootPanel());
        framePrincipal.setTitle("Cliente");
        framePrincipal.pack();
        framePrincipal.setLocationRelativeTo(null);
        framePrincipal.revalidate();
        framePrincipal.repaint();
    }

    // Método para recibir datos desde Saril
    public void actualizarPedido(String cantidad, String fecha, String formaRetiro) {
        lbCantidad.setText("Cantidad: " + cantidad);
        lbFecha.setText("Fecha: " + fecha);
        lbFormaRetiro.setText("Forma de retiro: " + formaRetiro);
    }

    // CLASE PARA AJUSTAR EL FONDO DEL JPANEL
    class FondoPedido extends JPanel {
        private Image imagen;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagen == null) {
                URL url = getClass().getResource("/grafico/Picture/Pedido.png");

                if (url == null) {
                    System.out.println("Imagen no encontrada");
                } else {
                    imagen = new ImageIcon(url).getImage();
                    System.out.println("Imagen encontrada.");
                }
            }
            if (imagen != null) {
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}