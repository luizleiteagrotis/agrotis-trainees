package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.helper.TipoNotaFiscal;
import com.agrotis.trainees.crud.service.ItemNotaFiscalService;
import com.agrotis.trainees.crud.service.NotaFiscalService;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

    private final NotaFiscalTipoService notaFiscalTipoService;
    private final ParceiroNegocioService parceiroNegocioService;
    private final ProdutoService produtoService;
    private final NotaFiscalService notaFiscalService;
    private final ItemNotaFiscalService itemNotaFiscalService;

    public CrudApplication(NotaFiscalTipoService notaFiscalTipoService, ParceiroNegocioService parceiroNegocioService,
                    ProdutoService produtoService, NotaFiscalService notaFiscalService,
                    ItemNotaFiscalService itemNotaFiscalService) {
        this.notaFiscalTipoService = notaFiscalTipoService;
        this.parceiroNegocioService = parceiroNegocioService;
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;
        this.itemNotaFiscalService = itemNotaFiscalService;
    }

    public static void main(String[] args) {
        LOG.info("Iniciado com sucesso!");
        SpringApplication.run(CrudApplication.class, args);
    }

    @Override

    public void run(String... args) {

        String tipoEntrada = TipoNotaFiscal.ENTRADA.getDescricao();
        String tipoSaida = TipoNotaFiscal.SAIDA.getDescricao();

        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setNome("Antonio e Levi Filmagens ME");
        parceiroNegocio.setInscricaoFiscal("76826798000197");
        parceiroNegocio.setEndereco("Praça Candida Maria da Silva");
        parceiroNegocio.setTelefone("11983090235");
        parceiroNegocioService.salvar(parceiroNegocio);

        // ParceiroNegocio parceiroNegocioId =
        // parceiroNegocioService.buscarPorId(235);

        Produto produto = new Produto("Laranja", Date.valueOf("2024-01-01"), Date.valueOf("2024-06-06"), parceiroNegocio);
        produto.setDescricao("Produto de alta qualidade");
        produtoService.salvar(produto);

        // Produto produtoPorId = produtoService.buscarPorId(243);

        // NOTA FISCAL ENTRADA
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setData(Date.valueOf("2024-01-10"));
        notaFiscal.setNumero(101011);
        notaFiscal.setParceiroNegocio(parceiroNegocio);
        notaFiscal.setTipo(tipoEntrada);
        notaFiscalService.salvar(notaFiscal);

        // NotaFiscal notaFiscalId = notaFiscalService.buscarPorId(247);

        // NOTA FISCAL SAIDA
        NotaFiscal notaFiscalSaida = new NotaFiscal();
        notaFiscalSaida.setData(Date.valueOf("2024-01-10"));
        notaFiscalSaida.setNumero(101011);
        notaFiscalSaida.setParceiroNegocio(parceiroNegocio);
        notaFiscalSaida.setTipo(tipoSaida);
        notaFiscalService.salvar(notaFiscalSaida);

        // item nota entrada
        ItemNotaFiscal itemNota = new ItemNotaFiscal(notaFiscal, produto, 100, 50);
        itemNotaFiscalService.salvar(itemNota);

        // item nota saida
        ItemNotaFiscal itemNotaSaida = new ItemNotaFiscal(notaFiscalSaida, produto, 99, 50);
        itemNotaFiscalService.salvar(itemNotaSaida);

    }
}
