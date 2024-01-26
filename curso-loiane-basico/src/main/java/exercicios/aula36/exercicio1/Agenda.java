package exercicios.aula36.exercicio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agenda {
	
	private String nome;
	private List<Contato> contatos;
	
	public Agenda(String nome) {
		this.nome = nome;
		contatos = new ArrayList<>();
	}
	
	public void adicionar(Contato contato) {
		contatos.add(contato);
	}
	
	public String getNome() {
		return nome;
	}
	
	public List<Contato> getContatos() {
		return Collections.unmodifiableList(contatos);
	}
}
