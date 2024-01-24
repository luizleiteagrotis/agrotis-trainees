package exercicios.aula34.exercicio4;

public class ConversaoDeUnidadesDeArea {

	private static final double VALORDOMETROPARAPES = 10.76;
	private static final double VALORDOPEQUADRADOEMCENTIMETROS = 929;
	private static final double VALORDAMILHAPARAACRE = 640;
	private static final double VALORDOACREEMPEHS = 43.56;

	public static double conversorMetroParaPeh(double valor) {
		return valor * VALORDOMETROPARAPES;

	}

	public static double pesParaCentimentros(double valor) {
		return valor * VALORDOPEQUADRADOEMCENTIMETROS;
	}

	public static double milhaParaAcres(double valor) {
		return valor * VALORDAMILHAPARAACRE;

	}

	public static double acreParaPes(double valor) {
		return valor * VALORDOACREEMPEHS;
	}

}
