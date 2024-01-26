package exercicios.aula36a43.exercicio2;

public class ReceitaFederalFactory {

	public static ReceitaFederal criarReceita(Contribuinte contribuinte, PessoaEnum pessoa, Double rendaBruta) {
		return new ReceitaFederal(contribuinte, pessoa, rendaBruta);
	}

}
