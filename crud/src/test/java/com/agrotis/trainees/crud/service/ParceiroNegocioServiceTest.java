package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.service.exceptions.IdExistenteException;

public class ParceiroNegocioServiceTest {

    private final Integer ID_INSERIDO = 1;
    private final String NOME = "AgroFertil Ltda";
    private final String INSCRICAO_FISCAL = "075987619";
    private final String ENDERECO = "Rua do Desenvolvimento";
    private final String TELEFONE = "41996483268";

    @Mock
    private ParceiroNegocioRepository repository;

    @InjectMocks
    private ParceiroNegocioService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void inserirParceiroNegocio() {

        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

        ParceiroNegocioDto dto = criarParceiroNegocioDto();

        ParceiroNegocioDto result = service.salvar(dto);

        assertNotNull(result);
    }

    private ParceiroNegocio criarParceiroNegocio() {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setNome(NOME);
        entidade.setInscricaoFiscal(INSCRICAO_FISCAL);
        entidade.setEndereco(ENDERECO);
        entidade.setTelefone(TELEFONE);
        return entidade;
    }

    private ParceiroNegocioDto criarParceiroNegocioDto() {

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(ID_INSERIDO);
        dto.setNome(NOME);
        dto.setInscricaoFiscal(INSCRICAO_FISCAL);
        dto.setEndereco(ENDERECO);
        dto.setTelefone(TELEFONE);
        return dto;
    }

    @Test
    public void inserirParceiroNegocioComErro() {
        // Criando um DTO com informações inválidas (por exemplo, nome vazio)
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome("");
        dto.setInscricaoFiscal(INSCRICAO_FISCAL);
        dto.setEndereco(ENDERECO);
        dto.setTelefone(TELEFONE);

        // Chamando o método salvar do serviço e esperando uma exceção
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.salvar(dto));
    }

    @Test
    public void inserirParceiroNegocioComIdExistente() {
        // Cenário: tentativa de inserir um parceiro de negócios com ID já
        // existente
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        parceiroNegocio.setId(ID_INSERIDO); // Simulando um parceiro de negócios
                                            // com o mesmo ID
        when(repository.existsById(ID_INSERIDO)).thenReturn(true);

        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        dto.setId(ID_INSERIDO); // Simulando a inserção do parceiro com o mesmo
                                // ID

        assertThrows(IdExistenteException.class, () -> service.salvar(dto));
    }

}
