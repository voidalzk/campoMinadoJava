import javax.swing.*;
import java.awt.*;

public class CampoMinado extends JFrame {
    private MinaPainel minaPainel;
    private final int LARGURA = 800;
    private final int ALTURA = 600;

    public CampoMinado() {
        setTitle("Campo Minado");
        setSize(LARGURA, ALTURA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        minaPainel = new MinaPainel(9, 9, 10);
        add(minaPainel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Nível");

        JMenuItem facil = new JMenuItem("Fácil");
        facil.addActionListener(e -> iniciarJogo(9, 9, 10));
        menu.add(facil);

        JMenuItem medio = new JMenuItem("Médio");
        medio.addActionListener(e -> iniciarJogo(16, 16, 40));
        menu.add(medio);

        JMenuItem dificil = new JMenuItem("Difícil");
        dificil.addActionListener(e -> iniciarJogo(30, 16, 99));
        menu.add(dificil);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    private void iniciarJogo(int linhas, int colunas, int minas) {
        remove(minaPainel);
        minaPainel = new MinaPainel(linhas, colunas, minas);
        add(minaPainel, BorderLayout.CENTER);
        revalidate();
    }

    public static void main(String[] args) {
        new CampoMinado();
    }
}
