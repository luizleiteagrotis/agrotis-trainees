package exercicios.aula37a43.exercicio3;

public class Main {

	public static void main(String[] args) {
		
		Animal camelo = new Animal("Camelo", 150, "Amarelo", "Terra", 2);
		
		Animal tubarao = new Peixe("Barbatanas e cauda");
		tubarao.setNome("Tubarão");
		tubarao.setComprimento(300);
		tubarao.setVelocidade(1.5);
		
		Animal urso = new Mamifero("Mel");		
		urso.setNome("Urso-do-Canadá");
		urso.setComprimento(180);
		urso.setCor("Vermelho");
		urso.setVelocidade(0.5);
		
		System.out.println("ZOO");
		System.out.println("--------------------");
		System.out.println(camelo);
		System.out.println("--------------------");
		System.out.println(tubarao);
		System.out.println("--------------------");
		System.out.println(urso);
		System.out.println("--------------------");
		
	}
}
