package atividades25_27;

public class ContaCorrenteTeste {

    public static void main(String[] args) {

        ContaCorrente c0 = new ContaCorrente();
        c0.numero = 0;
        c0.saldo = -259.98;
        c0.especial = false;
        c0.limite = 1000;

        if (c0.especial == true) {
            System.out.println("Conta especial");
        }
        System.out.println("Número: \t" + c0.numero);
        System.out.println("Saldo: \t\tR$ " + c0.saldo);
        System.out.println("Limite: \tR$ " + c0.limite);

        System.out.println();
        System.out.println("Testando novos métodos: ");

        c0.consultarChequeEspecial();
        c0.consultarSaldo();
        c0.realizarSaque(10);
        c0.realizarDeposito(260);
        c0.consultarChequeEspecial();

        System.out.println("\n");

        ContaCorrente c1 = new ContaCorrente();
        c1.numero = 1;
        c1.saldo = 859812.97;
        c1.especial = true;
        c1.limite = 20000;

        if (c1.especial == true) {
            System.out.println("Conta especial");
        }
        System.out.println("Número: \t" + c1.numero);
        System.out.println("Saldo: \t\tR$ " + c1.saldo);
        System.out.println("Limite: \tR$ " + c1.limite);

        System.out.println();
        System.out.println("Testando novos métodos: ");

        c1.consultarChequeEspecial();
        c1.consultarSaldo();
        c1.realizarSaque(10000); // não funcionou
        c1.realizarDeposito(20);
        c1.consultarChequeEspecial();

    }

}