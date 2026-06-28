public class Jogador {
    private char simbolo;
    private int numero;
    private static int total = 0;

    public Jogador(char simbolo) {
        this.simbolo = simbolo;
        this.numero = ++Jogador.total;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public int getOrdem() {
        return numero;
    }
}