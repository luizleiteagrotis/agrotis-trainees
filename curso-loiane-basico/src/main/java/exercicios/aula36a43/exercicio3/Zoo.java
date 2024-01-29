package exercicios.aula36a43.exercicio3;

public class Zoo {
    public static void main(String[] args) {
        Mamifero animal = new Mamifero("Camelo", 150, "Amarelo", 2.0);
        Peixe animal2 = new Peixe("Tubarão", 300, 0, "Cinzento", 1.5, "Barbatanas e cauda");
        Mamifero animal3 = new Mamifero("Urso-do-canadá", 180, "Vermelho", 0.5);
        animal3.setAlimento("Mel");

        System.out.println(animal);
        System.out.println(animal2);
        System.out.println(animal3);

    }
}
