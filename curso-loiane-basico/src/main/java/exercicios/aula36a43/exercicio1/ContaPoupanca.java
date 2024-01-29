package exercicios.aula36a43.exercicio1;

import java.util.Calendar;

public class ContaPoupanca extends ContaBancaria {

    private int diaRendimento;

    public ContaPoupanca() {
    };

    public ContaPoupanca(String nomeCliente) {
        super(nomeCliente);
    }

    public int getDiaRendimento() {
        return diaRendimento;
    }

    public void setDiaRendimento(int diaRendimento) {
        this.diaRendimento = diaRendimento;
    }

    public double calcularNovoSaldo(double taxaRendimento) {
        Calendar hoje = Calendar.getInstance();
        double valorRendimento = 0;
        if (this.diaRendimento == hoje.get(Calendar.DAY_OF_MONTH) && taxaRendimento > 0) {
            valorRendimento = this.getSaldo() * taxaRendimento;
            double novoSaldo = this.getSaldo() + valorRendimento;
            setSaldo(novoSaldo);
        }
        return valorRendimento;
    }

}
