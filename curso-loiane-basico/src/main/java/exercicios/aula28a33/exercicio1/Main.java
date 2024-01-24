package exercicios.aula28a33.exercicio1;

public class Main {

	public static void main(String[] args) {

		Lampada lampada = new Lampada("Oval", "Led", 110, 25.5, false);
		
		System.out.println(lampada.isLigada());
		
		lampada.ligarLampada();
		
		System.out.println(lampada.isLigada());
	}

}
