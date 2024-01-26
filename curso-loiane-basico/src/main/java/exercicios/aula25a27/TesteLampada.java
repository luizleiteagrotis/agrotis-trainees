package exercicios.aula25a27;

public class TesteLampada {

	public static void main(String[] args) {
		
		Lampada lampada = new Lampada ();
		
		lampada.acenderLampada();
		
		if (lampada.ativa) {
			System.out.println("a lampada se encontra no momento: Ligada!");
		}else {
		System.out.println("A lampada se encontra no momento: Desligada!");
		
		}
		lampada.desligarLampada();
		
		if(lampada.ativa) {
			System.out.println("a lampada se encontra no momento: Ligada!");
		}else {
			System.out.println("a lampada se encontra no momento: Desligada!");
		}
	}
	
}
