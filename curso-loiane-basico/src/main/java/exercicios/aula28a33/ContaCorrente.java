package exercicios.aula28a33;

public class ContaCorrente {

	private int numero;
	private int agencia;
	private double limiteChequeEspecial;
	private double saldo;
	private boolean especial;
	private double valorChequeUtilizado = 0;
	private String setNumero;
	
	
	
	  public ContaCorrente() {
		super();
	}
	  
	 
	  public ContaCorrente(int numero, int agencia, double limiteChequeEspecial, double saldo,
				 boolean especial, double valorChequeUtilizado) {
			super();
			this.numero = numero;
			this.agencia = agencia;
			this.limiteChequeEspecial = limiteChequeEspecial;
			this.saldo = saldo;
			this.especial = especial;
			this.valorChequeUtilizado = valorChequeUtilizado;
		}



	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public int getAgencia() {
		return agencia;
	}


	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}


	public double getLimiteChequeEspecial() {
		return limiteChequeEspecial;
	}


	public void setLimiteChequeEspecial(double limiteChequeEspecial) {
		this.limiteChequeEspecial = limiteChequeEspecial;
	}


	public double getSaldo() {
		return saldo;
	}


	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}



	public boolean isEspecial() {
		return especial;
	}


	public void setEspecial(boolean especial) {
		this.especial = especial;
	}


	public double getValorChequeUtilizado() {
		return valorChequeUtilizado;
	}


	public void setValorChequeUtilizado(double valorChequeUtilizado) {
		this.valorChequeUtilizado = valorChequeUtilizado;
	}
	
	public boolean efetuarSaque(double quantidadeSaque) {
	       
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
