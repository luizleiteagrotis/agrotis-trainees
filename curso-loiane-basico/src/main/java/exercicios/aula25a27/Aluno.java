package exercicios.aula25a27;

public class Aluno {
	
	public String nome;
	public int matricula;
	public String curso;
	public String[] disciplinaArray;
	public int[] notas;
	
	public Aluno() {
		this.disciplinaArray = new String[3];
		this.notas = new int[3];
	}
	
	public String verificaNota(String disciplina) {
		if(disciplina.equals(disciplinaArray[0])){
			return (notas[0] >= 7) ? "APROVADO" : "REPROVADO";
		}
		if(disciplina.equals(disciplinaArray[1])) {
			return (notas[1] >= 7) ? "APROVADO" : "REPROVADO";
		} 
		if(disciplina.equals(disciplinaArray[2])) {
			return (notas[2] >= 7) ? "APROVADO" : "REPROVADO";
		}
		return "";
	}
}
