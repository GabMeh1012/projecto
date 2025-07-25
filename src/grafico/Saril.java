package grafico;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Saril extends JFrame {
    private JButton volverButton;
    private JComboBox FormaRetiro;
    private JTextField CampoCantidad;
    private JPanel PSARIL;
    private JPanel JCalendar;
    private JTextField CampoFecha;
    private JButton EnviarPedido;
    private JDateChooser calendario;
    private Date FechaRetiro;


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

        calendario.getDateEditor().addPropertyChangeListener("date", evt -> {
            FechaRetiro = calendario.getDate();  // guarda la fecha en la variable global
            CampoFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(CampoFecha));
        });

        EnviarPedido.addActionListener(e -> {
            MiPedido miPedidoPanel = new MiPedido();

            JFrame frame = new JFrame("Resumen del Pedido");
            frame.setContentPane(miPedidoPanel.getRootPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Oculta la ventana actual si quieres
            setVisible(false);
        });

        // Código de prueba: se ejecuta al abrir Saril
        MiPedido miPedido = new MiPedido();
        JFrame frame = new JFrame("Mi Pedido");
        frame.setContentPane(miPedido.getRootPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        calendario = new JDateChooser(); // se instancia manualmente
        JPanel JDateChooser = new JPanel(); // si no lo crea automáticamente
        JCalendar.add(calendario);
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
    static class FondoSaril extends JPanel {
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