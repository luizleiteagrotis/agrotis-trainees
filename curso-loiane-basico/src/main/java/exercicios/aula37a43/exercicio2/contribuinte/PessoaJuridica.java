package exercicios.aula37a43.exercicio2.contribuinte;

public class PessoaJuridica implements Contribuinte{

	private String nome;
	private double rendaBruta;
	
	public PessoaJuridica(String nome, double rendaBruta) {
		this.nome = nome;
		this.rendaBruta = rendaBruta;
	}
	
	@Override
	public double calcularImposto() {
		return rendaBruta * 0.10;
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + "\tRendaBruta: " + rendaBruta;
	}
}
