package exercicios.aula34.exercicio5;

public class ConversaoDeUnidadesDeVolume {

	private static final double UMLITROEMCENTIMETROSCUBICOS = 1000;
	private static final double METROSCUBICOSEMLITROS = 1000;
	private static final double METROCUBICOSPARAPEHSCUBICOS = 35.32;
	private static final double GALAOAMERICANOPARAPOLEGADASCUBICAS = 231;
	private static final double GALAOAMERICANOPARALITROS = 231;

	private static double litrosParaCMCubicos(double valor) {
		return valor * UMLITROEMCENTIMETROSCUBICOS;
	}

	private static double metroCubicoParaLitros(double valor) {
		return valor * METROSCUBICOSEMLITROS;

	}

	private static double metroCubicoParaPehsCubicos(double valor) {
		return valor * METROCUBICOSPARAPEHSCUBICOS;

	}

	private static double galaoAmericadoParaPolegadaCubica(double valor) {
		return valor * GALAOAMERICANOPARAPOLEGADASCUBICAS;

	}

	private static double galaoAmericanoParaLitros(double valor) {
		return valor * GALAOAMERICANOPARALITROS;

	}

}
