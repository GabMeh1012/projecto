package grafico;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import static java.awt.AWTEventMulticaster.add;

public class MiPedido extends JPanel {

    private JPanel PMiPedido;
    private JPanel TPedido;



    //CLASE PARA AJUSTAR EL FONDO DEL JPANEL
    class FondoPedido extends JPanel {
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
