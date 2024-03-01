package utilidades;

import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.NotaFiscalC;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

import dto.CabecalhoDto;
import dto.ItemNotaDto;
import dto.ParceiroNegocioDto;
import dto.ProdutoDto;

public class DtoUtilidades {

    public static ParceiroNegocio converteParaEntidade(ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setNome(dto.getNome());
        entidade.setTelefone(dto.getTelefone());
        entidade.setInscricaoFiscal(dto.getInscricao());
        entidade.setEndereco(dto.getEndereco());
        return entidade;
    }
    
    public static ParceiroNegocioDto converteParaDto(ParceiroNegocio entidade) {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setTelefone(entidade.getTelefone());
        dto.setInscricao(entidade.getInscricaoFiscal());
        dto.setEndereco(entidade.getEndereco());
        return dto;
    }
    

    public static Produto converteParaEntidade(ProdutoDto dto) {
        Produto entidade = new Produto();
        entidade.setDescricao(dto.getDescricao());
        entidade.setFabricante(dto.getFabricante());
        entidade.setDataValidade(dto.getDataValidade());
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        return entidade;
    }
    
    public static ProdutoDto converteParaDto(Produto entidade) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(entidade.getId());
        dto.setDescricao(entidade.getDescricao());
        dto.setFabricante(entidade.getFabricante());
        dto.setDataValidade(entidade.getDataValidade());
        dto.setDataFabricacao(entidade.getDataFabricacao());
        dto.setQuantidadeEstoque(entidade.getQuantidadeEstoque());
        return dto;
    }
    
    public static NotaFiscalC converteParaEntidade(CabecalhoDto dto) {
        NotaFiscalC entidade = new NotaFiscalC();
        entidade.setNotaFiscalTipo(dto.getNotaFiscalTipo());
        entidade.setData(dto.getData());
        entidade.setNumeroNota(dto.getNumero());
        entidade.setParceiroNegocio(dto.getParceiroNegocio());
        return entidade;
    }
    
    public static CabecalhoDto converteParaDto(NotaFiscalC entidade) {
        CabecalhoDto dto = new CabecalhoDto();
        dto.setId(entidade.getId());
        dto.setNotaFiscalTipo(entidade.getNotaFiscalTipo());
        dto.setNumero(entidade.getNumeroNota());
        dto.setParceiroNegocio(entidade.getParceiroNegocio());
        return dto;
    }
    public static ItemNota converteParaEntidade(ItemNotaDto dto) {
        ItemNota entidade = new ItemNota();
        entidade.setNotaFiscalC(dto.getNotaFiscalC());
        entidade.setPrecoUnitario(dto.getPrecoUnitario());
        entidade.setProduto(dto.getProduto());
        entidade.setQuantidade(dto.getQuantidade());
        return entidade;
    }
    
    public static ItemNotaDto converteParaDto(ItemNota entidade) {
        ItemNotaDto dto = new ItemNotaDto();
        dto.setId(entidade.getId());
        dto.setNotaFiscalC(entidade.getNotaFiscalC());
        dto.setPrecoUnitario(entidade.getPrecoUnitario());
        dto.setProduto(entidade.getProduto());
        dto.setQuantidade(entidade.getQuantidade());
        return dto;
    }
}
