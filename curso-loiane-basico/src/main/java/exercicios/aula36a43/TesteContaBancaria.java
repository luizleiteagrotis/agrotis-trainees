package atividades37_43;

public class TesteContaBancaria {

    public static void main(String[] args) {

        ContaBancaria contaBancaria = new ContaBancaria("Conta1", 01, 500);
        ContaEspecial contaEspecial = new ContaEspecial("Conta Especial", 02, -100, 200);
        ContaPoupanca contaPoupanca = new ContaPoupanca("Conta Poupança", 03, 1000);

        System.out.println("Saque Conta 01");
        contaBancaria.sacar(600);
        contaBancaria.sacar(300);
        System.out.println();

        System.out.println("Saque Conta 02 (Conta Especial)");
        contaEspecial.sacar(100);
        contaEspecial.sacar(100);
        System.out.println();

        System.out.println("Saque Conta 03 (Conta Poupança)");
        contaPoupanca.sacar(600);
        contaPoupanca.sacar(500);
        System.out.println();

        System.out.println("Depósito Conta 01");
        contaBancaria.realizarDeposito(1000);
        System.out.println();

        System.out.println("Depósito Conta 02");
        contaEspecial.realizarDeposito(1000);
        System.out.println();

        System.out.println("Depósito Conta 03");
        contaPoupanca.realizarDeposito(1000);
        System.out.println();

        System.out.println("Mostrar saldo após rendimento Conta Poupança");
        contaPoupanca.setDiaRendimento(.1);
        contaPoupanca.calcularNovoSaldo();
        System.out.println();

        System.out.println(
                        "Para o teste abaixo utilizei o método toString() para mostrar as informações coletadas. Nas próximas atividades realizarei um menu mais "
                                        + "\"bonitinho\"");
        System.out.println(contaBancaria.toString());
        System.out.println();
        System.out.println(contaEspecial.toString());
        System.out.println();
        System.out.println(contaPoupanca.toString());

    }

}
