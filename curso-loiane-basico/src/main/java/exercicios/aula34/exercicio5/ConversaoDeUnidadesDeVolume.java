package exercicios.aula34.exercicio5;

public class ConversaoDeUnidadesDeVolume {
	
	private ConversaoDeUnidadesDeVolume() {}
	
	public static double converterLitroParaCentimetro(double litros) {
		return 1_000 * litros;
	}
	
	public static double converterMetroParaLitro(double metros) {
		return 1_000 * metros;
	}
	
	public static double converterMetroParaPe(double metros) {
		return 35.32 * metros;
	}
	
	public static double converterGalaoParaPolegada(double galoes) {
		return 231 * galoes;
	}
	
	public static double converterGalaoParaLitro(double galoes) {
		return 3_785 * galoes;
	}
}
