package exercicios.aula24.exercicio4;

import exercicios.aula24.exercicio2.Livro;

public class LivroDeBiblioteca extends Livro {

	private boolean disponivelParaEmprestimo;
	private Leitor leitor;

	public LivroDeBiblioteca() {
	}

	public LivroDeBiblioteca(Leitor leitor) {
		this.disponivelParaEmprestimo = true;
		this.leitor = leitor;
	}

	public Leitor getLeitor() {
		return leitor;
	}

	public void setLeitor(Leitor leitor) {
		this.leitor = leitor;
	}

	public boolean isDisponivelParaEmprestimo() {
		return disponivelParaEmprestimo;
	}

	public void setDisponivelParaEmprestimo(boolean disponivelParaEmprestimo) {
		this.disponivelParaEmprestimo = disponivelParaEmprestimo;
	}

	public void realizaEmprestimoDeLivro(LivroDeBiblioteca livro, Leitor leitor) {
		if (!livroDisponivelParaLocacao()) {
			this.disponivelParaEmprestimo = false;
			System.out.println("Emprestimo realizado");
		}

	}

	private boolean livroDisponivelParaLocacao() {
		if (disponivelParaEmprestimo) {
			System.out.println("Livro não disponivel para emprestimo.");
			return true;
		}
		return false;

	}

}
