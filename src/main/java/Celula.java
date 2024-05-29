import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Celula extends JButton {
    private int linha;
    private int coluna;
    private boolean temMina;
    private boolean marcada;
    private boolean aberta;

    private static final Icon ICON_MINA = new ImageIcon(Celula.class.getResource("/imgs/mine.png"));
    private static final Icon ICON_BANDEIRA = new ImageIcon(Celula.class.getResource("/imgs/flagged.png"));

    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.temMina = false;
        this.marcada = false;
        this.aberta = false;

        setFont(new Font("Arial", Font.BOLD, 20));
        setBackground(Color.LIGHT_GRAY);

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
        setForeground(obterCor(texto));
    }

    private void marcar() {
        if (!aberta) {
            marcada = !marcada;
            setIcon(marcada ? ICON_BANDEIRA : null);
        }
    }

    private void abrir() {
        if (!marcada) {
            aberta = true;
            if (temMina) {
                setIcon(ICON_MINA);
                setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "Você perdeu!");
                // Aqui você pode adicionar lógica para encerrar o jogo ou reiniciar
            } else {
                setBackground(Color.WHITE);
                setEnabled(false);
            }
        }
    }

    private Color obterCor(String texto) {
        switch (texto) {
            case "1": return Color.BLUE;
            case "2": return Color.GREEN;
            case "3": return Color.RED;
            case "4": return Color.MAGENTA;
            case "5": return Color.ORANGE;
            case "6": return Color.CYAN;
            case "7": return Color.BLACK;
            case "8": return Color.GRAY;
            default: return Color.BLACK;
        }
    }
}
