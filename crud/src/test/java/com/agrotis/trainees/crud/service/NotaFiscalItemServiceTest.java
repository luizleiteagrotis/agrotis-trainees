package com.agrotis.trainees.crud.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.repository.NotaFiscalTipoRepository;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class NotaFiscalItemServiceTest {

    @InjectMocks
    NotaFiscalItemService service;

    @Mock
    NotaFiscalItemRepository repository;

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    ParceiroNegocioRepository parceiroRepository;

    @Mock
    NotaFiscalRepository notaFiscalRepository;

    @Mock
    NotaFiscalTipoRepository notaFiscalTipoRepository;

    NotaFiscalItem item = new NotaFiscalItem();
    Produto produto = new Produto();
    NotaFiscal nota = new NotaFiscal();
    ParceiroNegocio parceiro = new ParceiroNegocio();
    NotaFiscalTipo tipo = new NotaFiscalTipo();

    @BeforeEach
    public void setUp() {
        parceiro.setId(1);
        parceiro.setNome("AgroFertil LTDA");
        parceiro.setInscricaoFiscal("111111111");
        parceiro.setEndereco("Paraná");
        parceiro.setTelefone("98765-4321");

        produto.setId(2);
        produto.setDescricao("Fertilizante 4-14-8");
        produto.setIdParceiro(parceiro);
        produto.setDataFabricacao(LocalDate.of(2023, 12, 25));
        produto.setDataValidade(LocalDate.of(2024, 1, 15));
        produto.setEstoque(250);

        tipo.setId(1);
        tipo.setNome("Entrada");

        nota.setId(3);
        nota.setTipo(tipo);
        nota.setParceiro(parceiro);
        nota.setNumero(15000);
        nota.setDataEmissao(LocalDate.now());
        nota.setValorTotal(new BigDecimal(0));

        item.setId(4);
        item.setProduto(produto);
        item.setQuantidade(15);
        item.setPrecoUnitario(new BigDecimal(25));
        item.setValorTotal(item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())));
        item.setIdNota(nota);
    }
    // Testes para o salvar

    @Test
    void deveExistirProdutoENota() {

    }

}
