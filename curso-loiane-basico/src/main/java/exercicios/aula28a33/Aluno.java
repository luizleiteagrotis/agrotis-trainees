package exercicios.aula28a33;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Aluno {

	private String nome;
	private Integer matricula;
	private List<String> cursosMatriculados = new ArrayList<String>();
	private Map<String, Integer> notasAluno = new HashMap<String, Integer>();
	
	
	public Aluno() {
		super();
	}


	public Aluno(String nome, Integer matricula, List<String> cursosMatriculados, Map<String, Integer> notasAluno) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.cursosMatriculados = cursosMatriculados;
		this.notasAluno = notasAluno;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Integer getMatricula() {
		return matricula;
	}


	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}


	public List<String> getCursosMatriculados() {
		return cursosMatriculados;
	}


	public void setCursosMatriculados(List<String> cursosMatriculados) {
		this.cursosMatriculados = cursosMatriculados;
	}


	public Map<String, Integer> getNotasAluno() {
		return notasAluno;
	}


	public void setNotasAluno(Map<String, Integer> notasAluno) {
		this.notasAluno = notasAluno;
	}
	
	public void materiasCursando(List<String> materias) {
		cursosMatriculados.addAll(materias);
	}
	
	public void adicionaNota(String curso, int nota) {
		notasAluno.put(curso, nota);
	}
	
	public int gerarNumeroDeMatricula() {
		Random random = new Random();
		return 10000 + random.nextInt(90000);
	}
	
	public double calcularMediaNotas() {
		if (notasAluno.isEmpty()) {
			return 0.0;
		}
		double somaNotas = 0.0;
		for (int nota : notasAluno.values()) {
			somaNotas += nota;
		}
		double media = somaNotas / notasAluno.size();
		if (media < 7.0) {
			System.out.println("Reprovado - Média: " + media);
		}
		return media;
	}
	public void alunoAprovado(double media) {
		if (media < 7.0) {
			System.out.println("Aluno reprovado. Média" + media);
		} else {
			System.out.println("Aluno aprovado. Média: " + media);
		}
	}
	
	
	
	
	
	
	
}
