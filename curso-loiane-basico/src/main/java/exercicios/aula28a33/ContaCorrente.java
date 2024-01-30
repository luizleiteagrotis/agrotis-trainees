package atividades28_33;

public class ContaCorrente {

    private int numero;
    private double saldo;
    private boolean especial;
    private double limite;

    public ContaCorrente() {
        super();
    }

    public ContaCorrente(int numero, double saldo, boolean especial, double limite) {
        super();
        this.numero = numero;
        this.saldo = saldo;
        this.especial = especial;
        this.limite = limite;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    void consultarSaldo() {
        System.out.println("Seu saldo é de: R$" + saldo);
    }

    double realizarSaque(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido.");
        } else if ((saldo - valor) < 0) {
            System.out.println("Você não pode sacar este valor. Saldo disponível é de R$" + saldo);
        } else {
            double saldoRestante = saldo - valor;
            saldo = saldoRestante;
            System.out.println("Saque no valor de R$" + valor + " realizado");
            this.consultarSaldo();
        }
        return saldo;
    }

    double realizarDeposito(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido. Apenas valores positivos aceitos.");
        } else {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado");
            this.consultarSaldo();
        }
        return saldo;
    }

    void consultarChequeEspecial() {
        if (saldo < 0) {
            System.out.println("Você está usando cheque especial");
        } else {
            System.out.println("Você não está usando cheque especial");
        }

    }

}
