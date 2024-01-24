package exercicios.aula34.exercicio4;

public class Main {

	public static void main(String[] args) {

		mostrarTela(ConversaoDeUnidadesDeArea.metrosPes(1));

		mostrarTela(ConversaoDeUnidadesDeArea.pesCentimetros(1));
		
		mostrarTela(ConversaoDeUnidadesDeArea.milhasAcres(1));
		
		mostrarTela(ConversaoDeUnidadesDeArea.acrePes(1));


	}

	static void mostrarTela(double resp) {
		System.out.println(resp);
	}
}
