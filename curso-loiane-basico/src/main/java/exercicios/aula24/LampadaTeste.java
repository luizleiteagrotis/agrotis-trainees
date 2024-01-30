package atividades24;

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

    }

}
