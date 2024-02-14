package exercicios.aula36a43;

public class PessoaFisica extends Pessoa {
	
	private String cpf;
	
	public PessoaFisica(String nome, double rendaBruta ,String cpf) {
		super(nome, rendaBruta);
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public double calculaImpostoDeRenda() {
		if(rendaBruta > 1400.01 && rendaBruta < 2100) {
			return (rendaBruta - 100) * 0.1;
		} 
		if(rendaBruta > 2100.01 && rendaBruta < 2800){
			return (rendaBruta - 270) * 0.15;
		} 
		if(rendaBruta > 2800.01 && rendaBruta < 3600) {
			return (rendaBruta - 500) * 0.25;
		} 
		if(rendaBruta > 3600) {
			return (rendaBruta - 700) * 0.3;
		}
		
		return 0;
	}
}
