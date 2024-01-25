package exercicios.aula37a43.exercicio2;

public class Main {

	public static void main(String[] args) {

		Contribuinte pessoaFisica1 = new PessoaFisica("1234");
		pessoaFisica1.setNome("Rafael");
		pessoaFisica1.setRenda(4000.40);
		Contribuinte pessoaFisica2 = new PessoaFisica("4321");
		pessoaFisica2.setNome("Vitor");
		pessoaFisica2.setRenda(1200);
		Contribuinte pessoaFisica3 = new PessoaFisica("9999");
		pessoaFisica3.setNome("Kayo");
		pessoaFisica3.setRenda(2500.70);
		
		Contribuinte pessoaJuridica1 = new PessoaJuridica("088");
		pessoaJuridica1.setNome("Cabanas");
		pessoaJuridica1.setRenda(7000);
		Contribuinte pessoaJuridica2 = new PessoaJuridica("077");		
		pessoaJuridica2.setNome("Agrotis");
		pessoaJuridica2.setRenda(13000);
		Contribuinte pessoaJuridica3 = new PessoaJuridica("066");		
		pessoaJuridica3.setNome("VIVO");
		pessoaJuridica3.setRenda(30000);
		
		Contribuinte[] contribuintes = new Contribuinte[6];
		
		contribuintes[0] = pessoaFisica1;
		contribuintes[1] = pessoaFisica2;
		contribuintes[2] = pessoaFisica3;
		
		contribuintes[3] = pessoaJuridica1;
		contribuintes[4] = pessoaJuridica2;
		contribuintes[5] = pessoaJuridica3;
		
		for (Contribuinte contribuinte : contribuintes) {
			System.out.println(contribuinte);
			contribuinte.calcularImposto();
			System.out.println();
		}
		
	}
}
