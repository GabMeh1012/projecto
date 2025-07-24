package grafico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL; // Important for loading resources


public class Pedido extends JFrame {
    private JButton BSARIL;
    private JButton BGUANDU;
    private JButton BVolver;
    private JButton BCOMBO;
    private JPanel panelPrincipal;

    public Pedido() {
        setTitle("Seleccione un Producto");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);

        // Título
        JLabel labelTitulo = new JLabel("Seleccione un Producto");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 20));
        labelTitulo.setBounds(200, 20, 300, 30);
        panelPrincipal.add(labelTitulo);

        // Botón volver
        BVolver = new JButton("Volver");
        BVolver.setBounds(50, 20, 80, 30);
        panelPrincipal.add(BVolver);

        // Logo
        JLabel labelLogo = new JLabel();
        ImageIcon iconLogo = new ImageIcon(getClass().getResource("/grafico/Picture/logo.png"));
        labelLogo.setIcon(new ImageIcon(iconLogo.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
        labelLogo.setBounds(230, 60, 150, 150);
        panelPrincipal.add(labelLogo);

        // Imagen y etiqueta SARIL
        JLabel imgSaril = new JLabel();
        ImageIcon iconSaril = new ImageIcon(getClass().getResource("/grafico/Picture/saril.png"));
        imgSaril.setIcon(new ImageIcon(iconSaril.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        imgSaril.setBounds(100, 250, 80, 80);
        panelPrincipal.add(imgSaril);

        JLabel labelSaril = new JLabel("SARIL $ 3.00 lb");
        labelSaril.setBounds(100, 330, 120, 20);
        panelPrincipal.add(labelSaril);

        BSARIL = new JButton("Agregar SARIL");
        BSARIL.setBounds(90, 360, 130, 25);
        panelPrincipal.add(BSARIL);

        // Imagen y etiqueta GUANDÚ
        JLabel imgGuandu = new JLabel();
        ImageIcon iconGuandu = new ImageIcon(getClass().getResource("/grafico/Picture/guandu.png"));
        imgGuandu.setIcon(new ImageIcon(iconGuandu.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        imgGuandu.setBounds(400, 250, 80, 80);
        panelPrincipal.add(imgGuandu);

        JLabel labelGuandu = new JLabel("GUANDÚ $ 2.50 lb");
        labelGuandu.setBounds(400, 330, 120, 20);
        panelPrincipal.add(labelGuandu);

        BGUANDU = new JButton("Agregar GUANDÚ");
        BGUANDU.setBounds(390, 360, 140, 25);
        panelPrincipal.add(BGUANDU);

        // Acción del botón volver
        BVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // o cambiar a la pantalla anterior si existe
            }
        });

        add(panelPrincipal);
        setVisible(true);
    }

    //CLASE PARA AJUSTAR EL FONDO DEL JPANEL
    class FondoCliente extends JPanel {
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