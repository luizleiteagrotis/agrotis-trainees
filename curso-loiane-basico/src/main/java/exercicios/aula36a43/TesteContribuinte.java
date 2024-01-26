package exercicios.aula36a43;

import java.util.Set;

public class TesteContribuinte {

	public static void main(String[] args) {
		
		Contribuinte[] cont = new Contribuinte[6];
		
		PessoaFisica primeiraPessoa = new PessoaFisica();
		primeiraPessoa.setNome("Primeira pessoa Contribuinte " );
		primeiraPessoa.setRenda(2155);
		primeiraPessoa.setCpf("033.349.289-42");
		
		PessoaJuridica segundaPessoa = new PessoaJuridica();
		segundaPessoa.setNome("Segunda pessoa Contribuinte ");
		segundaPessoa.setRenda(4155);
		segundaPessoa.setCnpj("61.454.847/001-30");
		
		PessoaFisica terceiraPessoa = new PessoaFisica();
		terceiraPessoa.setNome("Terceira pessoa Contribuinte " );
		terceiraPessoa.setRenda(6155);
		terceiraPessoa.setCpf("780.992.360-92");
		
		PessoaJuridica quartaPessoa = new PessoaJuridica();
		quartaPessoa.setNome("Quarta pessoa Contribuinte " );
		quartaPessoa.setRenda(8155);
		quartaPessoa.setCnpj("44.266.798/0001-93");
		
		
		PessoaFisica quintaPessoa = new PessoaFisica();
		quintaPessoa.setNome("Quinta pessoa Contribuinte " );
		quintaPessoa.setRenda(10155);
		quintaPessoa.setCpf("094.987.130-30");
		
		PessoaJuridica sextaPessoa = new PessoaJuridica();
		sextaPessoa.setNome("Sexta pessoa Contribuinte " );
		sextaPessoa.setRenda(12155);
		sextaPessoa.setCnpj("44.266.798/0001-93");
		
		cont[0] = primeiraPessoa;
		cont[1] = segundaPessoa;
		cont[2] = terceiraPessoa;
		cont[3] = quartaPessoa;
		cont[4] = quintaPessoa;
		cont[5] = sextaPessoa;
		
		for(Contribuinte contribuintes : cont) {
			System.out.println(contribuintes.toString());
		}
		
	}


}
