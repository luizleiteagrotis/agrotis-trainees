package exercicios.aula34.exercicio4;

public class ConversaoDeUnidadesDeArea {
	
	private ConversaoDeUnidadesDeArea() {}
	
	public static double converterMetroParaPe(double metros) {
		return 10.76 * metros;
	}
	
	public static double converterPeParaCentimetro(double pes) {
		return 929 * pes;
	}
	
	public static double converterMilhaParaAcre(double milhas) {
		return 640 * milhas;
	}
	
	public static double converterAcreParaPe(double acres) {
		return 43_560 * acres;
	}
}
