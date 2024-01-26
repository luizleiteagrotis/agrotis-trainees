package exercicios.aula44a46;

public class Piramide extends Figura3D{
	
	private double alturaPiramide;
	private double ladoBasePiramide;
	private double apotema;
	private Figura2D basePiramide;
	private int poligonos;
	
	
	public int getPoligonos() {
		return poligonos;
	}
	public void setPoligonos(int poligonos) {
		this.poligonos = poligonos;
	}
	@Override
	public double calcArea() {
		if (basePiramide != null) {
		return (poligonos * ladoBasePiramide * apotema) / 2 + basePiramide.calcArea();
	}
		return 0;
}
	@Override
	public double calcVolume() {
		if (basePiramide != null) {
		return (basePiramide.calcArea() * alturaPiramide)/3;
		}
		return 0;
	}
	public double getApotema() {
		return apotema;
	}
	public void setApotema(double apotema) {
		this.apotema = apotema;
	}
	public double getLadoBasePiramide() {
		return ladoBasePiramide;
	}
	public void setLadoBasePiramide(double ladoBasePiramide) {
		this.ladoBasePiramide = ladoBasePiramide;
	}
	public double getAlturaPiramide() {
		return alturaPiramide;
	}
	public void setAlturaPiramide(double alturaPiramide) {
		this.alturaPiramide = alturaPiramide;
	}
	public Figura2D getBasePiramide() {
		return basePiramide;
	}
	public void setBasePiramide(Figura2D basePiramide) {
		this.basePiramide = basePiramide;
	}
	
	
	
	

}
