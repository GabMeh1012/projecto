package grafico;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Presentación {
    private JButton inicioButton;
    private JPanel panelPresentacion;

    // Método que usará el Main para cambiar a esta vista
    public JPanel getRootPanel() {
        return panelPresentacion;
    }

    // Método para que el Main conecte el botón con la acción de ir a Portada
    public void addInicioButtonListener(ActionListener listener) {
        inicioButton.addActionListener(listener);
    }
}