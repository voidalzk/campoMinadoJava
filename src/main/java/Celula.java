
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Celula extends JButton {
    private int linha;
    private int coluna;
    private boolean temMina;
    private boolean marcada;
    private boolean aberta;
    private MinaPainel painel;
    private Temporizador temporizador;
    private Sorriso sorriso;

    private static final Icon ICON_MINA = new ImageIcon(Celula.class.getResource("/imgs/mine.png"));
    private static final Icon ICON_BANDEIRA = new ImageIcon(Celula.class.getResource("/imgs/flagged.png"));
    private static final Icon ICON_COBERTO = new ImageIcon(Celula.class.getResource("/imgs/covered.png"));
    private static final Icon ICON_ABERTO = new ImageIcon(Celula.class.getResource("/imgs/blank.png"));

    public Celula(int linha, int coluna, MinaPainel painel, Temporizador temporizador) {
        this.linha = linha;
        this.coluna = coluna;
        this.temMina = false;
        this.marcada = false;
        this.aberta = false;
        this.painel = painel;
        this.temporizador = temporizador;

        setFont(new Font("Arial", Font.BOLD, 20));
        setBackground(Color.LIGHT_GRAY);
        setIcon(ICON_COBERTO);

        addMouseListener(createMouseAdapter());
    }

    private MouseAdapter createMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    marcar();
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    abrir();
                }
            }
        };
    }

    public boolean temMina() {
        return temMina;
    }

    public void colocarMina() {
        this.temMina = true;
    }

    public void setTexto(String texto) {
        setIcon(obterImagem(texto));
    }

    private void marcar() {
        if (!aberta) {
            marcada = !marcada;
            setIcon(marcada ? ICON_BANDEIRA : null);
        }
    }

    private void derrota() {
        setIcon(ICON_MINA);
        setBackground(Color.RED);
        JOptionPane.showMessageDialog(this, "Você perdeu!");
        temporizador.stop();
        painel.notificarDerrota();
        sorriso.setDeadSmiley();
    }

    private void abrir() {
        if (!marcada) {
            aberta = true;
            if (temMina) {
                derrota();
            } else {
                setBackground(Color.WHITE);
                setEnabled(false);
                setIcon(ICON_ABERTO);
            }
        }
    }

    private Icon obterImagem(String texto) {
        switch (texto) {
            case "1": return new ImageIcon(Celula.class.getResource("/imgs/numbers/one.png"));
            case "2": return new ImageIcon(Celula.class.getResource("/imgs/numbers/two.png"));
            case "3": return new ImageIcon(Celula.class.getResource("/imgs/numbers/three.png"));
            case "4": return new ImageIcon(Celula.class.getResource("/imgs/numbers/four.png"));
            case "5": return new ImageIcon(Celula.class.getResource("/imgs/numbers/five.png"));
            case "6": return new ImageIcon(Celula.class.getResource("/imgs/numbers/six.png"));
            case "7": return new ImageIcon(Celula.class.getResource("/imgs/numbers/seven.png"));
            case "8": return new ImageIcon(Celula.class.getResource("/imgs/numbers/eight.png"));
            default: return null;
        }
    }
}
