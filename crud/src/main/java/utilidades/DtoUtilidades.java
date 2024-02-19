package utilidades;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class DtoUtilidades {

    public static ParceiroNegocio converteParaEntidade(ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setNome(dto.getNome());
        entidade.setTelefone(dto.getTelefone());
        entidade.setInscricaoFiscal(dto.getInscricaoFiscal());
        entidade.setEndereco(dto.getEndereco());
        return entidade;
    }
    
}
