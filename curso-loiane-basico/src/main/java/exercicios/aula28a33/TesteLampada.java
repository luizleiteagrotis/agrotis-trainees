package exercicios.aula28a33;

public class TesteLampada {

	public static void main(String[] args) {
		
		Lampada lampada = new Lampada(null, null, null, null, 0, null, false);
		
		lampada.acenderLampada();
		
		lampada.mostraStatus();
		
		lampada.desligarLampada();
		
		lampada.mostraStatus();
		

	}
	}

