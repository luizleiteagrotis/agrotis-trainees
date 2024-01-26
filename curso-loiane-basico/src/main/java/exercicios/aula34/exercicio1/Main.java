package exercicios.aula34.exercicio1;

public class Main {
	
	public static void main(String[] args) {
		mostrarNumeroinstancias();
		
		System.out.println();
		System.out.println("Criando instancias");
		Contador contador = new Contador();
		contador = new Contador();
		mostrarNumeroinstancias();
		
		System.out.println();
		System.out.println("Incrementando");
		Contador.incrementar();
		mostrarNumeroinstancias();
		
		System.out.println();
		System.out.println("Decrementando");
		Contador.decrementar();
		mostrarNumeroinstancias();
		
		System.out.println();
		System.out.println("Zerando");
		Contador.zerar();
		mostrarNumeroinstancias();
	}
	
	private static void mostrarNumeroinstancias() {
		System.out.println("Numero de instancias contador: " 
				+ Contador.getNumeroIntancias());
	}
}
