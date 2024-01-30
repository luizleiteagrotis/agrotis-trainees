package atividades25_27;

public class ContaCorrente {

    int numero;
    double saldo;
    boolean especial;
    double limite;

    public void consultarSaldo() {
        System.out.println("Seu saldo é de: " + saldo);
    }

    public double realizarSaque(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido.");
            return saldo;
        } else if ((saldo - valor) < 0) {
            System.out.println("Você não pode sacar este valor. Saldo disponível é de R$" + saldo);
            return saldo;
        } else {
            double saldoRestante = saldo - valor;
            saldo = saldoRestante;
            System.out.println("Saque no valor de R$" + valor + " realizado");
            this.consultarSaldo();
            return saldo;
        }
    }

    public double realizarDeposito(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido. Apenas valores positivos aceitos.");
            return saldo;
        } else {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado");
            this.consultarSaldo();
            return saldo;
        }
    }

    public void consultarChequeEspecial() {
        if (saldo < 0) {
            System.out.println("Você está usando cheque especial");
        } else {
            System.out.println("Você não está usando cheque especial");
        }

    }

}
