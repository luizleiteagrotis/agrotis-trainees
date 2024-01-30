package atividades44_46;

public class Teste {

    public static void main(String[] args) {

        FiguraGeometrica[] figuras = new FiguraGeometrica[6];

        figuras[0] = new Cilindro("Cilindro", null, 3, 2);
        figuras[1] = new Circulo("Círculo", null, 3);
        figuras[2] = new Cubo("Cubo", null, 7);
        figuras[3] = new Piramide("Pirâmide", null, 5, 2);
        figuras[4] = new Quadrado("Quadrado", null, 7);
        figuras[5] = new Triangulo("Triângulo", null, 5, 2);

        for (FiguraGeometrica figuraGeometrica : figuras) {
            if (figuraGeometrica instanceof Figura2D) {
                Figura2D figura2D = (Figura2D) figuraGeometrica;
                double area = figura2D.calcularArea();
                System.out.println("Área da figura " + figuraGeometrica.getNome() + " = " + area + "²");
            } else if (figuraGeometrica instanceof Figura3D) {
                Figura3D figura3D = (Figura3D) figuraGeometrica;
                double volume = figura3D.calcularVolume();
                System.out.println("Volume da figura " + figuraGeometrica.getNome() + " = " + volume + "³");
            }
        }

    }

}
