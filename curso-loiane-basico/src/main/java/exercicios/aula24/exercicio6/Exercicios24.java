package exercicios.aula24.exercicio6;

import java.util.Date;

import exercicios.aula24.exercicio1.Lampada;
import exercicios.aula24.exercicio2.Livro;
import exercicios.aula24.exercicio3.LivroDeLivraria;
import exercicios.aula24.exercicio4.LivroDeBiblioteca;
import exercicios.aula24.exercicio5.Conta;

public class Exercicios24 {

    public static void main(String[] args) {

        // teste exercicio 1
        Lampada lamp1 = new Lampada();
        lamp1.cor = " vermelha";
        lamp1.durabilidade = 2000;
        lamp1.potencia = 60;
        lamp1.valor = 10.99;

        System.out.println("Cor da lampada: " + lamp1.cor);
        System.out.println("Duração da lampada: " + lamp1.durabilidade);
        System.out.println("---------------------------");
        // teste exercicio 2
        Livro livro1 = new Livro();
        livro1.titulo = "O ladrão de cadáveres";
        livro1.autor = "James Bradley";
        livro1.idioma = "PT-BR";
        livro1.anoPublicacao = 2013;

        System.out.println("titulo: " + livro1.titulo);
        System.out.println("autor: " + livro1.autor);
        System.out.println("---------------------------");

        // teste exercicio 3
        LivroDeLivraria livroLiv1 = new LivroDeLivraria();
        livroLiv1.titulo = "O ladrão de cadáveres";
        livroLiv1.autor = "James Bradley";
        livroLiv1.preco = 15;
        livroLiv1.idioma = "PT-BR";
        livroLiv1.anoPublicacao = 2013;

        System.out.println("titulo: " + livroLiv1.titulo);
        System.out.println("autor: " + livroLiv1.autor);
        System.out.println("preço R$ " + livroLiv1.preco);
        System.out.println("---------------------------");

        // teste exercicio 4
        LivroDeBiblioteca livroBib1 = new LivroDeBiblioteca();
        livroBib1.titulo = "O ladrão de cadáveres";
        livroBib1.autor = "James Bradley";
        livroBib1.idioma = "PT-BR";
        livroBib1.anoPublicacao = 2013;
        livroBib1.emprestimo = true;
        livroBib1.nomeCliente = "Matheus Lourenço";
        livroBib1.dataEmprestimo = new Date();

        System.out.println("titulo: " + livroBib1.titulo);
        System.out.println("autor: " + livroBib1.autor);
        System.out.println("Nome do cliente: " + livroBib1.nomeCliente);
        System.out.println("Data Emprestimo: " + livroBib1.dataEmprestimo);
        System.out.println("Emprestimo: " + livroBib1.emprestimo);
        System.out.println("---------------------------");

        // teste exercicio 5
        Conta conta1 = new Conta();
        conta1.especial = false;
        conta1.limite = 500;
        System.out.println("Tipo conta " + conta1.tipo);
        System.out.println("Especial " + conta1.especial);
        System.out.println("Limite R$ " + conta1.limite);

        // teste exercicio 6
        System.out.println("---------------------------");
        Contato contato = new Contato();
        contato.apelido = "nana";
        contato.email = "nana@gmail.com";
        contato.nome = "Natalia";
        contato.telefones = new String[1];
        contato.tipos = new String[1];
        contato.telefones[0] = "41992477204";
        contato.tipos[0] = "celular";

        System.out.println("Nome " + contato.nome);
        System.out.println("Apelido " + contato.apelido);
        System.out.println("Telefone " + contato.telefones[0]);
        System.out.println("Tipo " + contato.tipos[0]);

    }

}
