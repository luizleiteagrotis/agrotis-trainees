package atividades24;

public class LivroLivrariaTeste {

    public static void main(String[] args) {

        LivroLivraria revBichos = new LivroLivraria();
        revBichos.nome = "A Revolução dos Bichos";
        revBichos.autor = "George Orwell";
        revBichos.editora = "Penguin";
        revBichos.anoLancamento = 1945;
        revBichos.paginas = 152;
        revBichos.edicao = 1;
        revBichos.preco = 15.99;
        revBichos.estante[0] = 3;

        System.out.println(revBichos.nome + "\nAutor: " + revBichos.autor + ". \nEditora: " + revBichos.editora
                        + ". \nAno de lançamento: " + revBichos.anoLancamento + ". \nQuantidade de páginas: " + revBichos.paginas
                        + ". \nEdição: " + revBichos.edicao + "\nPreco R$ " + revBichos.preco);

        LivroLivraria maus = new LivroLivraria();
        maus.nome = "Maus";
        maus.autor = "Art Spiegelman";
        maus.editora = "Quadrinhos na Cia";
        maus.anoLancamento = 2005;
        maus.paginas = 296;
        maus.edicao = 1;
        maus.preco = 25.90;
        maus.estante[1] = 1;

        System.out.println(maus.nome + "\nAutor: " + maus.autor + ". \nEditora: " + maus.editora + ". \nAno de lançamento: "
                        + maus.anoLancamento + ". \nQuantidade de páginas: " + maus.paginas + ". \nEdição: " + maus.edicao
                        + "\nPreco: R$ " + maus.preco);

    }

}
