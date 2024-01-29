package exercicios.aula25a27.exercicio2;

public class ContaCorrente {
    public String tipo = "corrente";
    public int numero;
    public String nome;
    public double saldo;
    public int limite;
    public boolean especial = false;

    public double depositar(double valor) {

        if (valor < 0) {
            System.out.println("valor invalido");
        } else {
            this.saldo += valor;
        }
        return this.saldo;
    }

    public double sacar(int valor) {
        if (valor > this.saldo && valor < 0) {
            System.out.println("Não foi possivel sacar este valor");
        } else {
            this.saldo -= valor;
        }
        return saldo;
    }

    public void consultarSaldo() {
        System.out.println("O seu saldo é R$ " + this.saldo);
    }

    public void consultarCheque() {
        System.out.println(" Está usando cheque especial " + this.especial);
    }
}
