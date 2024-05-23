import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Celula extends JButton {
    private int linha;
    private int coluna;
    private boolean temMina;
    private boolean marcada;
    private boolean aberta;

    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.temMina = false;
        this.marcada = false;
        this.aberta = false;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    marcar();
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    abrir();
                }
            }
        });
    }

    public boolean temMina() {
        return temMina;
    }

    public void colocarMina() {
        this.temMina = true;
    }

    public void setTexto(String texto) {
        setText(texto);
    }

    private void marcar() {
        if (!aberta) {
            marcada = !marcada;
            setText(marcada ? "M" : "");
        }
    }

    private void abrir() {
        if (!marcada) {
            aberta = true;
            if (temMina) {
                setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "Você perdeu!");
                // Aqui você pode adicionar lógica para encerrar o jogo ou reiniciar
            } else {
                setBackground(Color.WHITE);
                setEnabled(false);
            }
        }
    }
}
