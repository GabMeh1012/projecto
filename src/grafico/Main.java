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
            inicio.addInicioButtonListener(e -> { // Example: using 'bInicio' from Inicio
                frame.setContentPane(cliente.getRootPanel());
                frame.revalidate();
                frame.repaint();
                frame.setTitle("Cliente"); // Change title for the Cliente screen
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