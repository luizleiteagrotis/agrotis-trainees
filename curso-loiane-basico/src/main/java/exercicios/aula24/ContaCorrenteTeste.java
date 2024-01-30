package atividades24;

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

    }

}
