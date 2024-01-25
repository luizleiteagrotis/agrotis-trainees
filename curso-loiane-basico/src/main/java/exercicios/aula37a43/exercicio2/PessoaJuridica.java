package exercicios.aula37a43.exercicio2;

public class PessoaJuridica extends Contribuinte {

	private String cnpj;

	public PessoaJuridica() {
		super();
	}

	public PessoaJuridica(String cnpj) {
		super();
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PessoaJuridica [cnpj=");
		builder.append(cnpj);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public void calcularImposto() {
		System.out.println("Imposto a ser pago: ");
		System.out.println(super.getRenda() * 0.1);
	}

}
