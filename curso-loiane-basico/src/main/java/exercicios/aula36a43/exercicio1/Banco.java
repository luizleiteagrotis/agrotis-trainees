package exercicios.aula36a43.exercicio1;

public class Banco {

    public static void main(String[] args) {
        ContaPoupanca conta = new ContaPoupanca("Matheus Lourenço");
        conta.depositar(1000);
        conta.sacar(500);
        conta.calcularNovoSaldo(0.10);
        System.out.println(conta);

        ContaEspecial conta2 = new ContaEspecial("Luan");
        System.out.println(conta2);
        conta2.sacar(500);
        System.out.println(conta2);
        conta2.depositar(1500);
        System.out.println(conta2);
    }

}
