package atividades28_33;

import java.util.Scanner;

public class ContaCorrenteTeste {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // Definindo valores para uma conta fictícia a fim de testar os métodos
        // criados

        ContaCorrente conta1 = new ContaCorrente(1, -255, true, 1000);

        int menu = 0;

        System.out.println(
                        "Seja bem-vindo(a)! \nDigite 1 para verificar seu saldo \nDigite 2 para realizar saque \nDigite 3 para realizar depósito \nDigite 4 para verificar utilização de cheque especial ");
        menu = scan.nextInt();

        switch (menu) {
            case 1:
                conta1.consultarSaldo();
                break;
            case 2:
                System.out.print("Digite o quanto deseja sacar: ");
                conta1.realizarSaque(scan.nextDouble());
                break;
            case 3:
                System.out.print("Digite o valor que deseja depositar: ");
                conta1.realizarDeposito(scan.nextDouble());
                break;
            case 4:
                conta1.consultarChequeEspecial();
                break;
            default:
                System.out.println("Valor inválido, tente novamente.");
                System.out.println(
                                "Digite 1 para verificar seu saldo \nDigite 2 para realizar saque \nDigite 3 para realizar depósito \nDigite 4 para verificar utilização de cheque especial ");
                menu = scan.nextInt();
        }

        scan.close();

    }

}
