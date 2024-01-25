package exercicios.aula36a43.exercicio3;

public class AnimalTest {

	public static void main(String[] args) {
		Animal camelo = new Mamifero("Camelo", 150, "Amarelo", 2);
		Animal peixe = new Peixe("TuTuTubarão", 300, 1.5);
		Animal ursoDoCanada = new Urso("Urso do Canadá", 180, "Vermelho", 0.5);
		System.out.println(camelo);
		System.out.println(peixe);
		System.out.println(ursoDoCanada);

	}

}
