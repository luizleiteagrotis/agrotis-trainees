package exercicios.aula34;

public class ConversaoDeUnidadesDeTempo {
	
	public static int converteMinutosParaSegundos(int minutos) {
		return minutos*60;
	}
	
	public static int converteHorasEmMinutos(int horas) {
		return horas*60;
	}
	
	public static int converteDiasEmHoras(int dias) {
		return dias*24;
	}
	
	public static int converteSemanasEmDias(int semanas) {
		return semanas*7;
	}
	
	public static int converteMesesEmDias(int meses) {
		return meses*30;
	}
	
	public static double converteAnosEmDias(int anos) {
		return anos*365.25;
	}
}
