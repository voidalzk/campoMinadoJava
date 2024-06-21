/*
NOME: GABRIEL FRANCELINO VOIDALESKI GRR20234966
NOME: BRUNO BRUGNEROTTO DE LARA GRR20230933
 */

package game;

import javax.swing.*;

public class CampoMinado extends JFrame {
    public static final int TAMANHO_FACIL = 9;
    public static final int TAMANHO_MEDIO = 16;
    public static final int LARGURA_DIFICIL = 30;
    public static final int ALTURA_DIFICIL = 16;

    public static final int MINAS_FACIL = 10;
    public static final int MINAS_MEDIO = 40;
    public static final int MINAS_DIFICIL = 99;

    private Tabuleiro tabuleiro;
    private int nivelAtual;

    public CampoMinado() {
        setTitle("Campo Minado");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu jogoMenu = new JMenu("Jogo");
        JMenuItem facilItem = new JMenuItem("Fácil");
        JMenuItem medioItem = new JMenuItem("Médio");
        JMenuItem dificilItem = new JMenuItem("Avançado");

        facilItem.addActionListener(e -> iniciarNovoJogo(TAMANHO_FACIL, TAMANHO_FACIL, MINAS_FACIL));
        medioItem.addActionListener(e -> iniciarNovoJogo(TAMANHO_MEDIO, TAMANHO_MEDIO, MINAS_MEDIO));
        dificilItem.addActionListener(e -> iniciarNovoJogo(LARGURA_DIFICIL, ALTURA_DIFICIL, MINAS_DIFICIL));

        jogoMenu.add(facilItem);
        jogoMenu.add(medioItem);
        jogoMenu.add(dificilItem);
        menuBar.add(jogoMenu);
        setJMenuBar(menuBar);

        iniciarNovoJogo(TAMANHO_FACIL, TAMANHO_FACIL, MINAS_FACIL);
    }

    private void iniciarNovoJogo(int largura, int altura, int minas) {
        if (tabuleiro != null) {
            remove(tabuleiro);
        }
        tabuleiro = new Tabuleiro(largura, altura, minas);
        add(tabuleiro);
        pack();
        setLocationRelativeTo(null);
        validate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CampoMinado jogo = new CampoMinado();
            jogo.setVisible(true);
        });
    }
}
