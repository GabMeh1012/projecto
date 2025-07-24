package grafico;

import javax.swing.*;

public class Main {
    private static JFrame frame;
    private static Presentación presentacion;
    private static Portada portada;
    private static Inicio inicio;
    private static Cliente cliente;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Instanciar todas las pantallas
            presentacion = new Presentación();
            portada = new Portada();
            inicio = new Inicio();
            cliente = new Cliente();

            // Mostrar primero la pantalla de Presentación
            frame.setContentPane(presentacion.getRootPanel());
            frame.pack(); // ⬅ Ajusta automáticamente al contenido
            frame.setLocationRelativeTo(null); // Centrar la ventana
            frame.setVisible(true);

            // Acción para ir de Presentación → Portada
            presentacion.addInicioButtonListener(e -> {
                frame.setContentPane(portada.getRootPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setTitle("Portada");
            });

            // Acción para ir de Portada → Inicio
            portada.addStartButtonListener(e -> {
                frame.setContentPane(inicio.getRootPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setTitle("Inicio");
            });

            // Acción para ir de Inicio → Cliente (con validación)
            inicio.addInicioButtonListener(e -> {
                try {
                    String correo = inicio.getCorreo();
                    String contrasena = inicio.getContrasena();

                    if (correo == null || correo.isEmpty()) {
                        throw new IllegalArgumentException("El campo de correo no puede estar vacío.");
                    }

                    if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                        throw new IllegalArgumentException("El formato del correo no es válido.");
                    }

                    if (contrasena == null || contrasena.isEmpty()) {
                        throw new IllegalArgumentException("La contraseña no puede estar vacía.");
                    }

                    if (contrasena.length() < 6) {
                        throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
                    }

                    frame.setContentPane(cliente.getRootPanel());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setTitle("Cliente");

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            });

            // Acciones de botones en Cliente
            cliente.addHacerPedidoButtonListener(e -> {
                JOptionPane.showMessageDialog(frame, "¡Hacer Pedido clickeado desde Cliente!");
            });

            cliente.addVerPedidoButtonListener(e -> {
                JOptionPane.showMessageDialog(frame, "¡Ver Pedido clickeado desde Cliente!");
            });
        });
    }
}