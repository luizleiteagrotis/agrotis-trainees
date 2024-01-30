package atividades37_43;

public class ContaEspecial extends ContaBancaria {

    private double limite;

    public ContaEspecial() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ContaEspecial(String nomeCliente, int i, double saldo, double limite) {
        super(nomeCliente, i, saldo);
        this.setLimite(limite);
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public void sacar(double valor) {

        if ((super.saldo - valor) < -limite) {
            System.out.println("Operação negada. Valor acima do limite disponível.");
            super.consultarSaldo();
        } else {
            super.saldo -= valor;
            System.out.println("Saque no valor de R$" + valor + " realizado");
            super.consultarSaldo();
        }

    }

    @Override
    public String toString() {
        return "ContaEspecial [limite=" + limite + ", saldo=" + saldo + ", getLimite()=" + getLimite() + ", getNomeCliente()="
                        + getNomeCliente() + ", getNumConta()=" + getNumConta() + ", getSaldo()=" + getSaldo() + ", toString()="
                        + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
    }

}
