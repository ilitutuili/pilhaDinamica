import java.util.Random;
import java.util.Scanner;

// Classe que representa um nó da pilha
class No {
    int valor;
    No proximo;
    
    public No(int valor) {
        this.valor = valor;
        this.proximo = null;
    }
}

// Classe da Pilha Dinâmica
class Pilha {
    private No topo;
    private int tamanho;

    public Pilha() {
        this.topo = null;
        this.tamanho = 0;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public void empilhar(int valor) {
        No novo = new No(valor);
        novo.proximo = topo;
        topo = novo;
        tamanho++;
    }

    public Integer desempilhar() {
        if (estaVazia()) {
            return null;
        }
        int valor = topo.valor;
        topo = topo.proximo;
        tamanho--;
        return valor;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void imprimir(String nome) {
        System.out.println(nome + ":");
        No atual = topo;
        while (atual != null) {
            System.out.println(atual.valor);
            atual = atual.proximo;
        }
        System.out.println();
    }
}

public class PilhasDinamicas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Pilha pilha1 = new Pilha();
        Pilha pilha2 = new Pilha();
        Pilha pilha3 = new Pilha();

        int opcao;
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    executarFase1(pilha1, pilha2, pilha3, random);
                    exibirPilhas(pilha1, pilha2, pilha3);
                    break;
                case 2:
                    executarFase2(pilha1, pilha2, pilha3, random);
                    break;
                case 3:
                    System.out.println("\n--- Estado atual das pilhas ---");
                    exibirPilhas(pilha1, pilha2, pilha3);
                    break;
                case 4:
                    System.out.println("\nTamanhos das pilhas:");
                    System.out.println("Pilha 1: " + pilha1.getTamanho());
                    System.out.println("Pilha 2: " + pilha2.getTamanho());
                    System.out.println("Pilha 3: " + pilha3.getTamanho());
                    break;
                case 5:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);

        scanner.close();
    }

    static void exibirMenu() {
        System.out.println("\n========== MENU ==========");
        System.out.println("1. Executar Fase 1 (Distribuição de 100 números)");
        System.out.println("2. Executar Fase 2 (Movimentação entre pilhas)");
        System.out.println("3. Visualizar estado das pilhas");
        System.out.println("4. Visualizar tamanho das pilhas");
        System.out.println("5. Sair");
        System.out.println("==========================");
    }

    static void executarFase1(Pilha pilha1, Pilha pilha2, Pilha pilha3, Random random) {
        System.out.println("\n=== FASE 1: Sorteando e distribuindo 100 números entre as pilhas ===");
        for (int i = 0; i < 100; i++) {
            int numero = random.nextInt(9) + 1; // 1 a 9
            if (numero >= 1 && numero <= 3) {
                pilha1.empilhar(numero);
            } else if (numero >= 4 && numero <= 6) {
                pilha2.empilhar(numero);
            } else {
                pilha3.empilhar(numero);
            }
        }
        System.out.println("Fase 1 concluída!");
    }

    static void executarFase2(Pilha pilha1, Pilha pilha2, Pilha pilha3, Random random) {
        System.out.println("\n=== FASE 2: Sorteando pilhas e movendo elementos ===");
        for (int i = 0; i < 100; i++) {
            int sorteio = random.nextInt(3) + 1; // 1 a 3

            Pilha destino = null;
            Pilha doadora1, doadora2;

            if (sorteio == 1) {
                destino = pilha1;
                doadora1 = pilha2;
                doadora2 = pilha3;
            } else if (sorteio == 2) {
                destino = pilha2;
                doadora1 = pilha1;
                doadora2 = pilha3;
            } else {
                destino = pilha3;
                doadora1 = pilha1;
                doadora2 = pilha2;
            }

            // Se alguma das pilhas doadoras estiver vazia, o programa para
            if (doadora1.estaVazia() || doadora2.estaVazia()) {
                System.out.println("\nO programa foi encerrado porque uma das pilhas está vazia.");
                break;
            }

            Integer valor1 = doadora1.desempilhar();
            Integer valor2 = doadora2.desempilhar();

            System.out.println("Empilhando os números " + valor1 + " e " + valor2 + " na pilha " + sorteio);
            destino.empilhar(valor1);
            destino.empilhar(valor2);

            if (i == 99) {
                System.out.println("\nO programa foi encerrado após 100 sorteios.");
            }
        }
    }

    static void exibirPilhas(Pilha pilha1, Pilha pilha2, Pilha pilha3) {
        pilha1.imprimir("Pilha 1");
        pilha2.imprimir("Pilha 2");
        pilha3.imprimir("Pilha 3");
    }
}