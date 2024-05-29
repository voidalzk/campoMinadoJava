import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temporizador extends JPanel {
    private int contador = 0;
    private JLabel[] digitosLabels;

    public Temporizador(int numDigitos) {
        setLayout(new FlowLayout()); // Altere para FlowLayout
        digitosLabels = new JLabel[numDigitos];
        for (int i = 0; i < numDigitos; i++) {
            digitosLabels[i] = new JLabel();
            add(digitosLabels[i]);
        }

        Timer temporizador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contador++;
                String contadorStr = String.format("%0" + numDigitos + "d", contador);
                for (int i = 0; i < numDigitos; i++) {
                    digitosLabels[i].setIcon(getIconeImagem(Character.toString(contadorStr.charAt(i))));
                }
            }
        });
        temporizador.start();
    }

    private ImageIcon getIconeImagem(String digito) {
        return new ImageIcon(Temporizador.class.getResource("/imgs/timer/" + digito + ".png"));
    }
}