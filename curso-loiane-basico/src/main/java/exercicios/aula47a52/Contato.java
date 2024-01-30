package atividades47_52;

public class Contato {

    private static int count = 0;

    private int id;
    private String nome;
    private String telefone;
    private String email;

    public Contato() {
        count++;
        id = count;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "[Id: " + id + "; Nome: " + nome + "; Telefone: " + telefone + "; Email: " + email + "]";
    }

}
