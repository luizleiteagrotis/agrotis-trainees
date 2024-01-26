package exercicios.aula53a56;

public enum OperacoesMatematica {

	SOMAR("+") {
		@Override
		public double executarOperacao(double x, double y) {
			return x + y;
		}
	},
	SUBTRAIR("-") {
		@Override
		public double executarOperacao(double x, double y) {
			return x - y;
		}
	},
	MULTIPLICAR("*") {
		@Override
		public double executarOperacao(double x, double y) {
			return x * y;
		}
	},
	DIVIDIR("/") {
		@Override
		public double executarOperacao(double x, double y) {
			return x / y;
		}
	};

	private String simbolo;

	private OperacoesMatematica(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getSimbolo() {
		return simbolo;
	}

	@Override
	public String toString() {
		return this.getSimbolo();
	}

	public abstract double executarOperacao(double x, double y);

}
