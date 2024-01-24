package exercicios.aula25a27.exercicio3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Disciplina {
	
	private String nome;
	private List<Double> notas;
	
	public Disciplina(String nome) {
		this.nome = nome;
		notas = new ArrayList<>();
	}
	
	public String getNome() {
		return nome;
	}
	
	public List<Double> getNotas() {
		return Collections.unmodifiableList(notas);
	}
	
	public void addNota(double nota) {
		notas.add(nota);
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
}
