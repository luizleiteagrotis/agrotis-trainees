package com.agrotis.trainees.crud.repository.wrapper;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Classe wrapper para JpaRepository. Ela chama os métodos do repository e também
 * faz o log delas. Como os logs sao muito parecidos entre as services, foi
 * implementado esta classe para evitar repeticao de codigo/logica.
 * 
 * @param <Service> Local onde está sendo utilizada a repository
 * @param <Entity> Entidade manipulado no banco
 * @param <ID> Id da Entidade no banco.
 * @param <Repository> JPARepository da Entidade.
 */
public class JpaRepositoryWrapper<Service, Entity, ID, Repository extends JpaRepository<Entity, ID>> {

	protected final Repository REPOSITORY;
	protected final String NOME_ENTITY;
	protected final Logger LOG;
	
	public JpaRepositoryWrapper(Repository repository, Class<Entity> entity, 
			Class<Service> service) {
		this.REPOSITORY = repository;
		this.NOME_ENTITY = entity.getSimpleName();
		this.LOG = LoggerFactory.getLogger(service);
	}
	
	public Entity salvar(Entity entity) {
		LOG.info("Tentanto salvar entidade {}", NOME_ENTITY);
		entity = REPOSITORY.save(entity);
		LOG.info("Entidade {} salva com sucesso", NOME_ENTITY);
		return entity;
	}
	
	public Entity buscarPor(ID idEntity) {
		LOG.info("Tentando buscar entidade {} com id {}", NOME_ENTITY, idEntity);
		Optional<Entity> entity = REPOSITORY.findById(idEntity);
		if (entity.isEmpty()) {
			lancarExceptionNaoEncontrado(idEntity);
		}
		LOG.info("Entidade {} com id {} encontrada com sucesso", NOME_ENTITY, idEntity);
		return entity.get();
	}
	
	public List<Entity> buscarTodos() {
		LOG.info("Tentando buscar todas as entidades {}", NOME_ENTITY);
		List<Entity> entidades = REPOSITORY.findAll();
		LOG.info("Entidades do tipo {} encontradas com sucesso. Quantidade: {}",
				NOME_ENTITY, 
				entidades.size());
		return entidades;
	}
	
	public void deletar(ID idEntity) {
		LOG.info("Tentando deletar {} com id {}", NOME_ENTITY, idEntity);
		REPOSITORY.deleteById(idEntity);
		LOG.info("Entidade {} com id {} deletada com sucesso", NOME_ENTITY, idEntity);
	}

	private void lancarExceptionNaoEncontrado(ID idEntity) {
		String mensagemErro = "Entidade " 
							+ NOME_ENTITY
							+ " com id "
							+ idEntity
							+ " nao encontrada";
		LOG.error(mensagemErro);
		throw new JpaRepositoryWrapperException(mensagemErro);
	}
}
