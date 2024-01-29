package exercicios.aula25a27.exercicio1;

public class Lampada {
    String modelo;
    String cor;
    String tensao;
    String marca;
    int potencia;
    int durabilidade;
    int economiaDeEnergia;
    int garantiaMeses;
    public boolean estado;

    public boolean ligar() {
        return estado = true;
    }

    boolean desligar() {
        return estado = false;
    }

}
