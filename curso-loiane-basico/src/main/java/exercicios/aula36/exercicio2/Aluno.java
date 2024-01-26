package exercicios.aula36.exercicio2;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
	
	private String nome;
	private String matricula;
	private List<Double> notas;
	private final int QUANTIDADE_NOTAS;
	
	public Aluno(String nome, String matricula) {
		this.nome = nome;
		this.matricula = matricula;
		notas = new ArrayList<>();
		QUANTIDADE_NOTAS = 4;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getQuantidadeNotas() {
		return QUANTIDADE_NOTAS;
	}
	
	public void adicionar(double nota) {
		if (notas.size() < QUANTIDADE_NOTAS) {
			notas.add(nota);
		}
	}
	
	public double calcularMedia() {
		return calcularSoma() / notas.size();
	}
	
	private double calcularSoma() {
		double soma = 0;
		for (int i = 0; i < notas.size(); i++) {
			soma += notas.get(i);
		}
		return soma;
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + "\tMatricula: " + matricula + "\tNotas: " + notas;
	}
}
