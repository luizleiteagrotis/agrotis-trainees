package atividades24;

import java.util.Date;

public class LivroDeBibliotecaTeste {

    public static void main(String[] args) {

        LivroDeBiblioteca revBichos = new LivroDeBiblioteca();
        revBichos.nome = "A Revolução dos Bichos";
        revBichos.autor = "George Orwell";
        revBichos.editora = "Penguin";
        revBichos.anoLancamento = 1945;
        revBichos.paginas = 152;
        revBichos.edicao = 1;
        revBichos.disponivel = false;
        revBichos.dataEmprestimo = new Date();
        revBichos.dataDevolucao = new Date(+7); // necessário arrumar este ponto

        if (revBichos.disponivel == false) {
            System.out.println(revBichos.nome + ": indísponível. \nData de empréstimo: " + revBichos.dataEmprestimo
                            + "\nData de devolução: " + revBichos.dataDevolucao);
        } else {
            System.out.println(revBichos.nome + ": disponível");
        }

        System.out.println();

        LivroDeBiblioteca maus = new LivroDeBiblioteca();
        maus.nome = "Maus";
        maus.autor = "Art Spiegelman";
        maus.editora = "Quadrinhos na Cia";
        maus.anoLancamento = 2005;
        maus.paginas = 296;
        maus.edicao = 1;
        maus.disponivel = true;

        if (maus.disponivel == false) {
            System.out.println(maus.nome + ": indísponível. \nData de empréstimo: " + maus.dataEmprestimo + "\nData de devolução: "
                            + maus.dataDevolucao);
        } else {
            System.out.println(maus.nome + ": disponível");
        }

    }

}
