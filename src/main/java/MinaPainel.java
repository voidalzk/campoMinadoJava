

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MinaPainel extends JPanel {
    private Celula[][] celulas;
    private int linhas;
    private int colunas;
    private int minas;

    public MinaPainel(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;
        setLayout(new GridLayout(linhas, colunas));
        iniciarCampo();
    }

    private void iniciarCampo() {
        celulas = new Celula[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                celulas[i][j] = new Celula(i, j);
                add(celulas[i][j]);
            }
        }
        sortearMinas();
        calcularNumeros();
    }

    private void sortearMinas() {
        Random rand = new Random();
        int minasSorteadas = 0;
        while (minasSorteadas < minas) {
            int linha = rand.nextInt(linhas);
            int coluna = rand.nextInt(colunas);
            if (!celulas[linha][coluna].temMina()) {
                celulas[linha][coluna].colocarMina();
                minasSorteadas++;
            }
        }
    }

    private void calcularNumeros() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (!celulas[i][j].temMina()) {
                    int numMinas = contarMinasVizinhas(i, j);
                    if (numMinas > 0) {
                        celulas[i][j].setTexto(Integer.toString(numMinas));
                    }
                }
            }
        }
    }

    private int contarMinasVizinhas(int linha, int coluna) {
        int contador = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int novaLinha = linha + i;
                int novaColuna = coluna + j;
                if (novaLinha >= 0 && novaLinha < linhas && novaColuna >= 0 && novaColuna < colunas) {
                    if (celulas[novaLinha][novaColuna].temMina()) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }
}
