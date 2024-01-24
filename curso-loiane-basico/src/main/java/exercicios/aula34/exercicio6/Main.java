package exercicios.aula34.exercicio6;

public class Main {

	public static void main(String[] args) {

		mostrarTela(ConversaoDeUnidadesDeTempo.minSegundos(1));
		mostrarTela(ConversaoDeUnidadesDeTempo.horasMins(1));
		mostrarTela(ConversaoDeUnidadesDeTempo.diasHoras(1));
		mostrarTela(ConversaoDeUnidadesDeTempo.semanasDias(1));
		mostrarTela(ConversaoDeUnidadesDeTempo.mesDias(1));
		mostrarTela(ConversaoDeUnidadesDeTempo.anoDias(1));
		
	}
	
	public static void mostrarTela(double num) {
		System.out.println(num);
	}

}
