package grafico.Historial;

import grafico.Cliente;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class Historial {


    private JButton volverButton;
    private JPanel PHISTORIAL;

    public Historial(JFrame frame, Cliente cliente) {

        PHISTORIAL.setBackground(null);
        PHISTORIAL.setOpaque(false);
        PHISTORIAL.setBorder(null);

        // Acción del botón Volver
        volverButton.addActionListener(e -> {
            frame.setContentPane(cliente.getRootPanel());
            frame.setTitle("Cliente");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        PHISTORIAL.setPreferredSize(new Dimension(1000, 700));


    }

    public JPanel getRootPanel() {
        FondoHistorial fondo = new FondoHistorial();
        fondo.setLayout(new BorderLayout());
        fondo.add(PHISTORIAL, BorderLayout.CENTER);
        return fondo;
    }




    //CLASE PARA AJUSTAR EL FONDO DEL JPANEL
    class FondoHistorial extends JPanel {
        private Image imagen;


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagen == null) {
                URL url = getClass().getResource("/grafico/Picture/Pedido.png");

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
