package exercicios.aula28a33;

public class Lampada {

	
	private String marcaLampada;
	private String tipoLampada;
	private String corDaLuz;
	private String voltagem;
	private double potenciaLampada;
	private String frequencia;
	private boolean ativa;
	
	
	public Lampada(String marcaLampada, String tipoLampada, String corDaLuz, String voltagem, double potenciaLampada,
			String frequencia, boolean ativa) {
		super();
		this.marcaLampada = marcaLampada;
		this.tipoLampada = tipoLampada;
		this.corDaLuz = corDaLuz;
		this.voltagem = voltagem;
		this.potenciaLampada = potenciaLampada;
		this.frequencia = frequencia;
		this.ativa = ativa;
	}
	
	
	public String getMarcaLampada() {
		return marcaLampada;
	}
	public void setMarcaLampada(String marcaLampada) {
		this.marcaLampada = marcaLampada;
	}
	public String getTipoLampada() {
		return tipoLampada;
	}
	public void setTipoLampada(String tipoLampada) {
		this.tipoLampada = tipoLampada;
	}
	public String getCorDaLuz() {
		return corDaLuz;
	}
	public void setCorDaLuz(String corDaLuz) {
		this.corDaLuz = corDaLuz;
	}
	public String getVoltagem() {
		return voltagem;
	}
	public void setVoltagem(String voltagem) {
		this.voltagem = voltagem;
	}
	public double getPotenciaLampada() {
		return potenciaLampada;
	}
	public void setPotenciaLampada(double potenciaLampada) {
		this.potenciaLampada = potenciaLampada;
	}
	public String getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}
	public boolean isAtiva() {
		return ativa;
	}
	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
	
	public void acenderLampada() {
		setAtiva(true);
	}
	
	public void desligarLampada() {
		setAtiva(false);
	}
	
	public void mostraStatus() {
		if(isAtiva()) {
			System.out.println("Lampada acesa!");
		}else {
			System.out.println("Lampada apagada!");
		}
	}

	
	

}
