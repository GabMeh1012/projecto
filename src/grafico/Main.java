package grafico;

import javax.swing.*;
//Hola Gabyyyyy
public class Main {
    private static JFrame frame;
    private static Portada portada;
    private static Inicio inicio;
    private static Cliente cliente;

    public static void main(String[] args) {
        //AIIIIUUUUDAAAA
        SwingUtilities.invokeLater(() -> {
            //No se como usar esta cosa :(
            frame = new JFrame("Application"); // Give a general title
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null); // Centrar la ventana

            portada = new Portada();
            inicio = new Inicio();
            cliente = new Cliente();

            // Set the initial panel to Portada
            frame.setContentPane(portada.getRootPanel());
            frame.setVisible(true);


            portada.addStartButtonListener(e -> {
                frame.setContentPane(inicio.getRootPanel());
                frame.revalidate(); // Re-layout the components
                frame.repaint();    // Repaint the frame to show the new content
                frame.setTitle("Inicio"); // Change title if desired

            });
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

                    // Si todo está correcto, muestra la pantalla del cliente
                    frame.setContentPane(cliente.getRootPanel());
                    frame.revalidate();
                    frame.repaint();
                    frame.setTitle("Cliente");

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace(); // para depurar
                }
            });

            // 4. Optional: Action listener for hacerPedidoButton in Cliente
            //    This is for handling the click event on the image button once you are on the Cliente screen.
            cliente.addHacerPedidoButtonListener(e -> {
                JOptionPane.showMessageDialog(frame, "¡Hacer Pedido Clicked from Cliente screen!");
                // Add any logic you want to execute when the 'hacerPedido' button is clicked
                // For example, display a confirmation, open a new dialog, etc.
            });

            cliente.addVerPedidoButtonListener(e -> {
                JOptionPane.showMessageDialog(frame, "'¡VerPedido Clicked from Cliente screen!");
            });
        });
    }



    
}