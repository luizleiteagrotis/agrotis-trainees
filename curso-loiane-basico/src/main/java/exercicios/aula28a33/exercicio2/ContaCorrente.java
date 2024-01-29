package exercicios.aula28a33.exercicio2;

public class ContaCorrente {
    private static int numero = 1;
    private int id;
    private String tipo;
    private String nome;
    private double saldo;
    private int limite;
    private boolean especial;

    public ContaCorrente(String nome) {
        super();
        this.nome = nome;
        this.tipo = "Corrente";
        this.id = ContaCorrente.numero;
        this.saldo = 0;
        this.limite = 500;
        this.especial = false;
        ContaCorrente.numero++;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public void mostrarInformacoes() {
        System.out.println("Nome " + this.nome);
        System.out.println("Id " + this.id);
        System.out.println("Tipo " + this.tipo);
        System.out.println("Limite " + this.limite);
        System.out.println("Saldo " + this.saldo);
        System.out.println("-----------------");
    }

    public double depositar(double valor) {

        if (valor > 0) {
            this.saldo += valor;
        } else {
            System.out.println("Valor invalido");
        }
        return this.saldo;
    }

    public double sacar(int valor) {

        if (this.saldo >= valor && valor > 0) {
            this.saldo -= valor;
        } else if (limite > 0) {
            this.especial = true;
            this.limite -= valor;
            this.saldo -= valor;
            System.out.println("Usado o cheque especial");
        }

        return saldo;
    }

    void consultarSaldo() {
        System.out.println("O seu saldo é R$ " + this.saldo);
    }

    void consultarCheque() {
        System.out.println(" Está usando cheque especial " + this.especial);
    }
}
