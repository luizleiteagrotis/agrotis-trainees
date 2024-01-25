package exercicios.aula24;

public class Lampada2 {
	
	public static void main(String[] args) {

		Lampada lampada = new Lampada();
		
		lampada.modelo = "Philips 2030";
		lampada.tensao = "wtts";
		lampada.potencia = 120;
		lampada.cor = "branca";
		lampada.tipoLuz = "fluorescente";
		lampada.garantiaMeses = 12;
		//lampada.tipos[1] = "0";
		lampada.tipoAbajur = false;
		
		System.out.println("Modelo: " + lampada.modelo);
		System.out.println("Tensao: " + lampada.tensao);
		System.out.println("Ṕotencia: " + lampada.potencia);
		System.out.println("Cor: " + lampada.cor);
		System.out.println("Tipo da Luz: " + lampada.tipoLuz);
		System.out.println("Garantia: " + lampada.garantiaMeses + " meses");
		//System.out.println("Tipos: " + lampada.tipos);
		if (lampada.tipoAbajur == false) {
			lampada.tipoAbajur2 = "Não";
		}
		System.out.println("Tipo Abajur: " + lampada.tipoAbajur2);
		
	
	}	

}