package atividades28_33;

import java.util.Scanner;

public class Lampada {

    private String marca;
    private String tipo;
    private int tensao; // declaradas em volts
    private double preco;

    public Lampada() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Lampada(String marca, String tipo, int tensao, double preco) {
        super();
        this.marca = marca;
        this.tipo = tipo;
        this.tensao = tensao;
        this.preco = preco;
    }

    public Lampada(String tipo, int tensao, double preco) {
        super();
        this.tipo = tipo;
        this.tensao = tensao;
        this.preco = preco;
    }

    public Lampada(String marca, String tipo, int tensao) {
        super();
        this.marca = marca;
        this.tipo = tipo;
        this.tensao = tensao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTensao() {
        return tensao;
    }

    public void setTensao(int tensao) {
        this.tensao = tensao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void ligar() {
        System.out.println("Você acendeu a lâmpada.");
    }

    public void desligar() {
        System.out.println("Você desligou a lâmpada.");
    }

    public void ligarDesligar() {
        Scanner scan = new Scanner(System.in);

        int entrada = -1;

        System.out.println("Digite 1 para ligar sua lâmpada e 0 para desligá-la: ");
        entrada = scan.nextInt();
        if (entrada == 1) {
            ligar();
        } else if (entrada == 0) {
            desligar();
        } else {
            while (entrada != 0 && entrada != 1) {
                System.out.println("Valor inválido. Tente novamente");
                ligarDesligar();
            }
        }

        scan.close();
    }

}
