package dto;

import java.time.LocalDate;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class ProdutoDto {

    private Integer id;
    
    private String descricao;
    
    private ParceiroNegocio idParceiro;
    
    private LocalDate dataFabricacao;
    
    private LocalDate dataValidade;
    
    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    private Integer quantidadeEstoque;
    
    public String getFabricante() {
        return Fabricante;
    }

    public void setFabricante(String fabricante) {
        Fabricante = fabricante;
    }

    private String Fabricante;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ParceiroNegocio getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(ParceiroNegocio idParceiro) {
        this.idParceiro = idParceiro;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

   

   
    
    
    
}
