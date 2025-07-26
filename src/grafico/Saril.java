package grafico;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;

import java.net.URL;


public class Saril extends JFrame{
    private JButton volverButton;
    private JComboBox FormaRetiro;
    private JTextField CampoCantidad;
    private JPanel PSARIL;
    private JFormattedTextField CampoFecha;
    private JButton EnviarPedido;
    private JPanel JCalendar;

    private MiPedido miPedido; // ← importante

    private JFrame ventanaAnterior;

    public Saril(JFrame ventanaAnterior, MiPedido miPedido) {

        this.ventanaAnterior = ventanaAnterior; // Referencia de ventana anterior
        this.miPedido = miPedido;

        setContentPane(getRootPanel());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Acción del botón Volver
        volverButton.addActionListener(e -> {
            this.ventanaAnterior.setVisible(true); // Make the previous window visible
            dispose(); // Close current window (Saril)
        });

        EnviarPedido.addActionListener(e -> {
            try {
                String cantidadTexto = CampoCantidad.getText().trim();
                String fecha = CampoFecha.getText().trim();
                String forma = (String) FormaRetiro.getSelectedItem();

                // Validar cantidad
                if (cantidadTexto.isEmpty()) {
                    throw new IllegalArgumentException("La cantidad no puede estar vacía.");
                }

                int cantidad = Integer.parseInt(cantidadTexto);
                if (cantidad <= 0 || cantidad >= 51) {
                    throw new IllegalArgumentException("La cantidad debe ser un número positivo y menor que 50.");
                }

                // Validar fecha
                if (fecha.isEmpty()) {
                    throw new IllegalArgumentException("La fecha no puede estar vacía.");
                }
                // Validar formato dd/mm/aaaa usando expresión regular
                if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    throw new IllegalArgumentException("La fecha debe tener el formato dd/mm/aaaa.");
                }
                String[] partesFecha = fecha.split("/");
                int dia = Integer.parseInt(partesFecha[0]);
                int mes = Integer.parseInt(partesFecha[1]);
                int anio = Integer.parseInt(partesFecha[2]);

                if (anio < 2025) {
                    throw new IllegalArgumentException("El año debe ser 2025 o mayor.");
                }

                if (mes < 7 || mes > 12) {
                    throw new IllegalArgumentException("Solo se permiten pedidos de julio (mes 7) a diciembre (mes 12).");
                }

                // Validar días según el mes
                int maxDias;
                switch (mes) {
                    case 2: // febrero
                        maxDias = (anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0)) ? 29 : 28;
                        break;
                    case 4: case 6: case 9: case 11:
                        maxDias = 30;
                        break;
                    default:
                        maxDias = 31;
                }

                if (dia < 1 || dia > maxDias) {
                    throw new IllegalArgumentException("El día debe estar entre 1 y " + maxDias + " para el mes " + mes + ".");
                }

                if (mes == 7 && dia < 25) {
                    throw new IllegalArgumentException("Para el mes de julio, solo se aceptan pedidos a partir del día 25.");
                }


                // Validar forma de retiro
                if (forma == null || forma.trim().isEmpty()) {
                    throw new IllegalArgumentException("Debe seleccionar una forma de retiro.");
                }

                // Si todo está correcto, actualiza el pedido y cambia de ventana
                miPedido.actualizarPedido(String.valueOf(cantidad), fecha, forma);

                ventanaAnterior.setContentPane(miPedido.getRootPanel());
                ventanaAnterior.setTitle("Mi Pedido");
                ventanaAnterior.pack();
                ventanaAnterior.setLocationRelativeTo(null);
                ventanaAnterior.setVisible(true);

                dispose(); // Cierra Saril

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error: " + ex.getMessage(), "Error inesperado", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
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