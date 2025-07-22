package grafico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Inicio {
    private JTextField txtCorreo;
    private JButton bInicio;
    private JPasswordField passContra;
    private JPanel PINICIO;

    public JPanel getRootPanel() {
        FondoInicio fondo = new FondoInicio();
        fondo.setLayout(new BorderLayout());
        fondo.add(PINICIO, BorderLayout.CENTER);
        return fondo;
    }

    // This method allows the Main class to attach a listener to bInicio
    public void addInicioButtonListener(ActionListener listener) {
        if (bInicio != null) {
            bInicio.addActionListener(listener);
        }
    }

    class FondoInicio extends JPanel {
        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(Objects.requireNonNull(getClass().getResource("/grafico/Picture/Inicio.png"))).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

}
