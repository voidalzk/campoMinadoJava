import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Tabuleiro extends JPanel {
    private static final int TAMANHO_CELULA = 30;
    private final int larguraTabuleiro;
    private final int alturaTabuleiro;
    private final int numeroDeMinas;
    private Celula[][] celulas;

    public Tabuleiro(int larguraTabuleiro, int alturaTabuleiro, int numeroDeMinas) {
        this.larguraTabuleiro = larguraTabuleiro;
        this.alturaTabuleiro = alturaTabuleiro;
        this.numeroDeMinas = numeroDeMinas;
        setLayout(new GridLayout(alturaTabuleiro, larguraTabuleiro));
        inicializarCelulas();
        distribuirMinas();
        calcularMinasAdjacentes();
        setPreferredSize(new Dimension(larguraTabuleiro * TAMANHO_CELULA, alturaTabuleiro * TAMANHO_CELULA));
    }

    private void inicializarCelulas() {
        celulas = new Celula[alturaTabuleiro][larguraTabuleiro];
        for (int posY = 0; posY < alturaTabuleiro; posY++) {
            for (int posX = 0; posX < larguraTabuleiro; posX++) {
                celulas[posY][posX] = new Celula(posX, posY);
                add(celulas[posY][posX]);
            }
        }
    }

    private void distribuirMinas() {
        int minasColocadas = 0;
        Random random = new Random();
        while (minasColocadas < numeroDeMinas) {
            int posX = random.nextInt(larguraTabuleiro);
            int posY = random.nextInt(alturaTabuleiro);
            if (!celulas[posY][posX].temMina()) {
                celulas[posY][posX].setMina(true);
                minasColocadas++;
            }
        }
    }

    private void calcularMinasAdjacentes() {
        for (int posY = 0; posY < alturaTabuleiro; posY++) {
            for (int posX = 0; posX < larguraTabuleiro; posX++) {
                if (!celulas[posY][posX].temMina()) {
                    int contagem = contarMinasAdjacentes(posX, posY);
                    celulas[posY][posX].setMinasAdjacentes(contagem);
                }
            }
        }
    }

    private int contarMinasAdjacentes(int posX, int posY) {
        int contagem = 0;
        for (int deltaY = -1; deltaY <= 1; deltaY++) {
            for (int deltaX = -1; deltaX <= 1; deltaX++) {
                if (deltaX == 0 && deltaY == 0) continue;
                int novoX = posX + deltaX;
                int novoY = posY + deltaY;
                if (novoX >= 0 && novoX < larguraTabuleiro && novoY >= 0 && novoY < alturaTabuleiro && celulas[novoY][novoX].temMina()) {
                    contagem++;
                }
            }
        }
        return contagem;
    }

    private class Celula extends JButton {
        private final int posX, posY;
        private boolean temMina;
        private boolean estaRevelada;
        private boolean estaMarcada;
        private int minasAdjacentes;

        public Celula(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
            this.temMina = false;
            this.estaRevelada = false;
            this.estaMarcada = false;
            this.minasAdjacentes = 0;

            setPreferredSize(new Dimension(TAMANHO_CELULA, TAMANHO_CELULA));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        alternarMarcacao();
                    } else {
                        revelar();
                    }
                }
            });
        }

        public boolean temMina() {
            return temMina;
        }

        public void setMina(boolean temMina) {
            this.temMina = temMina;
        }

        public void setMinasAdjacentes(int minasAdjacentes) {
            this.minasAdjacentes = minasAdjacentes;
        }

        private void alternarMarcacao() {
            if (estaRevelada) return;
            estaMarcada = !estaMarcada;
            setText(estaMarcada ? "F" : "");
        }

        private void revelar() {
            if (estaMarcada || estaRevelada) return;
            estaRevelada = true;
            if (temMina) {
                setText("M");
                setBackground(Color.RED);
                JOptionPane.showMessageDialog(Tabuleiro.this, "Você acionou uma mina! Fim de jogo.");
                System.exit(0);
            } else {
                setText(minasAdjacentes > 0 ? String.valueOf(minasAdjacentes) : "");
                setBackground(Color.LIGHT_GRAY);
                if (minasAdjacentes == 0) {
                    revelarVizinhosVazios(posX, posY);
                }
            }
        }

        private void revelarVizinhosVazios(int x, int y) {
            for (int deltaY = -1; deltaY <= 1; deltaY++) {
                for (int deltaX = -1; deltaX <= 1; deltaX++) {
                    int novoX = x + deltaX;
                    int novoY = y + deltaY;
                    if (novoX >= 0 && novoX < larguraTabuleiro && novoY >= 0 && novoY < alturaTabuleiro) {
                        Celula vizinha = celulas[novoY][novoX];
                        if (!vizinha.estaRevelada) {
                            vizinha.revelar();
                        }
                    }
                }
            }
        }
    }
}
