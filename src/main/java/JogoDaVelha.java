import java.util.Random;
import java.util.Scanner;

class JogoDaVelha {
    private Jogador jogador1;
    private Jogador jogador2;
    private Tabuleiro tabuleiro;
    private Scanner scanner;
    private Random rng;

    public JogoDaVelha(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.tabuleiro = tabuleiro;
        this.scanner = new Scanner(System.in);
        this.rng = new Random();
    }

    public void iniciar() {
        System.out.print("Qual é o seu nome? ");
        String nomeJogador = scanner.nextLine();
        System.out.println();

        char marcaEscolhida = pedirSimbolo();
        char marcaBot = (marcaEscolhida == 'X') ? 'O' : 'X';

        jogador1 = new Jogador(marcaEscolhida);
        jogador2 = new Jogador(marcaBot);

        String ultimoAJogar = "";
        int rodada = 1;

        while (!tabuleiro.acabouOJogo()) {
            System.out.println("=== Rodada " + rodada + " ===\n");

            int[] jogadaHumano = pedirJogada();
            while (!tabuleiro.jogar(jogador1, jogadaHumano[0], jogadaHumano[1], nomeJogador, true)) {
                jogadaHumano = pedirJogada();
            }
            ultimoAJogar = nomeJogador;
            System.out.println();

            if (tabuleiro.acabouOJogo()) break;

            int linhaBot, colunaBot;
            do {
                linhaBot = rng.nextInt(3);
                colunaBot = rng.nextInt(3);
            } while (!tabuleiro.jogar(jogador2, linhaBot, colunaBot, "Bot", false));
            ultimoAJogar = "Bot";

            rodada++;
        }

        if (tabuleiro.haUmVencedor()) {
            System.out.println(ultimoAJogar + " venceu o jogo!");
        } else {
            System.out.println("Empate! Ninguém venceu.");
        }
    }

    private char pedirSimbolo() {
        char escolha = ' ';
        while (escolha != 'X' && escolha != 'O') {
            System.out.print("Escolha seu símbolo ('X' ou 'O'): ");
            String entrada = scanner.nextLine().toUpperCase();
            if (!entrada.isEmpty()) escolha = entrada.charAt(0);
            if (escolha != 'X' && escolha != 'O')
                System.out.println("Símbolo inválido, tente novamente.");
            System.out.println();
        }
        return escolha;
    }

    private int[] pedirJogada() {
        int linha = lerNumero("Linha (0-2): ", 0, 2);
        int coluna = lerNumero("Coluna (0-2): ", 0, 2);
        return new int[]{linha, coluna};
    }

    private int lerNumero(String msg, int min, int max) {
        int valor = -1;
        while (valor < min || valor > max) {
            System.out.print(msg);
            valor = scanner.nextInt();
            if (valor < min || valor > max)
                System.out.println("Valor inválido, use entre " + min + " e " + max + ".");
            System.out.println();
        }
        scanner.nextLine();
        return valor;
    }

    public static void main(String[] args) {
        Jogador j1 = new Jogador('X');
        Jogador j2 = new Jogador('O');
        Tabuleiro tab = new Tabuleiro();
        new JogoDaVelha(j1, j2, tab).iniciar();
    }
}