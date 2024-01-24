package exercicios.aula28a33.exercicio1;

public class Lampada {

	private String formato;
	private String tipo;
	private int potencia;
	private double valor;
	private boolean ligada;
	
	public Lampada(){}
	
	public Lampada(String formato, String tipo, int potencia, double valor, boolean ligada) {
		super();
		this.formato = formato;
		this.tipo = tipo;
		this.potencia = potencia;
		this.valor = valor;
		this.ligada = ligada;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isLigada() {
		return ligada;
	}

	public void setLigada(boolean isLigada) {
		this.ligada = isLigada;
	}

	public void ligarLampada() {
		setLigada(true);
	}

	public void desligarLampada() {
		setLigada(false);
	}

}
