package exercicios.aula24;

public class TesteContato {

	public static void main(String[] args) {
		
		Contato contatos = new Contato ();
		
		contatos.email = "Rafael.net@hotmail.com.br";
		contatos.endereco = "Rua Rio xingu 275";
		contatos.nome = "WIllian";
		contatos.telefone = "41 996483268";
		
		System.out.println("O email e: " + contatos.email);
		System.out.println("O endereco e: " + contatos.endereco);
		System.out.println("O nome e: " + contatos.nome);
		System.out.println("O telefone e: " + contatos.telefone);

	}

}
