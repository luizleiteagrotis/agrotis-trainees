package exercicios.aula36a43;

public class ZoologicoTeste {

	public static void main(String[] args) {
		
		Animal camelo = new Animal("Camelo", "Amarelo", "Terra", 150, 2, 4);
		Mamifero urso = new Mamifero("Urso-do-canadá", "Vermelho", "Terra", 180, 0.5, 4, "Mel");
		Peixe tubarao = new Peixe("Tubarão", "Cinzento", "Mar", 300, 1.5, 0, "Barbatanas e cauda");
		
		System.out.println("Zoo:");
		System.out.println("------------------------------------");
		System.out.println(camelo.toString());
		System.out.println("------------------------------------");
		System.out.println(urso.toString());
		System.out.println("------------------------------------");
		System.out.println(tubarao.toString());
		System.out.println("------------------------------------");
	}

}
