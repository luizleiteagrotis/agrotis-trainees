package atividades37_43;

public class PessoaJuridica extends Contribuinte {

    public PessoaJuridica(String nome, double rendaBruta) {
        super(nome, rendaBruta);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void calcularImposto() {

        System.out.println("O valor devido pelo contribuinte " + this.getNome() + " é de R$" + (super.getRendaBruta() * 0.1));

    }

}
