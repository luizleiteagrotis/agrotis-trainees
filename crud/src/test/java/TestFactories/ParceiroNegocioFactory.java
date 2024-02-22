package TestFactories;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class ParceiroNegocioFactory {

    private Integer id;

    private String nome;

    private String inscricaoFiscal;

    private String endereco;

    private String telefone;

    public ParceiroNegocioFactory id(Integer id) {
        this.id = id;
        return this;
    }

    public ParceiroNegocioFactory nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ParceiroNegocioFactory inscricaoFiscal(String inscricaoFiscal) {
        this.inscricaoFiscal = inscricaoFiscal;
        return this;
    }

    public ParceiroNegocioFactory endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public ParceiroNegocioFactory telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public ParceiroNegocio build() {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(this.id);
        parceiro.setNome(this.nome);
        parceiro.setInscricaoFiscal(this.inscricaoFiscal);
        parceiro.setEndereco(this.endereco);
        parceiro.setTelefone(this.telefone);
        return parceiro;
    }
}
