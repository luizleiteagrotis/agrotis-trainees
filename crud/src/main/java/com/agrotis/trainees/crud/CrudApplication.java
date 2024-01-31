package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

    private final NotaFiscalTipoService notaFiscalTipoService;
    private final ParceiroNegocioService parceiroNegocioService;

    public CrudApplication(NotaFiscalTipoService notaFiscalTipoService, ParceiroNegocioService parceiroNegocioService) {
        this.notaFiscalTipoService = notaFiscalTipoService;
        this.parceiroNegocioService = parceiroNegocioService;
    }

    public static void main(String[] args) {
        LOG.info("Iniciado com sucesso!");
        SpringApplication.run(CrudApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
            parceiroNegocio.setNome("Hadassa e Tatiane Esportes Ltda");
            parceiroNegocio.setInscricaoFiscal("11111704001134");
            // parceiroNegocio.setEndereco("Moradias Bom Jesus");
            parceiroNegocio.setTelefone("41992477204");
            // Create
            ParceiroNegocio parceiroNegocio2 = parceiroNegocioService.salvar(parceiroNegocio);
            LOG.info("id inserio: {}", parceiroNegocio2.getId());

            // select
            ParceiroNegocio porId = parceiroNegocioService.buscarPorId(parceiroNegocio2.getId());
            LOG.info("Buscar por id. Nome {} id {}", porId.getNome(), porId.getId());
            ParceiroNegocio porInscricaoFiscal = parceiroNegocioService
                            .buscarPorInscricaoFiscal(parceiroNegocio2.getInscricaoFiscal());
            LOG.info("Buscar por if. Nome {} inscricao fiscal {}", porId.getNome(), porInscricaoFiscal.getInscricaoFiscal());
            List<ParceiroNegocio> listarTodos = parceiroNegocioService.listarTodos();
            for (ParceiroNegocio parceiro : listarTodos) {
                LOG.info("Buscar por todos. Nome {} inscricao fiscal {}", parceiro.getNome(), parceiro.getInscricaoFiscal());
            }
        } catch (IllegalArgumentException exp) {
            System.out.println("Houve um erro ao tentar salvar no banco");
        }
        ;

        // ParceiroNegocio atualizar =
        // parceiroNegocioService.atualizar("21212704001134", "Hadassa e Tatiane
        // Esportes Ltda","00000704001134", "Moradias Bom Jesus","41992477204");
        // LOG.info("Atualizado. Nome {} inscricao fiscal {}",
        // atualizar.getNome(), atualizar.getInscricaoFiscal());
        //
        parceiroNegocioService.deletar(54);
        //

        // NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
        // notaFiscalTipo.setNome("nomeTeste");
        // NotaFiscalTipo notaFiscalTipo2 =
        // notaFiscalTipoService.salvar(notaFiscalTipo);
        // LOG.info("id inserido: {}", notaFiscalTipo2.getId());
        // NotaFiscalTipo porIdNF =
        // notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
        // LOG.info("Busca por id. Nome {} id {} ", porIdNF.getNome(),
        // porIdNF.getId());
        // List<NotaFiscalTipo> todosSalvos =
        // notaFiscalTipoService.listarTodos();
        // LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());
        // NotaFiscalTipo porNome =
        // notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());
        // porNome.setNome("nomeAlterado");
        // notaFiscalTipoService.salvar(porNome);
        // LOG.info("Busca por nome. Nome {} id {} ", porNome.getNome(),
        // porNome.getId());
        //
        // //notaFiscalTipoService.deletarPorId(porId.getId());
        // notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
        // notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());

    }
}
