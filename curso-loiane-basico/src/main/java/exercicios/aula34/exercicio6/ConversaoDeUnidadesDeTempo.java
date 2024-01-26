package exercicios.aula34.exercicio6;

public class ConversaoDeUnidadesDeTempo {
	
	private ConversaoDeUnidadesDeTempo() {}
	
	public static double converterMinutoParaSegundo(double minutos) {
		return minutos * 60;
	}
	
	public static double converterHoraParaMinuto(double horas) {
		return horas * 60;
	}
	
	public static double converterDiaParaHora(double dias) {
		return dias * 24;
	}
	
	public static double converterSemanaParaDia(double semanas) {
		return semanas * 7;
	}
	
	public static double converterMesParaDia(double meses) {
		return meses * 30;
	}
	
	public static double converterAnoParaDia(double anos) {
		return anos * 365.25;
	}
}
