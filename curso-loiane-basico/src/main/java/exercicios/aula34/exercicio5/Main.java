package exercicios.aula34.exercicio5;

public class Main {

	public static void main(String[] args) {

		mostrarTela(ConversaoDeUnidadeDeVolume.litrosCentimetros(1));
		mostrarTela(ConversaoDeUnidadeDeVolume.metrosLitros(1));
		mostrarTela(ConversaoDeUnidadeDeVolume.metrosPes(1));
		mostrarTela(ConversaoDeUnidadeDeVolume.galaosPolegadas(1));
		mostrarTela(ConversaoDeUnidadeDeVolume.galaosLitros(1));
		
		
		
	}
	
	
	public static void mostrarTela(double num) {
		System.out.println(num);
	}

}
