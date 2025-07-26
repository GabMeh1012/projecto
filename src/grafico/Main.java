
package grafico;

import grafico.Historial.Historial;

import javax.swing.*;

public class Main {

    //Declaración de los componentes principales -Instancias de las clases
    private static JFrame frame;
    private static Presentacion presentacion;
    private static Portada portada;
    private static Inicio inicio;
    private static Cliente cliente;
    private static MiPedido miPedido;

    public static void main(String[] args) {

        // Utilizado para que el código de la interfaz gráfica se ejecute en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {

            //Creación de la ventana principal
            frame = new JFrame("Granja");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true); // Permite ajustar el tamaño de la ventana


            presentacion = new Presentacion();
            portada = new Portada();
            inicio = new Inicio();
            cliente = new Cliente();
            miPedido = new MiPedido(frame, cliente);


            //Muestra la Presentación
            frame.setContentPane(presentacion.getRootPanel());  //Cambia el contenido a Presentacion
            frame.pack(); // tomar tamaño sugerido por layout
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);


            //Cambia la pantalla Presentacion a Portada
            presentacion.addInicioButtonListener(e -> {
                frame.setContentPane(portada.getRootPanel());
                frame.setTitle("Portada");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.revalidate();
                frame.repaint();
            });


            // Cambia la pantalla Portada a Inicio de Sesión
            portada.addStartButtonListener(e -> {
                frame.setContentPane(inicio.getRootPanel());
                frame.setTitle("Inicio");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.revalidate();
                frame.repaint();
            });


            //Validacion de los campos Correo y Contraseña
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


                    // Cambio de pantalla Inicio a Cliente
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

            // Muestra ventana de Pedidos
            cliente.addHacerPedidoButtonListener(e -> {

                frame.setVisible(false);    //Se oculta la ventana de Cliente
                new Pedido(frame, miPedido);

                frame.setTitle("Pedido");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.revalidate();
                frame.repaint();

                //JFrame independiente por eso no cambia el contentPane, se abre la ventana Pedido

            });

            // Muestra Historial de Pedidos
            cliente.addHPedidoButtonListener(e -> {
                Historial historial = new Historial(frame, cliente);
                frame.setContentPane(historial.getRootPanel());
                frame.setTitle("Historial");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });

            //Mensaje de que se clickeo el boton
            cliente.addVerPedidoButtonListener(e -> {
                frame.setContentPane(miPedido.getRootPanel());
                frame.setTitle("Mi Pedido");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
        });
    }
}