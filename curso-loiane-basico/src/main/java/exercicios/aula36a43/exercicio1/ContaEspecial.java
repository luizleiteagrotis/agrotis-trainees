package exercicios.aula36a43.exercicio1;

public class ContaEspecial extends ContaBancaria {
    private double limiteFixo;
    private double limite;

    public ContaEspecial() {
    };

    public ContaEspecial(String nomeCliente) {
        super(nomeCliente);
        this.limiteFixo = 500;
        this.limite = limiteFixo;
    }

    public double getLimiteFixo() {
        return limite;
    }

    public void setLimiteFixo(double limiteFixo) {
        this.limiteFixo = limiteFixo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        return "ContaBancaria [nomeCliente=" + getNomeCliente() + ", numConta=" + getNumConta() + ", saldo=" + getSaldo()
                        + ", limite= " + this.limite + "]";
    }

    @Override
    public boolean sacar(double valorSaque) {
        boolean saqueRealizado = false;
        double novoSaldo;
        if (this.getSaldo() > valorSaque) {
            novoSaldo = this.getSaldo() - valorSaque;
            this.setSaldo(novoSaldo);
            System.out.println("Saque realizado");
            saqueRealizado = true;
        } else if (this.limite >= valorSaque) {
            novoSaldo = this.getSaldo() - valorSaque;
            this.limite -= valorSaque;
            this.setSaldo(novoSaldo);
            System.out.println("Saque realizado");
        }

        return saqueRealizado;
    }

    public boolean depositar(double valorDeposito) {
        boolean depositoRealizado = false;
        double novoSaldo;
        if (valorDeposito > 0) {
            novoSaldo = this.getSaldo() + valorDeposito;
            System.out.println("Deposito realizado");
            depositoRealizado = true;
            this.setSaldo(novoSaldo);
            if (this.getSaldo() > 0) {
                this.limite = limiteFixo;
            }
        }

        return depositoRealizado;
    }

}
