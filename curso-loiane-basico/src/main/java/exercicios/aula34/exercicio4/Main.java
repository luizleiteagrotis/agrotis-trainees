package exercicios.aula34.exercicio4;

public class Main {
	
	public static void main(String[] args) {
		double metros = 1.5;
		double pes = 2;
		double milhas = 2;
		double acres = 2;
		
		System.out.println(metros 
						   + " metros = " 
						   + ConversaoDeUnidadesDeArea.converterMetroParaPe(metros) 
						   + " pes");
		System.out.println(pes
						   + " pes = "
						   + ConversaoDeUnidadesDeArea.converterPeParaCentimetro(pes)
						   + " centimetros");
		System.out.println(milhas
						   + " milhas = "
						   + ConversaoDeUnidadesDeArea.converterMilhaParaAcre(milhas)
						   + " acres");
		System.out.println(acres
						   + " acres = "
						   + ConversaoDeUnidadesDeArea.converterAcreParaPe(acres)
						   + " pes");
	}
}
