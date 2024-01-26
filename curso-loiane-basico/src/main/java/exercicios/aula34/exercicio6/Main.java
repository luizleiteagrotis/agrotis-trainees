package exercicios.aula34.exercicio6;

public class Main {
	
	public static void main(String[] args) {
		double minutos = 1;
		double horas = 1;
		double dias = 1;
		double semanas = 1;
		double meses = 1;
		double anos = 1;
		
		System.out.println(minutos 
				   		   + " minutos = " 
				   		   + ConversaoDeUnidadesDeTempo.converterMinutoParaSegundo(minutos) 
				   		   + " segundos");
		System.out.println(horas
		   		    	   + " horas = " 
		   		    	   + ConversaoDeUnidadesDeTempo.converterHoraParaMinuto(horas) 
		   		    	   + " minutos");
		System.out.println(dias 
		   		   		   + " dias = " 
		   		   		   + ConversaoDeUnidadesDeTempo.converterDiaParaHora(dias) 
		   		   		   + " horas");
		System.out.println(semanas
		   		           + " semanas = " 
		   		           + ConversaoDeUnidadesDeTempo.converterSemanaParaDia(semanas) 
		   		           + " dias");
		System.out.println(meses
		   		           + " meses = " 
		   		           + ConversaoDeUnidadesDeTempo.converterMesParaDia(meses) 
		   		           + " dias");
		System.out.println(anos 
		   		     	   + " anos = " 
		   		     	   + ConversaoDeUnidadesDeTempo.converterAnoParaDia(anos) 
		   		     	   + " dias");
	}
}
