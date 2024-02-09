
package exercicios.aula25a27;

public class Lampada {
	
	public String marca;
	public int potencia;
	public int tensao;
	public int tempCor;
	public int vidaUtil;
	public boolean acesa;
	
	public void ligarLampada(){
		acesa = true;
	}
	
	public void desligarLampada(){
		acesa = false;
	}
}
