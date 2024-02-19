package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
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

        Scanner scan = new Scanner(System.in);
        System.out.println(
                        "Insira um número para mexer com o CRUD do tipo nota fiscal , parceiro de negócio, produto, nota fiscal ou item nota fiscal: 1 - 2");
        int opcao = scan.nextInt();

        if (opcao == 1) {
            NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
            notaFiscalTipo.setNome("nomeTeste");
            NotaFiscalTipo notaFiscalTipo2 = notaFiscalTipoService.salvar(notaFiscalTipo);
            LOG.info("id inserido: {}", notaFiscalTipo2.getId());
            NotaFiscalTipo porId = notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
            LOG.info("Busca por id. Nome {} id {} ", porId.getNome(), porId.getId());
            List<NotaFiscalTipo> todosSalvos = notaFiscalTipoService.listarTodos();
            LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());
            NotaFiscalTipo porNome = notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());
            porNome.setNome("nomeAlterado");
            notaFiscalTipoService.salvar(porNome);
            LOG.info("Busca por nome. Nome {} id {} ", porNome.getNome(), porNome.getId());
            // notaFiscalTipoService.deletarPorId(porId.getId());
            notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
            notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());
        }
        if (opcao == 2) {

            /*
             * ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
             * 
             * parceiroNegocio.setNome("Empresa do Ciclano");
             * parceiroNegocio.setInscricaoFiscal("555777111");
             * parceiroNegocio.setTelefone("41 9 8888 2222");
             * 
             * ParceiroNegocio parceiroNegocio2 =
             * parceiroNegocioService.salvar(parceiroNegocio);
             * LOG.info("id inserido: {}", parceiroNegocio2.getId());
             */

            List<ParceiroNegocio> parceirosSalvos = parceiroNegocioService.listarTodos();
            for (ParceiroNegocio parceiro : parceirosSalvos) {
                System.out.println(parceiro);
            }

            ParceiroNegocio parceiroBuscaID = parceiroNegocioService.buscarPorId(parceirosSalvos.get(0).getId());
            // parceiroNegocioService.deletarPorId(parceiroBuscaID.getId());
            LOG.info("Parceiro {} encontrado por id.", parceiroBuscaID);
            ParceiroNegocio parceiroBuscaIF = parceiroNegocioService
                            .buscarPorInscricaoFiscal(parceirosSalvos.get(1).getInscricaoFiscal());
            LOG.info("Parceiro {} encontrado por inscrição fiscal.", parceiroBuscaIF);
            ParceiroNegocio parceiroBuscaNome = parceiroNegocioService.buscarPorNome(parceirosSalvos.get(2).getNome());
            LOG.info("Parceiro {} encontrado por nome.", parceiroBuscaNome);

        }

    }
}
