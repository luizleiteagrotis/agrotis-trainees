package exercicios.aula28a33.exercicio1;

public class Lampada {
    private String modelo;
    private String cor;
    private String tensao;
    private String marca;
    private int potencia;
    private int durabilidade;
    private int economiaDeEnergia;
    private int garantiaMeses;
    private boolean estado;

    public Lampada() {
        this.estado = false;
        this.garantiaMeses = 12;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTensao() {
        return tensao;
    }

    public void setTensao(String tensao) {
        this.tensao = tensao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }

    public int getEconomiaDeEnergia() {
        return economiaDeEnergia;
    }

    public void setEconomiaDeEnergia(int economiaDeEnergia) {
        this.economiaDeEnergia = economiaDeEnergia;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void ligar() {
        this.setEstado(true);
        System.out.println("Ligado");
    }

    public void desligar() {
        this.setEstado(false);
        System.out.println("Desligado");
    }

}
