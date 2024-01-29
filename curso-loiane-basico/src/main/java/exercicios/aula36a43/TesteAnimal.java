package exercicios.aula36a43;

public class TesteAnimal {

	public static void main(String[] args) {
				
		Animal camelo = new Animal("Camelo", 150, 0, "Amarelo", "Terra", 2);
		
		Animal tubarao = new Peixe();
		tubarao.setNome("Tubarão");
		tubarao.setComprimento(300);
		tubarao.setVelocidade(1.5);
		
		Animal urso = new Mamifero("Mel");		
		urso.setNome("Urso-do-Canadá");
		urso.setComprimento(180);
		urso.setCor("Vermelho");
		urso.setVelocidade(0.5);
		
		System.out.println("Zoo:");
		System.out.println("--------------------");
		System.out.println(camelo);
		System.out.println("--------------------");
		System.out.println(tubarao);
		System.out.println("--------------------");
		System.out.println(urso);
		System.out.println("--------------------");
		
	}
}
