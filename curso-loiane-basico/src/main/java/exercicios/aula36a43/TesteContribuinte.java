package atividades37_43;

public class TesteContribuinte {

    public static void main(String[] args) {

        Contribuinte pj1 = new PessoaJuridica("Pessoa Jurídica1", 1000);
        Contribuinte pj2 = new PessoaJuridica("Pessoa Jurídica2", 250000.5);
        Contribuinte pj3 = new PessoaJuridica("Pessoa Jurídica3", 5000000);

        Contribuinte pf1 = new PessoaFisica("Pessoa Física1", 1400);
        Contribuinte pf2 = new PessoaFisica("Pessoa Física2", 2000);
        Contribuinte pf3 = new PessoaFisica("Pessoa Física3", 2500);
        Contribuinte pf4 = new PessoaFisica("Pessoa Física4", 3100);
        Contribuinte pf5 = new PessoaFisica("Pessoa Física5", 3700.8);

        pj1.calcularImposto();
        pj2.calcularImposto();
        pj3.calcularImposto();

        System.out.println();

        pf1.calcularImposto();
        pf2.calcularImposto();
        pf3.calcularImposto();
        pf4.calcularImposto();
        pf5.calcularImposto();

    }

}
