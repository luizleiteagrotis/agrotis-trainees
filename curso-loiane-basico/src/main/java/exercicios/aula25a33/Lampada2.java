package exercicios.aula25a33;

import java.util.Scanner;

public class Lampada2 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Lampada lampada = new Lampada();
		
//		lampada.modelo = "Philips 2030";
//		lampada.tensao = "wtts";
//		lampada.potencia = 120;
//		lampada.cor = "branca";
//		lampada.tipoLuz = "fluorescente";
//		lampada.garantiaMeses = 12;
//		//lampada.tipos[1] = "0";
//		lampada.tipoAbajur = false;
//		
//		System.out.println("Modelo: " + lampada.modelo);
//		System.out.println("Tensao: " + lampada.tensao);
//		System.out.println("Ṕotencia: " + lampada.potencia);
//		System.out.println("Cor: " + lampada.cor);
//		System.out.println("Tipo da Luz: " + lampada.tipoLuz);
//		System.out.println("Garantia: " + lampada.garantiaMeses + " meses");
		//System.out.println("Tipos: " + lampada.tipos);
		System.out.println("Digita 1 para ligar a lampada: ");
		System.out.println("Digite 2 para desligar a lampada: ");
		lampada.opcao = sc.nextInt();
		
		if (lampada.ligaDesliga = true) {
			lampada.opcao = 1;
			lampada.tipoLuz = "A lampada foi ligada";
			System.out.println(lampada.tipoLuz);
		} else {
			lampada.opcao = 2;
			lampada.tipoLuz = "A lampada foi desligada.";
			System.out.println(lampada.tipoLuz);
		}
		
		if (lampada.tipoAbajur == false) {
			lampada.tipoAbajur2 = "Não";
		}
		System.out.println("Tipo Abajur: " + lampada.tipoAbajur2);
		
		
	
	}	

}