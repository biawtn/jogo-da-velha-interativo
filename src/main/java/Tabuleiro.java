class Tabuleiro {
    private char[][] grade;

    public Tabuleiro() {
        grade = new char[3][3];
        limpar();
    }

    private void limpar() {
        for (int l = 0; l < 3; l++)
            for (int c = 0; c < 3; c++)
                grade[l][c] = ' ';
    }

    public boolean jogar(Jogador jogador, int linha, int coluna, String nome, boolean humano) {
        if (grade[linha][coluna] != ' ') {
            if (humano) System.out.println("\nPosição já ocupada!\n");
            return false;
        }
        grade[linha][coluna] = jogador.getSimbolo();
        System.out.println("Jogada de " + nome);
        mostrar();
        return true;
    }

    public boolean acabouOJogo() {
        return estaCompleto() || haUmVencedor();
    }

    public boolean estaCompleto() {
        for (int l = 0; l < 3; l++)
            for (int c = 0; c < 3; c++)
                if (grade[l][c] == ' ') return false;
        return true;
    }

    public boolean todasAsCasasPreenchidas() {
        return estaCompleto();
    }

    public boolean haUmVencedor() {
        for (int i = 0; i < 3; i++) {
            if (verificarLinha(i) || verificarColuna(i)) return true;
        }
        return verificarDiagonais();
    }

    private boolean verificarLinha(int l) {
        return grade[l][0] != ' '
            && grade[l][0] == grade[l][1]
            && grade[l][1] == grade[l][2];
    }

    private boolean verificarColuna(int c) {
        return grade[0][c] != ' '
            && grade[0][c] == grade[1][c]
            && grade[1][c] == grade[2][c];
    }

    private boolean verificarDiagonais() {
        boolean d1 = grade[0][0] != ' '
            && grade[0][0] == grade[1][1]
            && grade[1][1] == grade[2][2];
        boolean d2 = grade[0][2] != ' '
            && grade[0][2] == grade[1][1]
            && grade[1][1] == grade[2][0];
        return d1 || d2;
    }

    public void mostrar() {
        System.out.println("  0   1   2");
        for (int l = 0; l < 3; l++) {
            System.out.print(l + " ");
            for (int c = 0; c < 3; c++) {
                System.out.print(" " + grade[l][c] + " ");
                if (c < 2) System.out.print("|");
            }
            System.out.println();
            if (l < 2) System.out.println("  ---+---+---");
        }
        System.out.println();
    }
}