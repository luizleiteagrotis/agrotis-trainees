package atividades20;

import java.util.Random;

public class Atv01_MatrizMaiorAleatorio {

    public static void main(String[] args) {

        // exercício feito com ajuda da correção
        int[][] numerosAleatorios = new int[4][4];

        Random random = new Random();

        for (int i = 0; i < numerosAleatorios.length; i++) {
            for (int j = 0; j < numerosAleatorios[i].length; j++) {
                numerosAleatorios[i][j] = random.nextInt(9);
            }
        }

        int maior = Integer.MIN_VALUE; // esta função calcula o maior valor e o
                                       // oposto dela calcula o menor
        int linha = 0, coluna = 0;

        for (int i = 0; i < numerosAleatorios.length; i++) {
            for (int j = 0; j < numerosAleatorios[i].length; j++) {
                if (numerosAleatorios[i][j] > maior) {
                    maior = numerosAleatorios[i][j];
                    linha = i;
                    coluna = j;
                }
            }
        }

        for (int i = 0; i < numerosAleatorios.length; i++) {
            for (int j = 0; j < numerosAleatorios[i].length; j++) {
                System.out.print(numerosAleatorios[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Maior valor = " + maior);
        System.out.println("Linha = " + linha);
        System.out.println("Coluna = " + coluna);

    }

}
