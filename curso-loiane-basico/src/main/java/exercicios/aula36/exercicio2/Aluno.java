package exercicios.aula36.exercicio2;

import java.util.Arrays;

public class Aluno {

	private String nome;
	private String matricula;
	private double[] notas = new double[4];

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public double[] getNotas() {
		return notas;
	}

	public void setNotas(double[] notas) {
		this.notas = notas;
	}

	public double calcularMedia() {
		double soma = 0;
		System.out.println(nome);
		for (int i = 0; i < notas.length; i++) {
			soma += notas[i];
		}
		return soma/4;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Aluno [nome=");
		builder.append(nome);
		builder.append(", matricula=");
		builder.append(matricula);
		builder.append(", notas=");
		builder.append(Arrays.toString(notas));
		builder.append("]");
		return builder.toString();
	}

}
