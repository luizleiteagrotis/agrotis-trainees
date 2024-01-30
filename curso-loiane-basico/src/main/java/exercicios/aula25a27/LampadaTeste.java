package atividades25_27;

import java.util.Scanner;

public class LampadaTeste {

    public static void main(String[] args) {

        Lampada incandescente = new Lampada();
        incandescente.marca = "Elgin";
        incandescente.tipo = "Incandescente";
        incandescente.tensao = 15;
        incandescente.preco = 10.2;

        System.out.println("Lâmpada " + incandescente.marca + " " + incandescente.tipo + " " + incandescente.tensao + "v " + "\tR$"
                        + incandescente.preco);

        Lampada halogena = new Lampada();
        halogena.marca = "Empalux";
        halogena.tipo = "Halógena";
        halogena.tensao = 12;
        halogena.preco = 15;

        System.out.println("Lâmpada " + halogena.marca + " " + halogena.tipo + " " + halogena.tensao + "v " + "\t\tR$"
                        + halogena.preco);

        Lampada fluorescente = new Lampada();
        fluorescente.marca = "Elgin";
        fluorescente.tipo = "Fluorescente";
        fluorescente.tensao = 35;
        fluorescente.preco = 5.5;

        System.out.println("Lâmpada " + fluorescente.marca + " " + fluorescente.tipo + " " + fluorescente.tensao + "v " + "\t\tR$"
                        + fluorescente.preco);

        Lampada led = new Lampada();
        led.marca = "Ledvance";
        led.tipo = "LED";
        led.tensao = 5;
        led.preco = 25;

        System.out.println("Lâmpada " + led.marca + " " + led.tipo + " " + led.tensao + "v " + "\t\tR$" + led.preco);
        System.out.println();

        Scanner scan = new Scanner(System.in);

        System.out.println("Olá. Escolha sua lâmpada: 1 para incandescente, 2 para halógena, 3 para fluorescente e 4 para LED: ");
        int lampadaEscolhida = scan.nextInt();

        while (lampadaEscolhida < 1 && lampadaEscolhida > 4) {
            System.out.println("Valor inválido. Tente novamente escolhendo entre 1 e 4: ");
            lampadaEscolhida = scan.nextInt();
        }

        int acesa = 1;

        switch (lampadaEscolhida) {
            case 1:
                incandescente.ligar();
                System.out.println("Sua lâmpada está acesa! Digite 0 para apagá-la!");
                acesa = scan.nextInt();
                if (acesa == 0) {
                    incandescente.desligar();
                } else {
                    System.out.println("Você deixou sua lâmpada acesa gastando energia...");
                }
                break;
            case 2:
                halogena.ligar();
                System.out.println("Sua lâmpada está acesa! Digite 0 para apagá-la!");
                acesa = scan.nextInt();
                if (acesa == 0) {
                    halogena.desligar();
                } else {
                    System.out.println("Você deixou sua lâmpada acesa gastando energia...");
                }
                break;
            case 3:
                fluorescente.desligar();
                break;
            case 4:
                led.desligar();
                break;
        }

        scan.close();

    }

}
