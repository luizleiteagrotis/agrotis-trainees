package exercicios.aula44a46;

public class Cilindro extends Figura3D{
	
	private double alturaCilindro;
	private double raioCilindro;

	public double getAlturaCilindro() {
		return alturaCilindro;
	}
	public void setAlturaCilindro(double alturaCilindro) {
		this.alturaCilindro = alturaCilindro;
	}
	public double getRaioCilindro() {
		return raioCilindro;
	}
	public void setRaioCilindro(double raioCilindro) {
		this.raioCilindro = raioCilindro;
	}
	@Override
	public double calcArea() {
		double  aB = 3.14 * raioCilindro * raioCilindro;
		double  aL = 2 * 3.14 * raioCilindro * alturaCilindro;
		double  aT = (2 * aB) + aL;	
		return aT;
	}
	@Override
	public double calcVolume() {
		
		return 3.14 * (raioCilindro * raioCilindro) * alturaCilindro;
	}
	
	
	
	

}
