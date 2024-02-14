package exercicios.aula36a43;

public class ImpostoRendaTeste {

	public static void main(String[] args) {
		
		PessoaJuridica pessoaJ1 = new PessoaJuridica("Foo", 140000, "07877655/0001-09");
		PessoaJuridica pessoaJ2 = new PessoaJuridica("Bar", 200000, "08556341/0001-07");
		PessoaJuridica pessoaJ3 = new PessoaJuridica("Foobar", 500000, "12457899/0001-32");
		
		System.out.println("Imposto a ser pago da Pessoa Jurídica " + pessoaJ1.getNome() + " CNPJ " + pessoaJ1.getCnpj() + " : R$ " + String.format("%.2f", pessoaJ1.calculaImpostoDeRenda()));
		System.out.println("Imposto a ser pago da Pessoa Jurídica " + pessoaJ2.getNome() + " CNPJ " + pessoaJ2.getCnpj() + " : R$ " + String.format("%.2f", pessoaJ2.calculaImpostoDeRenda()));
		System.out.println("Imposto a ser pago da Pessoa Jurídica " + pessoaJ3.getNome() + " CNPJ " + pessoaJ3.getCnpj() + " : R$ " + String.format("%.2f", pessoaJ3.calculaImpostoDeRenda()));
		
		PessoaFisica pessoaF1 = new PessoaFisica("Michelangelo", 1400, "086.772.997-23");
		PessoaFisica pessoaF2 = new PessoaFisica("Donatello", 7000, "099.556.908-30");
		PessoaFisica pessoaF3 = new PessoaFisica("Leonardo", 2400, "321.432.543-65");
		
		System.out.println("Imposto a ser pago da Pessoa Física " + pessoaF1.getNome() + " CPF " + pessoaF1.getCpf() + " : R$ " + String.format("%.2f", pessoaF1.calculaImpostoDeRenda()));
		System.out.println("Imposto a ser pago da Pessoa Física " + pessoaF2.getNome() + " CPF " + pessoaF2.getCpf() + " : R$ " + String.format("%.2f", pessoaF2.calculaImpostoDeRenda()));
		System.out.println("Imposto a ser pago da Pessoa Física " + pessoaF3.getNome() + " CPF " + pessoaF3.getCpf() + " : R$ " + String.format("%.2f", pessoaF3.calculaImpostoDeRenda()));
		
	}

}
