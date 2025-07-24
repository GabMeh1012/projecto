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
            frame.setSize(1920, 1080); // Tamaño compatible con imagen de portada
            frame.setLocationRelativeTo(null); // Centrar la ventana
            frame.setVisible(true);

            // Acción: Presentación → Portada
            presentacion.addInicioButtonListener(e -> {
                frame.setContentPane(portada.getRootPanel());
                frame.setTitle("Portada");
                frame.revalidate(); // Asegura redibujado correcto
                frame.repaint();
            });

            // Acción: Portada → Inicio
            portada.addStartButtonListener(e -> {
                frame.setContentPane(inicio.getRootPanel());
                frame.setSize(1920, 1080); // Mantener tamaño pantalla completa
                frame.setLocationRelativeTo(null);
                frame.setTitle("Inicio");
                frame.revalidate();
                frame.repaint();
            });

            // Acción: Inicio → Cliente (con validación)
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

            // Acciones Cliente
            cliente.addHacerPedidoButtonListener(e ->
                    JOptionPane.showMessageDialog(frame, "¡Hacer Pedido clickeado desde Cliente!")
            );

            cliente.addVerPedidoButtonListener(e ->
                    JOptionPane.showMessageDialog(frame, "¡Ver Pedido clickeado desde Cliente!")
            );
        });
    }
}