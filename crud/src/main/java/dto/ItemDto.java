package dto;

import com.agrotis.trainees.crud.entity.NotaFiscalC;
import com.agrotis.trainees.crud.entity.Produto;

public class ItemDto {

    private Integer id;
    
    private NotaFiscalC notaFiscalC;
    
    private Produto produto;
    
    private Integer quantidade;
    
    private Double precoUnitario;
    
    private Double valorTotal;
    
    public NotaFiscalC getNotaFiscalC() {
        return notaFiscalC;
    }

    public void setNotaFiscalC(NotaFiscalC notaFiscalC) {
        this.notaFiscalC = notaFiscalC;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

   

    
    
}
