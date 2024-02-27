package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    
    @Captor
    private ArgumentCaptor<Integer> idCaptor;

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
    public void inserirParceiroNegocioComNomeVazio() {
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

    @Test
    public void inserirParceiroNegocioComEnderecoEmBranco() {
        // Criando um DTO com endereço em branco
        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        dto.setEndereco("");

        // Chamando o método salvar do serviço e esperando uma exceção
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.salvar(dto));
    }

    @Test
    public void inserirParceiroNegocioComTelefoneInvalido() {
        // Criando um DTO com telefone inválido
        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        dto.setTelefone("41996"); // Telefone inválido, deve conter 11
        // dígitos

        // Chamando o método salvar do serviço e esperando uma exceção
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.salvar(dto));
    }

    @Test
    public void inserirParceiroNegocioComTelefoneNulo() {
        // Criando um DTO com telefone nulo
        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        dto.setNome(NOME);
        dto.setInscricaoFiscal(INSCRICAO_FISCAL);
        dto.setEndereco(ENDERECO);
        dto.setTelefone(null);

        // Chamando o método salvar do serviço e esperando uma exceção
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.salvar(dto));
    }

    @Test
    public void inserirParceiroNegocioComSucesso() {
        // Criando um DTO com informações válidas
        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        dto.setId(ID_INSERIDO);
        dto.setNome(NOME);
        dto.setEndereco(ENDERECO);
        dto.setTelefone("41996483268");
        dto.setInscricaoFiscal(INSCRICAO_FISCAL);

        // Mockando o comportamento do repositório para retornar um objeto
        // ParceiroNegocio válido
        ParceiroNegocio parceiroNegocioSalvo = criarParceiroNegocio();
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocioSalvo);

        // Chamando o método salvar do serviço
        ParceiroNegocioDto result = service.salvar(dto);

        // Verificando se o método save do repositório foi chamado uma vez
        verify(repository, times(1)).save(any(ParceiroNegocio.class));

        // Verificando se o objeto retornado pelo serviço não é nulo
        assertNotNull(result);
    }

    @Test
    public void buscarPorIdExistente() {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(1);
        entidade.setNome("Nome do Parceiro");

        when(repository.findById(1)).thenReturn(Optional.of(entidade));

        ParceiroNegocioDto result = service.buscarPorId(1);

        assertNotNull(result);
        assertEquals(entidade.getId(), result.getId());
        assertEquals(entidade.getNome(), result.getNome());
    }

    @Test
    public void buscarPorIdNaoExistente() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> service.buscarPorId(1));
    }

    @Test
    public void buscarPorNomeExistente() {
        String nomeParceiro = "Nome do Parceiro";
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setId(1);
        parceiroNegocio.setNome(nomeParceiro);

        when(repository.findByNome(nomeParceiro)).thenReturn(Optional.of(parceiroNegocio));

        ParceiroNegocioDto result = service.buscarPorNome(nomeParceiro);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getId(), result.getId());
        assertEquals(parceiroNegocio.getNome(), result.getNome());
    }

    @Test
    public void buscarPorNomeNaoExistente() {
        String nomeParceiro = "Nome do Parceiro Inexistente";

        when(repository.findByNome(nomeParceiro)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> service.buscarPorNome(nomeParceiro));
    }
    
    @Test
    public void testeDeletarPorId() {
    	ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
    	parceiroNegocio.setId(1);
    	when(repository.findById(1)).thenReturn(Optional.of(parceiroNegocio));
    	
    	service.deletarPorId(1);
    	
    	verify(repository).findById(idCaptor.capture());
    	assertEquals(1, idCaptor.getValue().intValue());
    	
    	verify(repository, times(1)).deleteById(1);
    }
    
    @Test
    public void testListarTodos() {
        when(repository.findAll()).thenReturn(List.of(new ParceiroNegocio()));

        assertEquals(1, service.listarTodos().size());
    }
    
    @Test
    public void testUpdate() {
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(parceiroNegocio));
        when(repository.save(any())).thenReturn(parceiroNegocio);

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome("Novo Nome");

        ParceiroNegocioDto result = service.update(1, dto);

        assertNotNull(result);
        assertEquals(dto.getNome(), result.getNome());
    }
    
    @Test
    public void testSalvar_IdNuloExistente() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(null); // ID nulo
        dto.setNome("Nome");
        dto.setEndereco("Endereco");
        dto.setTelefone("12345678901");

        // Simulando um ID nulo existente
        when(repository.existsById(null)).thenReturn(true);

        assertThrows(EntidadeNaoEncontradaException.class, () -> service.salvar(dto));
    }
    
    
    @Test
    public void testUpdate_NaoEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> service.update(1, new ParceiroNegocioDto()));
    }
    
    @Test
    public void testSalvar_NomeNulo() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome(null);

        assertThrows(EntidadeNaoEncontradaException.class, () -> service.salvar(dto));
    }
    
    @Test
    public void testSalvar_EnderecoNulo() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome("Nome");
        dto.setEndereco(null);

        assertThrows(EntidadeNaoEncontradaException.class, () -> service.salvar(dto));
    }
    
    
    @Test
    public void testListarTodosNenhumParceiro() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        assertEquals(0, service.listarTodos().size());
    }
    
    @Test
    public void testDeletarPorIdNaoExistente() {
        // Configuração do mock do repositório para retornar Optional vazio
        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        // Chamada do método de deleção com um ID não existente
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.deletarPorId(1));

        // Verificação de que o método deleteById não foi chamado
        verify(repository, never()).deleteById(anyInt());
    }
    
    @Test
    public void testDeletarPorIdExistente() {
        // Criando um objeto ParceiroNegocio para simular um retorno do findById
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(parceiroNegocio));

        // Chamando o método de deleção
        service.deletarPorId(1);

        // Verificando se o método findById foi chamado com o ID correto
        verify(repository).findById(idCaptor.capture());
        assertEquals(1, idCaptor.getValue().intValue());

        // Verificando se o método deleteById foi chamado uma vez com o ID correto
        verify(repository, times(1)).deleteById(1);
    }
    

    @Test
    public void testeTelefoneMaisDigitosQueOPermitido() {
      // Teste com telefone inválido (mais de 11 dígitos)
        assertFalse(ParceiroNegocioService.seTelefoneForValido("123456789012345"));

    }
    
    
    @Test
    public void testTelefoneComCaracteresNaoNumericos() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome("Nome");
        dto.setEndereco("Endereço");
        dto.setTelefone("1234ABCDE5678"); // Telefone com caracteres não numéricos

        // Mockando o comportamento do repositório para retornar um objeto ParceiroNegocio válido
        ParceiroNegocio parceiroNegocioSalvo = criarParceiroNegocio();
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocioSalvo);

        ParceiroNegocioService service = new ParceiroNegocioService(repository);

        // Espera-se que uma exceção EntidadeNaoEncontradaException seja lançada
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.salvar(dto));
    }

    
    @Test
    public void testTelefoneComMenosDigitosQueOPermitido() {
        // Teste com telefone inválido (menos de 11 dígitos)
        assertFalse(ParceiroNegocioService.seTelefoneForValido("1234567890"));
    }
    
    @Test
    public void inserirParceiroNegocioComTelefoneVazio() {
        // Criando um DTO com telefone vazio
        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        dto.setNome(NOME);
        dto.setInscricaoFiscal(INSCRICAO_FISCAL);
        dto.setEndereco(ENDERECO);
        dto.setTelefone(""); // Telefone vazio

        // Chamando o método salvar do serviço e esperando uma exceção
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.salvar(dto));
    }
    
    
     @Test
     public void inserirParceiroNegocioComTelefoneValido() {
        // Criando um DTO com telefone válido
        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        dto.setTelefone("41996483268"); // Telefone válido, contém 11 dígitos

        // Mockando o comportamento do repositório e chamando o método salvar do serviço
        ParceiroNegocio parceiroNegocioSalvo = criarParceiroNegocio();
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocioSalvo);
        ParceiroNegocioDto result = service.salvar(dto);

        // Verificando se o método save do repositório foi chamado uma vez
        verify(repository, times(1)).save(any(ParceiroNegocio.class));

        // Verificando se o objeto retornado pelo serviço não é nulo
        assertNotNull(result);
    }
}



    

    


