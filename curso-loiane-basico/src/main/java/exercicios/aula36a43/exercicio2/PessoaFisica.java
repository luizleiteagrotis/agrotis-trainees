package exercicios.aula36a43.exercicio2;

public class PessoaFisica extends Pessoa {

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public PessoaFisica(String nome, double rendaBruta, String cpf) {
        super(nome, rendaBruta);
        this.cpf = cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        String s = super.toString();
        s += ", cpf=" + this.cpf + ", Imposto a pagar  " + this.calcularImposto() + "]";
        return s;
    }

    @Override
    public double calcularImposto() {
        double renda = this.getRendaBruta();
        if (getRendaBruta() > 0 && getRendaBruta() <= 1400) {
            return 0;
        } else if (renda > 1400 && renda <= 2100) {
            return (renda * 0.10) - 100;
        } else if (renda > 2100 && renda <= 2800) {
            return (renda * 0.15) - 270;
        } else if (renda > 2800 && renda <= 3600) {
            return (renda * 0.25) - 500;
        } else {
            return (renda * 0.30) - 700;
        }

    }

}
