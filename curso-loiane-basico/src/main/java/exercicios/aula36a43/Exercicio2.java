package exercicios.aula36a43;

	import java.util.Scanner;

	class Contribuinte {
	    String nome;
	    double rendaBruta;

	    Contribuinte(String nome, double rendaBruta) {
	        this.nome = nome;
	        this.rendaBruta = rendaBruta;
	        
	    }
	}

	class PessoaJuridica extends Contribuinte {
	    PessoaJuridica(String nome, double rendaBruta) {
	        super(nome, rendaBruta);
	    }

	    double calcularImposto() {
	        return rendaBruta * 0.10;
	    }
	}

	class PessoaFisica extends Contribuinte {
	    PessoaFisica(String nome, double rendaBruta) {
	        super(nome, rendaBruta);
	    }

	    double calcularImposto() {
	        if (rendaBruta <= 1400) {
	            return 0;
	        } else if (rendaBruta <= 2100) {
	            return rendaBruta * 0.10 - 100;
	        } else if (rendaBruta <= 2800) {
	            return rendaBruta * 0.15 - 270;
	        } else if (rendaBruta <= 3600) {
	            return rendaBruta * 0.25 - 500;
	        } else {
	            return rendaBruta * 0.30 - 700;
	        }
	    }
	}
