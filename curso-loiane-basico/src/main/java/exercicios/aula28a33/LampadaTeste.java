package atividades28_33;

import java.util.Scanner;

public class LampadaTeste {

    public static void main(String[] args) {

        Lampada lampada = new Lampada();

        Scanner scan = new Scanner(System.in);

        System.out.println("Digite as características da lâmpada");
        System.out.print("Marca: ");
        lampada.setMarca(scan.next());
        System.out.print("Tipo: ");
        lampada.setTipo(scan.next());
        System.out.print("Tensão em volts: ");
        lampada.setTensao(scan.nextInt());
        System.out.print("Preço: R$");
        lampada.setPreco(scan.nextDouble());

        System.out.println();

        lampada.ligarDesligar();

        scan.close();

    }

}
