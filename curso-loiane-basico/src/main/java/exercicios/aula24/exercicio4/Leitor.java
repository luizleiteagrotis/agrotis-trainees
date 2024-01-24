package exercicios.aula24.exercicio4;

public class Leitor {

	private String nome;
	private String carteirinhaDeLeitor;

	public Leitor() {
	}

	public Leitor(String nome, String carteirinhaDeLeitor) {
		this.nome = nome;
		this.carteirinhaDeLeitor = carteirinhaDeLeitor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCarteirinhaDeLeitor() {
		return carteirinhaDeLeitor;
	}

	public void setCarteirinhaDeLeitor(String carteirinhaDeLeitor) {
		this.carteirinhaDeLeitor = carteirinhaDeLeitor;
	}

	@Override
	public String toString() {
		return "Leitor [nome=" + nome + ", carteirinhaDeLeitor=" + carteirinhaDeLeitor + "]";
	}

}
