package exercicios.aula34.exercicio5;

public class Main {

	public static void main(String[] args) {
		double litros = 1;
		double metros = 1;
		double galoes = 1;
				
		System.out.println(litros 
						   + " litros = " 
						   + ConversaoDeUnidadesDeVolume.converterLitroParaCentimetro(litros) 
						   + " centimetros");
		System.out.println(metros 
				   		   + " metros = " 
				           + ConversaoDeUnidadesDeVolume.converterMetroParaLitro(metros) 
				           + " litros");
		System.out.println(metros 
				           + " metros = " 
				           + ConversaoDeUnidadesDeVolume.converterMetroParaPe(metros) 
				           + " pes");
		System.out.println(galoes 
				   	  	   + " galoes = " 
				   	  	   + ConversaoDeUnidadesDeVolume.converterGalaoParaPolegada(galoes) 
				   	  	   + " polegadas");
		System.out.println(galoes 
				           + " galoes = " 
				           + ConversaoDeUnidadesDeVolume.converterGalaoParaLitro(galoes) 
				           + " litros");
	}
}
