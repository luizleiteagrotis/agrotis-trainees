package exercicios.aula37a43.exercicio2;

public class PessoaFisica extends Contribuinte {

	private String cpf;

	public PessoaFisica() {
		super();
	}

	public PessoaFisica(String cpf) {
		super();
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PessoaFisica [cpf=");
		builder.append(cpf);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public void calcularImposto() {
		
		double renda = super.getRenda();
		System.out.println("Imposto a ser pago: ");
		
		if (renda <= 1400) {
			System.out.println("0");
		} else if (renda <= 2100) {
			System.out.println((renda * 0.1) + 100);
		} else if (renda <= 2800) {
			System.out.println((renda * 0.15) + 270);
		} else if (renda <= 3600) {
			System.out.println((renda * 0.25) + 500);
		} else {
			System.out.println((renda * 0.3) + 700);
		}

	}

}
