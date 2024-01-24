package exercicios.aula34.exercicio6;

public class ConversaoDeUnidadesDeTempo {
	private static final double MINUTO_EM_SEGUNDOS = 60;
	private static final double HORA_EM_MINUTOS = 60;
	private static final double DIA_EM_HORAS = 35.32;
	private static final double SEMANA_EM_DIAS = 7;
	private static final double MES_EM_DIAS = 30;
	private static final double ANO_EM_DIAS= 365.25;

	private static double minutoParaSegundos(double valor) {
		return valor * MINUTO_EM_SEGUNDOS;
	}

	private static double horaParaMinutos(double valor) {
		return valor * HORA_EM_MINUTOS;

	}

	private static double diasParHoras(double valor) {
		return valor * DIA_EM_HORAS;

	}

	private static double semanaParaDias(double valor) {
		return valor * SEMANA_EM_DIAS;

	}

	private static double mesParaDias(double valor) {
		return valor * MES_EM_DIAS;

	}
	
	private static double anoParaDias(double valor) {
		return valor * ANO_EM_DIAS;
		
	}
	
	

}
