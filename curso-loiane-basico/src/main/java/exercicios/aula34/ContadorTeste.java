package exercicios.aula34;

public class ContadorTeste {

	public static void main(String[] args) {
		Contador contador = new Contador();
		Contador contador1 = new Contador();
		Contador contador2 = new Contador();
		
		System.out.println("Valor do contador: " + contador.getCount());
		
		contador.incrementar();
		
		System.out.println("Valor do contador: " + contador.getCount());
		
		System.out.println("Valor do contador (acesso estático): " + Contador.getCount());
		
		contador.zerar();
		
		System.out.println("Valor do contador: " + contador.getCount());
		System.out.println("Valor do contador: " + contador1.getCount());
		System.out.println("Valor do contador: " + contador2.getCount());
		System.out.println("Valor do contador (acesso estático): " + Contador.getCount());
	}

}
