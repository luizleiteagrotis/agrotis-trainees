package exercicios.aula44a46.exercicio1e2e3.figurageometrica;

public abstract class FiguraGeometrica {
	
	private NomeFiguraGemotetrica nome;
	private CorFiguraGeometrica cor;
	
	public FiguraGeometrica(NomeFiguraGemotetrica nome, CorFiguraGeometrica cor) {
		this.nome = nome;
		this.cor = cor;
	}
	
	@Override
	public String toString() {
		return nome
				+ "\nCor: " + cor;
	}
}
