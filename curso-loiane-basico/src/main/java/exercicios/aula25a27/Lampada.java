package exercicios.aula25a27;

public class Lampada {

	String marcaLampada;
	String tipoLampada;
	String corDaLuz;
	String voltagem;
	double potenciaLampada;
	String frequencia;
	boolean ativa;
	
	void acenderLampada() {
		ativa = true;
	}
	
	void desligarLampada() {
		ativa = false;
	}
	
}
