package atividades37_43;

public class ContaBancaria {

    private String nomeCliente;
    private int numConta;
    protected double saldo;

    public ContaBancaria() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ContaBancaria(String nomeCliente, int i, double saldo) {
        super();
        this.nomeCliente = nomeCliente;
        this.numConta = i;
        this.saldo = saldo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void consultarSaldo() {
        System.out.println("Seu saldo atual é de: " + saldo);
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido.");
        } else if ((saldo - valor) < 0) {
            System.out.println("Você não pode sacar este valor. Saldo insuficiente: R$" + saldo);
        } else {
            saldo -= valor;
            System.out.println("Saque no valor de R$" + valor + " realizado");
            this.consultarSaldo();
        }
    }

    public double realizarDeposito(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido. Apenas valores positivos aceitos.");
        } else {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado");
            this.consultarSaldo();
        }

        return saldo;
    }

    @Override
    public String toString() {
        return "ContaBancaria [nomeCliente=" + nomeCliente + ", numConta=" + numConta + ", saldo=" + saldo + ", getNomeCliente()="
                        + getNomeCliente() + ", getNumConta()=" + getNumConta() + ", getSaldo()=" + getSaldo() + ", getClass()="
                        + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

}
