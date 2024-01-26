package exercicios.aula34a36;

public class Calculadora {
	
    public static double soma(double numeroA, double numeroB) {
        return numeroA + numeroB;
    }


    public static double diminuir(double numeroA, double numeroB) {
        return numeroA - numeroB;
    }

 
    public static double vezes(double numeroA, double numeroB) {
        return numeroA * numeroB;
    }
    
    public static double divisao(double numeroA, double numeroB) {
        if (numeroB != 0) {
            return numeroA / numeroB;
        } else {
            return Double.NaN;
        }
    }
    
    public static double potencia(double base, int expoente) {
        return Math.pow(base, expoente);
    }
    
   

}
