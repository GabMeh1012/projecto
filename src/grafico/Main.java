
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
            frame.setResizable(true); // permitir ajuste manual de tamaño

            presentacion = new Presentación();
            portada = new Portada();
            inicio = new Inicio();
            cliente = new Cliente();

            frame.setContentPane(presentacion.getRootPanel());
            frame.pack(); // tomar tamaño sugerido por layout
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            presentacion.addInicioButtonListener(e -> {
                frame.setContentPane(portada.getRootPanel());
                frame.setTitle("Portada");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.revalidate();
                frame.repaint();
            });

            portada.addStartButtonListener(e -> {
                frame.setContentPane(inicio.getRootPanel());
                frame.setTitle("Inicio");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.revalidate();
                frame.repaint();
            });

            inicio.addInicioButtonListener(e -> {
                try {
                    String correo = inicio.getCorreo();
                    String contrasena = inicio.getContrasena();

                    if (correo == null || correo.isEmpty())
                        throw new IllegalArgumentException("El campo de correo no puede estar vacío.");
                    if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
                        throw new IllegalArgumentException("El formato del correo no es válido.");
                    if (contrasena == null || contrasena.isEmpty())
                        throw new IllegalArgumentException("La contraseña no puede estar vacía.");
                    if (contrasena.length() < 6)
                        throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");

                    frame.setContentPane(cliente.getRootPanel());
                    frame.setTitle("Cliente");
                    frame.pack();
                    frame.setLocationRelativeTo(null);

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            });

            cliente.addHacerPedidoButtonListener(e -> {
                frame.setVisible(false);
                new Pedido(frame);
            });

            cliente.addVerPedidoButtonListener(e ->
                    JOptionPane.showMessageDialog(frame, "¡Ver Pedido clickeado desde Cliente!"));
        });
    }
}