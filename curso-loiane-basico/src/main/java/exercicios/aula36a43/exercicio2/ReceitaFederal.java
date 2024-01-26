package exercicios.aula36a43.exercicio2;

public class ReceitaFederal implements ImpostoDeRenda {

	private Contribuinte contribuinte;
	private PessoaEnum pessoa;
	private Double rendaBruta;

	public ReceitaFederal() {
	}

	public ReceitaFederal(Contribuinte contribuinte, PessoaEnum pessoa, Double rendaBruta) {
		this.contribuinte = contribuinte;
		this.pessoa = pessoa;
		this.rendaBruta = rendaBruta;
	}

	public Contribuinte getContribuinte() {
		return contribuinte;
	}

	public void setContribuinte(Contribuinte contribuinte) {
		this.contribuinte = contribuinte;
	}

	public PessoaEnum getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEnum pessoa) {
		this.pessoa = pessoa;
	}

	public Double getRendaBruta() {
		return rendaBruta;
	}

	public void setRendaBruta(Double rendaBruta) {
		this.rendaBruta = rendaBruta;
	}

	public Double calculoPessoaFisica() {
		double desconto = this.rendaBruta * 0.10;
		System.out.println("Desconto a ser pago: " + desconto);
		double novaRenda = this.rendaBruta - desconto;

		this.rendaBruta = novaRenda;
		return novaRenda;
	}

	public Double calculoPessoaJuridica() {
		double desconto = this.rendaBruta * aliquota(this.rendaBruta);
		System.out.println("Desconto a ser pago: " + desconto);
		double novaRenda = this.rendaBruta - desconto;

		this.rendaBruta = novaRenda;

		return novaRenda;
	}
	
	private Double calculoRenda() {
		if (pessoa == PessoaEnum.PESSOA_FISICA) {
			return calculoPessoaFisica();
		} else if (pessoa == PessoaEnum.PESSOA_JURIDICA) {
			return calculoPessoaJuridica();
		} else {
			return rendaBruta;
		}
	}

	private double aliquota(double rendaBruta) {
		if (rendaBruta <= 1400) {
			return 0.0;
		} else if (rendaBruta <= 2100) {
			return 0.10;
		} else if (rendaBruta <= 2800) {
			return 0.15;
		} else if (rendaBruta <= 3600) {
			return 0.25;
		} else {
			return 0.30;
		}
	}

	@Override
	public String toString() {
		return contribuinte
				+ "Pessoa: " + pessoa +"\n"
				+ "Renda Bruta: " + rendaBruta +"\n"
				+ "Renda após cálculo: " + calculoRenda() + "\n";
	}

}
