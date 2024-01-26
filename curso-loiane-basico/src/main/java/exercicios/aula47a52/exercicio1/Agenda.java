package exercicios.aula47a52.exercicio1;

public class Agenda {
	
	private Contato[] contatos;
	private long contadorIdContato = 0;
	
	public Agenda(int tamanho) {
		contatos = new Contato[tamanho];
	}
	
	public void adicionar(Contato contato) throws AgendaCheiaException {
		boolean adicionado = false;
		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] == null) {
				contatos[i] = contato;
				contato.setId(contadorIdContato++);
				adicionado = true;
				break;
			}
		}
		if (!adicionado) throw new AgendaCheiaException(contato);
		
	}
	
	public Contato consultar(long id) throws ContantoNaoExisteException {
		for (Contato contato : contatos) {
			if (contato != null && contato.getId() == id) {
				return contato;
			}
		}
		throw new ContantoNaoExisteException(id);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AGENDA\nTamanho: " + contatos.length + "\nContatos: \n");
		for (Contato contato : contatos) {
			if (contato == null) builder.append("Vazio\n");
			else builder.append(contato + "\n");
		}
		return builder.toString();
	}
}
