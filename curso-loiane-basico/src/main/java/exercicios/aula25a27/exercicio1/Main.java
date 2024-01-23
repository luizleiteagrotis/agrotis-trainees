package exercicios.aula25a27.exercicio1;

public class Main {

	public static void main(String[] args) {
		
		Lampada lampada = new Lampada();
		
		lampada.ligarLampada();
		
		System.out.println(lampada.isLigada());
		
		lampada.desligarLampada();
		
		System.out.println(lampada.isLigada());
		
	}

}
