package exercicios.aula34.exercicio1;


public class TestClass {
	public static void main(String[] args) {

		Contador contador = new Contador();

		System.out.println(contador.retornaContador());

		contador.incementarContador();
		System.out.println(contador.retornaContador());

		contador.zerarContador();
		System.out.println(contador.retornaContador());

	}

}
