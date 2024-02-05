package com.agrotis.trainees.crud.repository.wrapper;

import java.lang.reflect.ParameterizedType;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe wrapper para JpaRepository. Ela chama os métodos do repository e também
 * faz o log delas. Como os logs sao muito parecidos entre as services, foi
 * implementado esta classe para evitar repeticao de codigo/logica.
 * 
 * @param <Entity> Entidade manipulado no banco
 * @param <ID> Id da Entidade no banco.
 */
public class JpaRepositoryWrapper<Entity, ID> {

	protected final JpaRepository<Entity, ID> REPOSITORY;
	protected final String NOME_ENTITY;
	protected final Logger LOG;
	
	public JpaRepositoryWrapper(JpaRepository<Entity, ID> repository, Class<?> nomeLogger) {
		this.REPOSITORY = repository;
		this.NOME_ENTITY = getNomeEntity();
		this.LOG = LoggerFactory.getLogger(nomeLogger);
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
	
	private String getNomeEntity() {
		ParameterizedType tipo = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] argumentos = tipo.getActualTypeArguments();
		int entity = 0;
		Class<?> classeEntity = (Class<?>) argumentos[entity];
		return classeEntity.getSimpleName();
	}
	
	/**
	 * Metodo para maior legibilidade do argumento nomeLogger quando as filhas chamarem 
	 * o construtor da mae.
	 * 
	 * @param nome Classe para ser o nome do logger
	 * @return Mesma instancia do argumento
	 */
	protected static Class<?> nomeLogger(Class<?> nome) {
		return nome;
	}
}
