package exercicios.aula37a43.exercicio2.contribuinte;

public class PessoaFisica implements Contribuinte{
	
	private String nome;
	private double salarioBruto;
	
	public PessoaFisica(String nome, double salarioBruto) {
		this.nome = nome;
		this.salarioBruto = salarioBruto;
	}

	@Override
	public double calcularImposto() {
		if (salarioBruto < 1400.01) return 0;
		if (salarioBruto < 2100.01) return salarioBruto * 0.10 + 100;
		if (salarioBruto < 2800.01) return salarioBruto * 0.15 + 270;
		if (salarioBruto < 3600.01) return salarioBruto * 0.25 + 500;
		return salarioBruto * 0.30 + 700;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + "\tSalarioBruto: " + salarioBruto;
	}
}
