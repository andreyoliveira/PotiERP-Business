package br.com.potierp.core;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.util.EntityUtils;

/**
 * Classe abstrata que resolve parte(comum) do trabalho de persistência CRUD, sob o framework JEE EJB3.<br>
 * Agrega utilização do EntityManager, é extensivel a Session Bean, Interceptors ou elementos relacionados<br>
 * a este ambiente.
 * 
 * @author
 *         <p>
 *         $LastChangedBy: felipe $
 *         <p>
 *         $LastChangedDate: 2010-10-04 23:42:28 -0300 (seg, 04 out 2010) $
 */
public abstract class BaseCadastro {

	/**
	 * Log da classe.
	 */
	private static Logger log = Logger.getLogger(BaseCadastro.class);


	/**
	 * @return Referência para o EntityManager.
	 */
	protected abstract EntityManager getEntityManager();

	/**
	 * @return NamedQuery para montar a <i>Query</i> da operação getAll().
	 */
	protected abstract String getNamedQueryAll();
	
	/**
	 * @return NamedQuery para montar a <i>Query</i> da operação getCountAll().
	 */
	protected abstract String getNamedQueryCountAll();
	
	/**
	 * @return NamedQuery para montar a <i>Query</i> da operação getCountAll().
	 *
	protected abstract String getNamedQueryCountAll();*/

	/**
	 * @return Class do objeto persistente. Utilizado no metodo find de EntityManager.
	 */
	protected abstract Class<? extends BaseEntity> getClassEntity();

	/**
	 * Salva Objeto no repositório.
	 * @param e
	 * @return
	 * @throws DaoException 
	 */
	public <T extends BaseEntity> T salvar(final T e) throws DaoException {	
		try {
			if (e.getId() != null) {
				if (!getEntityManager().contains(e))
					if (getEntityManager().find(e.getClass(), e.getId()) == null)
						throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO_REMOVIDO, new Object[]{getClassEntity().getSimpleName()});
				return getEntityManager().merge(e);
			} else
				getEntityManager().persist(e);
			return e;
		} catch (EntityExistsException ee){
			log.error(ee.getMessage(), ee);
			throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO_EXISTE, 
					new Object[]{getClassEntity().getSimpleName(), ee.getMessage()});
		} catch (OptimisticLockException oe) {
			log.error(oe.getMessage(), oe);
			throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO_CONCORRENCIA, 
					new Object[]{getClassEntity().getSimpleName(), oe.getMessage()});
		} catch (PersistenceException pe) {
			log.error(pe.getMessage(), pe);
			throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO, new Object[]{getClassEntity().getSimpleName(), pe.getMessage()});
		}
	}

	/**
	 * Salva Objeto e efetiva a sua persistência no repositório.
	 * 
	 * @param e BaseEntity a ser Salva.
	 * @throws DaoException 
	 */
	public <T extends BaseEntity> T salvarComFlush(final T e) throws DaoException {
		try {
			if (e.getId() != null) {
				if (!getEntityManager().contains(e))
					if (getEntityManager().find(e.getClass(), e.getId()) == null)
						throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO_REMOVIDO, new Object[]{getClassEntity().getSimpleName()});
				return getEntityManager().merge(e);
			} else
				getEntityManager().persist(e);
			getEntityManager().flush();
			return e;
		} catch (EntityExistsException ee){
			log.error(ee.getMessage(), ee);
			throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO_EXISTE, 
					new Object[]{getClassEntity().getSimpleName(), ee.getMessage()});
		} catch (OptimisticLockException oe) {
			log.error(oe.getMessage(), oe);
			throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO_CONCORRENCIA, 
					new Object[]{getClassEntity().getSimpleName(), oe.getMessage()});
		} catch (PersistenceException pe) {
			log.error(pe.getMessage(), pe);
			throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO, new Object[]{getClassEntity().getSimpleName(), pe.getMessage()});
		} catch(Exception ex){
			log.error(ex.getMessage(), ex);
			if(ex.getCause() instanceof ConstraintViolationException){
				throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO_EXISTE, 
						new Object[]{getClassEntity().getSimpleName(), ex.getMessage()});
			}else
				throw new DaoException(ex.getMessage(), ex);
		}
	}

	/**
	 * Exclui a Entity diretamente pelo Objeto entity, sem realizar busca.
	 * @param e
	 * @throws DaoException 
	 */
	public void excluir(final BaseEntity e) throws DaoException {
		try {
			BaseEntity temp = getEntityManager().find(e.getClass(), e.getId());
			if (temp == null)
				throw new DaoException(MensagensExceptionEnum.ERRO_EXCLUIR_OBJETO_REMOVIDO, new Object[]{getClassEntity().getSimpleName()});
			getEntityManager().remove(temp);
			getEntityManager().flush();
		} catch (EntityExistsException ee) {
			log.error(ee.getMessage(), ee);
			throw new DaoException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE_RELACIONAMENTO, 
					new Object[]{getClassEntity().getSimpleName(), ee.getMessage()});
		} catch (PersistenceException pe) {
			log.error(pe.getMessage(), pe);
			throw new DaoException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{getClassEntity().getSimpleName(), pe.getMessage()});
		}
	}

	/**
	 * Efetua busca do Tipo por Key.
	 * @param k
	 *            Key
	 * @return Entity
	 * @throws DaoException 
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> T getByPrimaryKey(final Long k) throws DaoException {
		try {
			return (T)getEntityManager().find(getClassEntity(), k);
		} catch (PersistenceException pe) {
			log.error(pe.getMessage(), pe);
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{getClassEntity().getSimpleName(), pe.getMessage()});
		}
	}

	/**
	 * Efetua busca de Todos os Objetos armazenados.
	 * 
	 * @return Coleção de BaseEntity.
	 * @throws DaoException 
	 */	
	public <T extends BaseEntity> Collection<T> getAll() throws DaoException {
		try {
			Query query = getEntityManager().createNamedQuery(getNamedQueryAll());
			return getResultList(query);
		} catch (PersistenceException pe) {
			log.error(pe.getMessage(), pe);
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{getClassEntity().getSimpleName(), pe.getMessage()});
		}
	}
	
	public <T extends BaseEntity> Number getCountAll() throws DaoException {
		try {
			Query query = getEntityManager().createNamedQuery(getNamedQueryCountAll());
			return (Number) query.getSingleResult();
		} catch (PersistenceException pe) {
			log.error(pe.getMessage(), pe);
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_TOTAL_ENTIDADE, new Object[]{getClassEntity().getSimpleName(), pe.getMessage()});
		}
	}
	
	public <T extends BaseEntity> Collection<T> getAll(final int firstRow, final int lastRow) throws DaoException{
		try {
			Query query = getEntityManager().createNamedQuery(getNamedQueryAll());
			query.setFirstResult(firstRow);
			query.setMaxResults(lastRow - firstRow);
			return getResultList(query);
		} catch (PersistenceException pe) {
			log.error(pe.getMessage(), pe);
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{getClassEntity().getSimpleName(), pe.getMessage()});
		}
	}

	/**
	 * Executa o método getResultList de forma padrao sem se preocupar com cast.
	 * @param <T>
	 * @param query
	 * @return
	 */
	protected <T> List<T> getResultList(final Query query) {
		EntityUtils e = new EntityUtils();
		return e.getResultList(query);
	}

	/**
	 * @param entity
	 * @return Nome do Objeto, conforme a classe.
	 */
	protected String getEntityName(final BaseEntity entity) {
		return new EntityUtils().getEntityName(entity);
	}
}