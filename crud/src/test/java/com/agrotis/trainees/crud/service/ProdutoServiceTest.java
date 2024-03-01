package com.agrotis.trainees.crud.service;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.enums.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.exceptions.CampoEmptyOrNullException;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;


@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ParceiroNegocioRepository parceiroNegocioRepository;

    @InjectMocks
    private ProdutoService service;

//    @Test
//    void saveProductWhenDtoValido() {
//        ProdutoDto produtoDto = criarProdutoDto();
//        Produto produtoSalvo = criarProduto();
//        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);
//
//        ProdutoDto result = service.salvar(produtoDto);
//
//        assertNotNull(result);
//        assertEquals(produtoSalvo.getId(), result.getId());
//        verify(produtoRepository).save(any(Produto.class));
//    }
    
    @Test
    void salvarProdutoQuandoDtoValido() {
    	// Criar um ProdutoDto válido
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setNome("Nome do Produto");
        produtoDto.setDescricao("Descrição do Produto");
        produtoDto.setId(3);
        produtoDto.setDataFabricacao(LocalDate.of(2024, 2, 21));
        produtoDto.setDataValidade(LocalDate.of(2025, 2, 21));
        produtoDto.setEstoque(new BigDecimal(50));
       
        
        // Criar um fabricante válido
        ParceiroNegocio fabricante = new ParceiroNegocio();
        fabricante.setId(1); // Defina um ID válido para o fabricante
        // Defina outros atributos do fabricante conforme necessário
        
        produtoDto.setFabricante(fabricante);

        // Criar um Produto válido
        Produto produtoSalvo = new Produto();
        produtoSalvo.setId(3); // Defina um ID válido
        produtoSalvo.setNome("Nome do Produto");
        produtoSalvo.setDescricao("Descrição do Produto");
        produtoSalvo.setFabricante(fabricante);
        produtoSalvo.setDataFabricacao(LocalDate.of(2024, 2, 21));
        produtoSalvo.setDataValidade(LocalDate.of(2025, 2, 21));
        produtoSalvo.setEstoque(new BigDecimal(10));
        // Defina outros atributos do produtoSalvo conforme necessário

        // Configurar o comportamento do mock do ProdutoRepository
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);

        // Chamar o método salvar
        ProdutoDto result = service.salvar(produtoDto);

        // Verificar se o resultado não é nulo
        assertNotNull(result);
        // Verificar se o ID do produto salvo é igual ao ID do resultado
        assertEquals(produtoSalvo.getId(), result.getId());
        // Verificar se o método save do produtoRepository foi chamado
        verify(produtoRepository).save(any(Produto.class));
    }
    
    
    
    @Test
    void salvarCampoVazioOuNuloQuandoDtoInvalido() {
        // Crie um ProdutoDto inválido (sem dados)
        ProdutoDto produtoDto = new ProdutoDto();

        // Verifique se o serviço está lançando a exceção esperada
        assertThrows(CampoEmptyOrNullException.class, () -> {
            service.salvar(produtoDto);
        });
    }

   
    

    @Test
    void buscarPeloIdRetornarProdutoDto() {
        int id = 1;
        Produto produto = criarProduto();
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        ProdutoDto result = service.buscarPorId(id);

        assertNotNull(result);
        assertEquals(produto.getDescricao(), result.getDescricao());
    }

    @Test
    void buscarPeloIdLancaExcecao_SeProdutoNaoEncontrado() {
        int id = 1;
        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.buscarPorId(id);
        });
    }

    @Test
    void buscarTodosRetornaListDeProdutosDto() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(criarProduto());
        when(produtoRepository.findAll()).thenReturn(produtos);

        List<ProdutoDto> result = service.listarTodos();

        assertNotNull(result);
        assertEquals(produtos.size(), result.size());
        assertEquals(produtos.get(0).getDescricao(), result.get(0).getDescricao());
    }

    @Test
    void deletarPorIdDeletaProduto_QuandoEncontrar() {
        int id = 1;
        Produto produto = criarProduto();
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        service.deletarPorId(id);

        verify(produtoRepository).deleteById(id);
    }

    @Test
    void deletarPorIdLancaExcecao_SeProdutoNaoEncontrado() {
        int id = 1;
        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.deletarPorId(id);
        });
    }
    
    @Test
    void atualizarProduto_QuandoEncontrarProduto() {
        int id = 3;
        Produto produtoExistente = criarProduto();
        ProdutoDto produtoDto = criarProdutoDto();
        
        // Mock do fabricante existente com ID 1
        ParceiroNegocio fabricanteExistente = new ParceiroNegocio();
        fabricanteExistente.setId(1);
        
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoExistente));
        when(parceiroNegocioRepository.findById(1)).thenReturn(Optional.of(fabricanteExistente));
        when(produtoRepository.save(any(Produto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProdutoDto result = service.update(id, produtoDto);

        assertNotNull(result);
        assertEquals(produtoDto.getDescricao(), result.getDescricao());
        verify(produtoRepository).save(any(Produto.class));
    }

    @Test
    void atualizar_LancarExcecaoSeProdutoNaoEncontrado() {
        int id = 1;
        ProdutoDto produtoDto = criarProdutoDto();
        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.update(id, produtoDto);
        });
    }
    
    @Test
    void salvar_DeveLancarExcecaoQuandoIdNulo() {
        ProdutoDto produtoDto = criarProdutoDto();
        produtoDto.setId(null);

        assertThrows(CampoEmptyOrNullException.class, () -> service.salvar(produtoDto));
    }

    @Test
    void salvar_DeveLancarExcecaoQuandoNomeNulo() {
        ProdutoDto produtoDto = criarProdutoDto();
        produtoDto.setNome(null);

        assertThrows(CampoEmptyOrNullException.class, () -> service.salvar(produtoDto));
    }

    @Test
    void salvar_DeveLancarExcecaoQuandoNomeVazio() {
        ProdutoDto produtoDto = criarProdutoDto();
        produtoDto.setNome("");

        assertThrows(CampoEmptyOrNullException.class, () -> service.salvar(produtoDto));
    }
    
    @Test
    void salvar_DeveLancarExcecaoQuandoIdFabricanteNulo() {
        ProdutoDto produtoDto = criarProdutoDto();
        produtoDto.getFabricante().setId(null);

        assertThrows(CampoEmptyOrNullException.class, () -> service.salvar(produtoDto));
    }
    
    @Test
    void salvar_DeveLancarExcecaoQuandoDataFabricacaoNula() {
        ProdutoDto produtoDto = criarProdutoDto();
        produtoDto.setDataFabricacao(null);

        assertThrows(CampoEmptyOrNullException.class, () -> service.salvar(produtoDto));
    }
    
    @Test
    void salvar_DeveLancarExcecaoQuandoDataValidadeNula() {
        ProdutoDto produtoDto = criarProdutoDto();
        produtoDto.setDataValidade(null);

        assertThrows(CampoEmptyOrNullException.class, () -> service.salvar(produtoDto));
    }
    
    @Test
    void salvar_DeveLancarExcecaoQuandoDescricaoNula() {
        ProdutoDto produtoDto = criarProdutoDto();
        produtoDto.setDescricao(null);

        assertThrows(CampoEmptyOrNullException.class, () -> service.salvar(produtoDto));
    }
    
    @Test
    void salvar_DeveLancarExcecaoQuandoEstoqueNulo() {
        ProdutoDto produtoDto = criarProdutoDto();
        produtoDto.setEstoque(null);

        assertThrows(CampoEmptyOrNullException.class, () -> service.salvar(produtoDto));
    }
    
    @Test
    void salvar_DeveLancarExcecaoQuandoDescricaoVazia() {
        ProdutoDto produtoDto = criarProdutoDto();
        produtoDto.setDescricao("");

        assertThrows(CampoEmptyOrNullException.class, () -> service.salvar(produtoDto));
    }
    
    
    
    
    @Test
    void salvar_DeveLancarExcecaoQuandoFabricanteNulo() {
        // Dados de entrada
        ProdutoDto produtoDto = criarProdutoDto();
        produtoDto.setFabricante(null);

        // Verificação da exceção lançada
        assertThrows(CampoEmptyOrNullException.class, () -> service.salvar(produtoDto));
    }


    
    @Test
    void salvar_DeveLancarExcecaoQuandoDataValidadeMenorQueDataFabricacao() {
        // Dados de entrada
        ProdutoDto produtoDto = criarProdutoDto();
        produtoDto.setDataFabricacao(LocalDate.of(2024, 2, 21));
        produtoDto.setDataValidade(LocalDate.of(2024, 2, 20)); // Data de validade anterior à data de fabricação

        // Verificação da exceção lançada
        assertThrows(CampoEmptyOrNullException.class, () -> service.salvar(produtoDto));
    }
    
    @Test
    void buscarPeloIdLancaExcecao_SeIdNegativo() {
        // Dados de entrada
        int idNegativo = -1;

        // Verificação da exceção lançada
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.buscarPorId(idNegativo));
    }
    
   
    
    @Test
    void update_DeveAtualizarFabricante_QuandoFabricanteEncontrado() {
        // Dados de entrada
        int id = 3;
        Produto produtoExistente = criarProduto();
        ProdutoDto produtoDto = criarProdutoDto();
        ParceiroNegocio fabricanteExistente = produtoExistente.getFabricante();

        // Configuração do comportamento dos mocks
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoExistente));
        when(parceiroNegocioRepository.findById(produtoDto.getFabricante().getId()))
            .thenReturn(Optional.of(fabricanteExistente));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoExistente);

        // Chamada do método a ser testado
        ProdutoDto result = service.update(id, produtoDto);

        // Verificação dos resultados
        assertNotNull(result);
        assertEquals(produtoDto.getDescricao(), result.getDescricao());
        verify(produtoRepository).save(any(Produto.class));
    }
    
    @Test
    void update_DeveLancarExcecao_QuandoFabricanteNaoEncontrado() {
        // Dados de entrada
        int id = 3;
        Produto produtoExistente = criarProduto();
        ProdutoDto produtoDto = criarProdutoDto();

        // Configuração do comportamento dos mocks
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoExistente));
        when(parceiroNegocioRepository.findById(produtoDto.getFabricante().getId()))
            .thenReturn(Optional.empty());

        // Verificação da exceção lançada
        assertThrows(EntidadeNaoEncontradaException.class, () -> service.update(id, produtoDto));
    }

    
    @Test
    void update_DeveAtualizarFabricante_QuandoFabricanteNaoNulo() {
        // Dados de entrada
        int id = 1;
        Produto produtoExistente = criarProduto();
        ProdutoDto produtoDto = criarProdutoDto();
        ParceiroNegocio fabricanteExistente = produtoExistente.getFabricante();

        // Configuração do comportamento dos mocks
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoExistente));
        when(parceiroNegocioRepository.findById(produtoDto.getFabricante().getId()))
            .thenReturn(Optional.of(fabricanteExistente));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoExistente);

        // Chamada do método a ser testado
        ProdutoDto result = service.update(id, produtoDto);

        // Verificação dos resultados
        assertNotNull(result);
        assertEquals(produtoDto.getDescricao(), result.getDescricao());
        verify(produtoRepository).save(any(Produto.class));
    }
    
    @Test
    void update_DeveSalvarNovoFabricante_QuandoFabricanteNulo() {
        // Dados de entrada
        int id = 1;
        Produto produtoExistente = criarProduto();
        ProdutoDto produtoDto = criarProdutoDto();
        produtoDto.getFabricante().setId(null); // Fabricante nulo

        // Configuração do comportamento dos mocks
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoExistente));
        when(parceiroNegocioRepository.save(any(ParceiroNegocio.class))).thenReturn(produtoExistente.getFabricante());
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoExistente);

        // Chamada do método a ser testado
        ProdutoDto result = service.update(id, produtoDto);

        // Verificação dos resultados
        assertNotNull(result);
        assertEquals(produtoDto.getDescricao(), result.getDescricao());
        verify(produtoRepository).save(any(Produto.class));
    }
    

    
    
    @Test
    void atualizarEstoque_EntradaDeveAtualizarEstoqueECustoMedioCorretamente() {
        // Arrange
        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(BigDecimal.TEN);
        produto.setCustoMedio(BigDecimal.valueOf(10.00));

        NotaFiscal notaFiscal = new NotaFiscal(); // Inicialize o objeto NotaFiscal
        notaFiscal.setTipo(NotaFiscalTipo.ENTRADA);

        ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal();
        itemNotaFiscal.setProduto(produto);
        itemNotaFiscal.setQuantidade(BigDecimal.valueOf(5));
        itemNotaFiscal.setValorTotal(BigDecimal.valueOf(50.00));
        itemNotaFiscal.setNotaFiscal(notaFiscal); // Defina o objeto NotaFiscal dentro do ItemNotaFiscal

        BigDecimal novoEstoqueEsperado = BigDecimal.valueOf(15);
        BigDecimal novoCustoMedioEsperado = BigDecimal.valueOf(8.33).setScale(2, RoundingMode.HALF_UP);

        when(produtoRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ProdutoDto result = service.atualizarEstoque(itemNotaFiscal);

        // Assert
        assertEquals(novoEstoqueEsperado, result.getEstoque());
        assertEquals(novoCustoMedioEsperado, result.getCustoMedio());
    }
    
    

    
    

    private ProdutoDto criarProdutoDto() {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(1);
        dto.setNome("Nome do Produto");
        dto.setDescricao("Milho Teste");
        dto.setEstoque(new BigDecimal(10));
        dto.setDataFabricacao(LocalDate.of(2024, 2, 21));
        dto.setDataValidade(LocalDate.of(2025, 2, 21));
        ParceiroNegocio fabricante = new ParceiroNegocio();
        fabricante.setId(1);
        fabricante.setNome("Milho");
        dto.setFabricante(fabricante);
        return dto;
    }

    private Produto criarProduto() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setDescricao("Milho Teste");
        produto.setEstoque(new BigDecimal(10));
        produto.setDataFabricacao(LocalDate.of(2024, 2, 21));
        produto.setDataValidade(LocalDate.of(2025, 2, 21));
        ParceiroNegocio fabricante = new ParceiroNegocio();
        fabricante.setId(1);
        fabricante.setNome("Milho");
        produto.setFabricante(fabricante);
        return produto;
    }
    
    
    
    
    

}
	   




	    
	    
	    
	    
	    
	    
	
	    
