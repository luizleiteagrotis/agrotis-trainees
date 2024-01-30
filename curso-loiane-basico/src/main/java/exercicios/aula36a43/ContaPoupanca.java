package atividades37_43;

public class ContaPoupanca extends ContaBancaria {

    private double diaRendimento;

    public ContaPoupanca() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ContaPoupanca(String nomeCliente, int i, double saldo) {
        super(nomeCliente, i, saldo);
        // TODO Auto-generated constructor stub
    }

    public double getDiaRendimento() {
        return diaRendimento;
    }

    public void setDiaRendimento(double diaRendimento) {
        this.diaRendimento = diaRendimento;
    }

    public void calcularNovoSaldo() {
        super.saldo += (saldo * diaRendimento);
        System.out.println("Saldo atualizado!");
        super.consultarSaldo();
    }

    @Override
    public String toString() {
        return "ContaPoupanca [diaRendimento=" + diaRendimento + ", saldo=" + saldo + ", getDiaRendimento()=" + getDiaRendimento()
                        + ", getNomeCliente()=" + getNomeCliente() + ", getNumConta()=" + getNumConta() + ", getSaldo()="
                        + getSaldo() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                        + super.toString() + "]";
    }

}
