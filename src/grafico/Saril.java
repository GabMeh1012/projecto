package grafico;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;

import java.net.URL;

public class Saril extends JFrame{
    private JButton volverButton;
    private JComboBox FormaRetiro;
    private JTextField CampoCantidad;
    private JTextField CampoRetiro;
    private JPanel PSARIL;
    private JFormattedTextField CampoFecha;
    private JButton EnviarPedido;
    private JPanel JCalendar;
    private JCalendar JCalendar1;
    private JCalendar JCalendar2;
    private JCalendar JCalendar3;

    private JFrame ventanaAnterior;

    public Saril(JFrame ventanaAnterior) {

        this.ventanaAnterior = ventanaAnterior; // Store the reference
        setContentPane(getRootPanel());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Acción del botón Volver
        volverButton.addActionListener(e -> {
            this.ventanaAnterior.setVisible(true); // Make the previous window visible
            dispose(); // Close current window (Saril)
        });



        // ComboBox lógica
        FormaRetiro.addActionListener(e -> {
            String seleccion = (String) FormaRetiro.getSelectedItem();

        });
        setVisible(true); // Make Saril window visible when created


    }

    private void createUIComponents() {
        // Aquí creas manualmente el JCalendar
        JCalendar calendario = new JCalendar();
    }

    public JPanel getRootPanel() {
        FondoSaril fondo = new FondoSaril();
        fondo.setLayout(new BorderLayout());
        fondo.add(PSARIL, BorderLayout.CENTER);
        PSARIL.setOpaque(false); // para que se vea el fondo
        PSARIL.setPreferredSize(new Dimension(800, 600));

        return fondo;
    }


    //CLASE PARA AJUSTAR EL FONDO DEL JPANEL
    class FondoSaril extends JPanel {
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
                    System.out.println("Imagen encontrada");
                }
            }

            if (imagen != null) {
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}