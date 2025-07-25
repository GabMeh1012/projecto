package grafico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Saril extends JFrame{
    private JButton volverButton;
    private JComboBox FormaRetiro;
    private JTextField CampoCantidad;
    private JTextField CampoRetiro;
    private JPanel panelSaril;



    public Saril() {
        setContentPane(panelSaril);
        setTitle("Pedido de Saril");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Agregar opciones al ComboBox
        FormaRetiro.addItem("Retiro local");
        FormaRetiro.addItem("Domicilio");

        // Lógica para actualizar el campo según la opción seleccionada
        FormaRetiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) FormaRetiro.getSelectedItem();
                CampoRetiro.setText(seleccion);
            }
        });

        // Acción del botón Volver
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Solo cierra esta ventana
            }
        });
    }
}