package exercicios.aula36a43.exercicio2;

public class ReceitaFederal {
    public static void main(String[] args) {
        PessoaJuridica pessoaJ1 = new PessoaJuridica("Marinaldo", 5000, "25.796.920/0001-99");
        PessoaJuridica pessoaJ2 = new PessoaJuridica("Arnaldo", 1000, "99.738.297/0001-54");
        PessoaJuridica pessoaJ3 = new PessoaJuridica("Gabriel", 10000, "81.516.574/0001-19");

        PessoaFisica pessoaF1 = new PessoaFisica("Matheus", 2155, "103.952.609-81");
        PessoaFisica pessoaF2 = new PessoaFisica("José", 1000, "566.041.530-02");
        PessoaFisica pessoaF3 = new PessoaFisica("Kleber", 3900, "133.315.560-33");

        Pessoa[] contribuientes = new Pessoa[6];
        contribuientes[0] = pessoaF1;
        contribuientes[1] = pessoaF2;
        contribuientes[2] = pessoaF3;
        contribuientes[3] = pessoaJ1;
        contribuientes[4] = pessoaJ2;
        contribuientes[5] = pessoaJ3;

        for (Pessoa contribuinte : contribuientes) {
            System.out.println(contribuinte);
        }
    }
}
