package exercicios.aula36a43;

public class PessoaJuridica extends Contribuinte{
	
	private String cnpj;
	
	@Override
	public double calculoImpost() {
		return this.getRenda()/100 * 10;
	}
	

	@Override
	public String toString() {
	 String str = "PJ: ";
	 str += super.toString();
	 str += "TOTAL IMPOSTO PENDENTE: " + calculoImpost();
	 str += "CNPJ: " + cnpj;
	 return str;
	}


	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}



	

}
