package exercicios.aula36a43;

public class PessoaFisica extends Contribuinte{
	
	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
	@Override
	public String toString() {
		String str = super.toString();
		str += " CPF " + cpf;
		str += " TOTAL VALOR PENDENTE IMPOSTO: " + calculoImpost();
		
				
	 return str;
	}

	
	
	
	
	@Override
	public double calculoImpost() {
		
		
		if (this.getRenda() <= 1400) {
		
		return 0;
		} 
		if (this.getRenda() > 1400 && this.getRenda() <=2100) {
		return (this.getRenda() * 0.1 - 100);
	}
		if (this.getRenda() > 2100 && this.getRenda() <=2800) {
			return (this.getRenda() * 0.15 - 270);
 }   if (this.getRenda() > 2800 && this.getRenda() <=3600) {
		return (this.getRenda() * 0.25 - 500);
			
 }  if (this.getRenda() > 3600) {
		return (this.getRenda() * 0.30 - 700);
	
	}
return 0;
 }
}
