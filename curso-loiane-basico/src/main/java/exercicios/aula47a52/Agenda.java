package exercicios.aula47a52;

public class Agenda {
	
	private Contato[] contatos;
	
	public Agenda() {
		contatos = new Contato[5];
	}
	
	public void addContato (Contato cont)  throws AgendaCheiaException {
		boolean agendaCheia = true;
		for (int i=0; i<contatos.length; i++) {
			if (contatos[i] == null) {
				contatos[i] = cont;
				agendaCheia = false;
				break;
			}
		}
		
		if (agendaCheia) {
			throw new AgendaCheiaException();
		}
	}
	
	public int consultarContato(String nome) throws ContatoNaoExisteException {
		for (int i=0; i<contatos.length; i++) {
			if (contatos[i] != null) {
				if (contatos[i].getNome().equalsIgnoreCase(nome));
				return i;
			}
		}
		throw new ContatoNaoExisteException(nome);
	}
	
	
	
	

	@Override
	public String toString() {
		String str = "";
		
		for (Contato contat : contatos) {
			if (str != null) {
			str+=contat.toString() + "\n";
			}
			
		}
		
		return str;
	}
	
	
}
