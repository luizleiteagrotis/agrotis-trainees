package exercicios.aula25a27;

public class ContaCorrente {

	int numero;
	int agencia;
	double limiteChequeEspecial;
	double saldo;
	String cartaoCreditoVinculadoAConta;
	boolean especial;
	double valorChequeUtilizado = 0;

    
	    boolean efetuarSaque(double quantidadeSaque) {
       
		if (saldo >= quantidadeSaque) {
			saldo -= quantidadeSaque;
			return true;
		} else {
			if(especial) {
				double limite = limiteChequeEspecial + saldo;
				if (limite >= quantidadeSaque) {
					saldo -= quantidadeSaque;
					return true;
				} else {
					return false;
				}
				
			} else {
				return false;
			}
		}
	    }
	   public void realizarDeposito(double valorDeposito) {
	        saldo += valorDeposito;
	        System.out.println("Depósito de R$ " + valorDeposito + " realizado com sucesso.");
	    }

	  public void consultarSaldo() {
	        System.out.println("Saldo atual da conta: " + saldo);
	    }
	  
	  public boolean consultaChequeEspecial() {
		  return saldo < 0;
	  }
	  
	    }

