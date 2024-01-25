package exercicios.aula36a43.exercicio2;

public class ReceitaFederalTest {
	public static void main(String[] args) {
		ReceitaFederal federal = new ReceitaFederal();
		
		federal.setContribuinte("Anderson");
		federal.setPessoa(PessoaEnum.PESSOA_FISICA);
		federal.setRendaBruta(1450.0);
		System.out.println(federal);
		
		ReceitaFederal federal2 = new ReceitaFederal();
		
		federal2.setContribuinte("Gustavo");
		federal2.setPessoa(PessoaEnum.PESSOA_JURIDICA);
		federal2.setRendaBruta(5000.0);
		System.out.println(federal2);
		
	}
}
