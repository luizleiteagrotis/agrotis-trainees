package exercicios.aula36a43.exercicio2;

import exercicios.aula47a52.exercicio1.Contato;

public class ContribuinteFactory {
	
    public static Contribuinte criarContato(String nome) {
        return new Contribuinte(nome);
    }
	

}
