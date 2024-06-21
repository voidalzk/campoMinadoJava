package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Celula extends JButton {
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

        setPreferredSize(new Dimension(Tabuleiro.TAMANHO_CELULA, Tabuleiro.TAMANHO_CELULA));
        setFont(new Font("Arial", Font.PLAIN, 20));
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
            JOptionPane.showMessageDialog(null, "Você acionou uma mina! Fim de jogo.");
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
                if (novoX >= 0 && novoX < Tabuleiro.larguraTabuleiro && novoY >= 0 && novoY < Tabuleiro.alturaTabuleiro) {
                    Celula vizinha = Tabuleiro.celulas[novoY][novoX];
                    if (!vizinha.estaRevelada) {
                        vizinha.revelar();
                    }
                }
            }
        }
    }
}
