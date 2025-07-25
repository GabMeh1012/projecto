package grafico.Historial;
import grafico.Cliente;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class Historial {

    private JButton volverButton;
    private JPanel PHISTORIAL;

    //Constructor
    public Historial(JFrame frame, Cliente cliente) {

        //Configuracion del JPanel
        PHISTORIAL.setBackground(null); // Elimina fondo transparente
        PHISTORIAL.setOpaque(false);    // No se pinta a sí mismo
        PHISTORIAL.setBorder(null);     // Quita borde

        // Acción del botón Volver
        volverButton.addActionListener(e -> {
            frame.setContentPane(cliente.getRootPanel());
            frame.setTitle("Cliente");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        // Tamaño para el panel
        PHISTORIAL.setPreferredSize(new Dimension(1000, 750));
    }

    // Obtener el panel visual completo
    public JPanel getRootPanel() {
        FondoHistorial fondo = new FondoHistorial();    //Dibuja la imagen de fondo
        fondo.setLayout(new BorderLayout());

        // Asegura que el panel de fondo tenga también el tamaño correcto
        fondo.setPreferredSize(new Dimension(1280, 720));

        fondo.add(PHISTORIAL, BorderLayout.CENTER);
        return fondo;
    }

    // Clase interna que permite pintar una imagen de fondo
    static class FondoHistorial extends JPanel {
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
                    System.out.println("Imagen encontrada.");
                }
            }
            if (imagen != null) {
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

}
