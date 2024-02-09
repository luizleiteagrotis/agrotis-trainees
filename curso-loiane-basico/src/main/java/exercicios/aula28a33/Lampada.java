
package exercicios.aula28a33;

public class Lampada {

	private String marca;
	private int potencia;
	private int tensao;
	private int tempCor;
	private int vidaUtil;
	private boolean acesa;
	
	public Lampada() {};
	
	public Lampada(String marca, int potencia, int tensao, int tempCor, int vidaUtil, boolean acesa) {
		super();
		this.marca = marca;
		this.potencia = potencia;
		this.tensao = tensao;
		this.tempCor = tempCor;
		this.vidaUtil = vidaUtil;
		this.acesa = acesa;
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public int getTensao() {
		return tensao;
	}

	public void setTensao(int tensao) {
		this.tensao = tensao;
	}

	public int getTempCor() {
		return tempCor;
	}

	public void setTempCor(int tempCor) {
		this.tempCor = tempCor;
	}

	public int getVidaUtil() {
		return vidaUtil;
	}

	public void setVidaUtil(int vidaUtil) {
		this.vidaUtil = vidaUtil;
	}

	public boolean isAcesa() {
		return acesa;
	}

	public void setAcesa(boolean acesa) {
		this.acesa = acesa;
	}
	
	public void ligarLampada(){
		acesa = true;
	}
	
	public void desligarLampada(){
		acesa = false;
	}
}
