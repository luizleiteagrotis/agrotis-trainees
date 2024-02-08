package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);

    private final NotaFiscalRepository repository;

    public NotaFiscalService(NotaFiscalRepository repository) {
        this.repository = repository;
    }

    public NotaFiscal salvar(NotaFiscal notaFiscal) {
        return repository.save(notaFiscal);
    }

    public NotaFiscal buscarPorId(Integer integer) {
        return repository.findById(integer).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para id {}.", integer);
            return null;
        });
    }

    public NotaFiscal atualizar(Integer id, NotaFiscal notaFiscal) {
        // Verifica se a nota fiscal com o ID especificado existe no banco de
        // dados
        NotaFiscal existingNotaFiscal = repository.findById(id).orElseGet(() -> {
            LOG.info("Não foi possível encontrar a nota fiscal pelo ID {}", id);
            return null;
        });

        if (existingNotaFiscal != null) {
            // Atualiza os campos relevantes com os valores da nova nota fiscal
            existingNotaFiscal.setDataEmissao(notaFiscal.getDataEmissao());
            existingNotaFiscal.setNumero(notaFiscal.getNumero());
            existingNotaFiscal.setNotaFiscalTipo(notaFiscal.getNotaFiscalTipo());
            existingNotaFiscal.setParceiroNegocio(notaFiscal.getParceiroNegocio());

            // Salva a nota fiscal atualizada no banco de dados
            return repository.save(existingNotaFiscal);
        } else {
            // Se a nota fiscal com o ID especificado não existe, você pode
            // lidar de acordo com sua lógica de negócios
            LOG.error("Nota Fiscal não encontrada para id {}.", id);
            return null; // Ou lança uma exceção ou retorna null, dependendo da
                         // lógica desejada
        }
    }

    public List<NotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Nota Fiscal deletada com sucesso. ID: {}", id);
    }

    public void atualizarValorTotal(Integer id, Double valorTotalItem) {
        // TODO Auto-generated method stub

    }

}
