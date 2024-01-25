package exercicios.aula47a52.exercicio1;
public class ContatoFactory {

    public static Contato criarContato(String nome, String telefone) {
        return new Contato(nome, telefone);
    }

}
