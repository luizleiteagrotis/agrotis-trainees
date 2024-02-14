package exercicios.aula47a52;

public class Agenda {

	private Contato[] contatos;
	
	public Agenda() {
		contatos = new Contato[5]; 
	}
	
	public Contato[] getContatos() {
		return contatos;
	}

	public void setContatos(Contato[] contatos) {
		this.contatos = contatos;
	}

	public Contato consultarContatos(String nomeBusca) {
		for(Contato contato : contatos) {
			if(nomeBusca.equals(contato.getNome())) {
				return contato;
			}
		}
		return null;
	}
	
	public void adicionarContato(Contato novoContato) {
		for(int i = 0; i < contatos.length; i++) {
			if(contatos[i] == null) {
				contatos[i] = novoContato;
				break;
			}
		}
	}
	
}
 