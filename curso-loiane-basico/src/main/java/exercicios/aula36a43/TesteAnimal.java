package exercicios.aula36a43;

public class TesteAnimal extends Animal{

	public static void main(String[] args) {
		
		Mamifero camelo = new Mamifero();
		camelo.setNome("Camelo do Parana");
		camelo.setCor("Amarelo");
		camelo.setComprimento(150);
		camelo.setAmbiente("Terra");
		camelo.setVelocidade(2.0);
		Peixe tubarao = new Peixe();
		tubarao.setNome("Tubarao das Aguas");
		tubarao.setComprimento(300);
		tubarao.setVelocidade(1.5);
		tubarao.setCor("Cinzento");
		Mamifero ursoDoCanada = new Mamifero();
		ursoDoCanada.setNome("Urso Do Canada");
		ursoDoCanada.setComprimento(180);
		ursoDoCanada.setCor("Vermelho");
		ursoDoCanada.setVelocidade(0.5);
		ursoDoCanada.setAlimento("Mel");
		ursoDoCanada.setAmbiente("Terra");
		
		
		System.out.println("Jardim Zoologico: ");
		camelo.imprimirAnimal();
		
		tubarao.imprimirAnimal();
		
		ursoDoCanada.imprimirAnimal();

		//System.out.println(camelo);
		//System.out.println(tubarao);
		//System.out.println(ursoDoCanada);
	
	}
	}
	

