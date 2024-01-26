package exercicios.aula36a43.exercicio2;

public class ReceitaFederalTest {
	public static void main(String[] args) {
		Contribuinte contribuinte1 = ContribuinteFactory.criarContato("Anderson");
		Contribuinte contribuinte2 = ContribuinteFactory.criarContato("Gustavo");
		Contribuinte contribuinte3 = ContribuinteFactory.criarContato("Souza");
		Contribuinte contribuinte4 = ContribuinteFactory.criarContato("Linkin");
		Contribuinte contribuinte5 = ContribuinteFactory.criarContato("Park");
		
		ReceitaFederal federal1 = ReceitaFederalFactory.criarReceita(contribuinte1, PessoaEnum.PESSOA_FISICA, 1450.0);
		ReceitaFederal federal2 = ReceitaFederalFactory.criarReceita(contribuinte2, PessoaEnum.PESSOA_JURIDICA, 50000.0);
		ReceitaFederal federal3 = ReceitaFederalFactory.criarReceita(contribuinte3, PessoaEnum.PESSOA_FISICA, 2500.0);
		ReceitaFederal federal4 = ReceitaFederalFactory.criarReceita(contribuinte4, PessoaEnum.PESSOA_JURIDICA, 100000.0);
		ReceitaFederal federal5 = ReceitaFederalFactory.criarReceita(contribuinte5, PessoaEnum.PESSOA_FISICA, 2810.0);

		System.out.println(federal1);
		System.out.println(federal2);
		System.out.println(federal3);
		System.out.println(federal4);
		System.out.println(federal5);
		
		
	}
}
