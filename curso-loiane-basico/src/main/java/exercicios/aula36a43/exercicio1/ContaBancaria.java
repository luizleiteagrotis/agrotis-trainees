package exercicios.aula36a43.exercicio1;

public abstract class ContaBancaria {
    private static int numContaTotal = 0;
    private String nomeCliente;
    private int numConta;
    private double saldo;

    public ContaBancaria() {
    };

    public ContaBancaria(String nomeCliente) {
        super();
        this.nomeCliente = nomeCliente;
        this.numConta = ContaBancaria.numContaTotal;
        this.saldo = 0;
        ContaBancaria.numContaTotal++;
    }

    public static int getNumContaTotal() {
        return numContaTotal;
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

    @Override
    public String toString() {
        return "ContaBancaria [nomeCliente=" + nomeCliente + ", numConta=" + numConta + ", saldo=" + saldo + "]";
    }

    public boolean sacar(double valorSaque) {
        boolean saqueRealizado = false;

        if (this.saldo > valorSaque) {
            this.saldo -= valorSaque;
            System.out.println("Saque realizado");
            saqueRealizado = true;
        }

        return saqueRealizado;
    }

    public boolean depositar(double valorDeposito) {
        boolean depositoRealizado = false;

        if (valorDeposito > 0) {
            this.saldo += valorDeposito;
            System.out.println("Deposito realizado");
            depositoRealizado = true;
        }

        return depositoRealizado;
    }

}
