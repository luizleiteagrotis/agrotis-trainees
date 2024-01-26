package exercicios.aula24;

public class TesteLampada {

	public static void main(String[] args) {
		
		Lampada especifica = new Lampada ();
		
		especifica.marcaLampada = "Elgin";
		especifica.tipoLampada = "LED";
		especifica.corDaLuz = "Branca";
		especifica.voltagem = "Bivolt";
		especifica.vidaUtil = 25.000;
		especifica.potenciaLampada = 4.9;
		especifica.frequencia = "50/60Hz";
		
		System.out.println(especifica.marcaLampada);
		System.out.println(especifica.tipoLampada);
		System.out.println(especifica.corDaLuz);
		System.out.println(especifica.voltagem);
		System.out.println(especifica.vidaUtil);
		System.out.println(especifica.potenciaLampada);
		System.out.println(especifica.frequencia);

	}

}
