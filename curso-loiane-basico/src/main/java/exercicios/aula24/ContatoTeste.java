package atividades24;

public class ContatoTeste {

    public static void main(String[] args) {

        Contato c1 = new Contato();
        c1.nome = "Letícia";
        c1.email = "leticia@leticia.com";

        c1.telefones = new String[2];
        c1.telefones[0] = "99999999";
        c1.telefones[1] = "88888888";

        System.out.println("Contato: " + c1.nome);
        System.out.println("Email: " + c1.email);
        System.out.println("Telefones: ");
        for (int i = 0; i < c1.telefones.length; i++) {
            System.out.print(c1.telefones[i] + "\n");
        }

        Contato c2 = new Contato();
        c2.nome = "Allan";
        c2.email = "allan@allan.com";

        c2.telefones = new String[1];
        c2.telefones[0] = "99999999";

        System.out.println("\nContato: " + c2.nome);
        System.out.println("Email: " + c2.email);
        System.out.println("Telefones: ");
        for (int i = 0; i < c2.telefones.length; i++) {
            System.out.print(c2.telefones[i] + "\n");
        }

    }

}
