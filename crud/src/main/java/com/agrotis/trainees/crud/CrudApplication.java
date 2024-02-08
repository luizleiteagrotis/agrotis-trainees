package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

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
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setNome("Hadassa e Tatiane Esportes Ltda");
        parceiroNegocio.setInscricaoFiscal("10199709302155");
        parceiroNegocio.setEndereco("Moradias Bom Jesus");
        parceiroNegocio.setTelefone("41992477204");

        ParceiroNegocio parceiro361 = parceiroNegocioService.buscarPorId(361);
        ParceiroNegocio parceiro362 = parceiroNegocioService.buscarPorId(362);
        ParceiroNegocio parceiro363 = parceiroNegocioService.buscarPorId(363);
        ParceiroNegocio parceiro364 = parceiroNegocioService.buscarPorId(364);

        Produto produto366 = produtoService.buscarPorId(366);
        Produto produto367 = produtoService.buscarPorId(367);
        Produto produto370 = produtoService.buscarPorId(370);

        String tipoEntrada = TipoNotaFiscal.ENTRADA.getDescricao();
        String tipoSaida = TipoNotaFiscal.SAIDA.getDescricao();

        NotaFiscal notaFiscalS377 = notaFiscalService.buscarPorId(377);
        NotaFiscal notaFiscalE374 = notaFiscalService.buscarPorId(374);
        BigDecimal quantidade = new BigDecimal(9999);
        BigDecimal preco = BigDecimal.valueOf(60.95);
        ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal(notaFiscalS377, produto367, quantidade, preco);
        itemNotaFiscalService.salvar(itemNotaFiscal);

    }
}
