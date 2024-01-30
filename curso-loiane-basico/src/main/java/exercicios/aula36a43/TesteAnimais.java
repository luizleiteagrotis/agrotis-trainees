
package atividades37_43;

public class TesteAnimais {

    public static void main(String[] args) {

        Mamifero camelo = new Mamifero("Camilo", 150, "Amarelo", 2, "Folhas e frutos");
        Peixe tubarao = new Peixe("Tutubarão", 300, 1.5);
        Mamifero ursoDoCanada = new Mamifero("Ursulão", 180, "Vermelho", 0.5, "Mel");
        Animal aranha = new Animal("Dona Aranha", 6, "Preta", "Cavernas", 0.2);

        System.out.println("Zoo: ");
        camelo.imprimirAnimal();
        tubarao.imprimirAnimal();
        ursoDoCanada.imprimirAnimal();
        aranha.imprimirAnimal();

    }

}
