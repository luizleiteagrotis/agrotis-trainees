package com.agrotis.trainees.crud.repository.notafiscal.cabecalho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapper;
import com.agrotis.trainees.crud.service.NotaFiscalService;

@Repository
public class CabecalhoNotaRepository extends JpaRepositoryWrapper<
										  NotaFiscalService, 
										  CabecalhoNota, 
										  Long, 
										  CabecalhoNotaJpaRepository> {

	@Autowired
	public CabecalhoNotaRepository(CabecalhoNotaJpaRepository repository, 
			Class<CabecalhoNota> entity,
			Class<NotaFiscalService> service) {
		super(repository, entity, service);
	}
	
	@Override
	public CabecalhoNota salvar(CabecalhoNota cabecalho) {
		if (existeInstanciaCom(cabecalho.getTipo(), cabecalho.getNumero())) {
			String mensagemErro = "Ja existe " 
									+ NOME_ENTITY 
									+ " com tipo " 
									+ cabecalho.getTipo()
									+ " e numero "
									+ cabecalho.getNumero();
			LOG.error("{} com tipo {} e numero {} nao foi salva", 
					NOME_ENTITY, cabecalho.getTipo().getNome(), cabecalho.getNumero());
			throw new CabecalhoNotaRepositoryException(mensagemErro);
		}
		return super.salvar(cabecalho);
	}
	
	public boolean existeInstanciaCom(NotaFiscalTipo tipo, long numero) {
		LOG.info("Tentando encontrar {} com tipo {} e numero {}", 
				NOME_ENTITY, tipo.getNome(), numero);
		boolean encontrado = REPOSITORY.existsByTipoAndNumero(tipo, numero);
		if (encontrado) {
			LOG.info("Encontrado {} com tipo {} e numero {}", 
					NOME_ENTITY, tipo.getNome(), numero);
		} else {
			LOG.error("Nao encontrado {} com tipo {} e numero {}", 
					NOME_ENTITY, tipo.getNome(), numero);
		}
		return encontrado;
	}
}
