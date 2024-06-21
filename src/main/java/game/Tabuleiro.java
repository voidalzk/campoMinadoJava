package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Tabuleiro extends JPanel {
    public static final int TAMANHO_CELULA = 50;
    public static int larguraTabuleiro;
    public static int alturaTabuleiro;
    public static Celula[][] celulas;

    private final int numeroDeMinas;

    public Tabuleiro(int larguraTabuleiro, int alturaTabuleiro, int numeroDeMinas) {
        Tabuleiro.larguraTabuleiro = larguraTabuleiro;
        Tabuleiro.alturaTabuleiro = alturaTabuleiro;
        this.numeroDeMinas = numeroDeMinas;
        setLayout(new GridLayout(alturaTabuleiro, larguraTabuleiro));
        inicializarCelulas();
        distribuirMinas();
        calcularMinasAdjacentes();
        setPreferredSize(new Dimension(larguraTabuleiro * TAMANHO_CELULA, alturaTabuleiro * TAMANHO_CELULA));
    }

    private void inicializarCelulas() {
        celulas = new Celula[alturaTabuleiro][larguraTabuleiro];
        for (int y = 0; y < alturaTabuleiro; y++) {
            for (int x = 0; x < larguraTabuleiro; x++) {
                celulas[y][x] = new Celula(x, y);
                celulas[y][x].setFont(new Font("Arial", Font.PLAIN, 20)); // Define a fonte com tamanho 20 para o texto na célula
                add(celulas[y][x]);
            }
        }
    }

    private void distribuirMinas() {
        int minasColocadas = 0;
        Random random = new Random();
        while (minasColocadas < numeroDeMinas) {
            int x = random.nextInt(larguraTabuleiro);
            int y = random.nextInt(alturaTabuleiro);
            if (!celulas[y][x].temMina()) {
                celulas[y][x].setMina(true);
                minasColocadas++;
            }
        }
    }

    private void calcularMinasAdjacentes() {
        for (int y = 0; y < alturaTabuleiro; y++) {
            for (int x = 0; x < larguraTabuleiro; x++) {
                if (!celulas[y][x].temMina()) {
                    int contagem = contarMinasAdjacentes(x, y);
                    celulas[y][x].setMinasAdjacentes(contagem);
                }
            }
        }
    }

    private int contarMinasAdjacentes(int x, int y) {
        int contagem = 0;
        for (int deltaY = -1; deltaY <= 1; deltaY++) {
            for (int deltaX = -1; deltaX <= 1; deltaX++) {
                if (deltaX == 0 && deltaY == 0) continue;
                int newX = x + deltaX;
                int newY = y + deltaY;
                if (newX >= 0 && newX < larguraTabuleiro && newY >= 0 && newY < alturaTabuleiro && celulas[newY][newX].temMina()) {
                    contagem++;
                }
            }
        }
        return contagem;
    }
}
