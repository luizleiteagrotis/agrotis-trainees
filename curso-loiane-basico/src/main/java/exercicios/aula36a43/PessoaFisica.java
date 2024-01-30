package atividades37_43;

public class PessoaFisica extends Contribuinte {

    public PessoaFisica(String nome, double d) {
        super(nome, d);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void calcularImposto() {

        double valorImposto = 0;
        double renda = super.getRendaBruta();

        if (renda >= 0 && renda <= 1400) {

        } else if (renda > 1400 && renda <= 2100) {
            valorImposto = (renda * .1) - 100;
        } else if (renda > 2100 && renda <= 2800) {
            valorImposto = (renda * .15) - 270;
        } else if (renda > 2800 && renda <= 3600) {
            valorImposto = (renda * .25) - 500;
        } else {
            valorImposto = (renda * .3) - 700;
        }

        System.out.println("O valor devido pelo contribuinte " + this.getNome() + " é de R$" + valorImposto);

    }

}
