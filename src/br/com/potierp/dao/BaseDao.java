package br.com.potierp.dao;

import static br.com.potierp.infra.msg.MensagensExceptionEnum.ERRO_BUSCAR_OBJETO;
import static br.com.potierp.infra.msg.MensagensExceptionEnum.ERRO_ENTITY_MANAGER;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import br.com.potierp.core.BaseCadastro;
import br.com.potierp.infra.bd.BaseEntityPotiErp;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;

/**
 * Classe base que resolve parte(comum) do trabalho de persistência CRUD.
 *
 * @author 
 * 27/09/2010 14:27:41
 *    	   <p>
 *         $LastChangedBy: $
 *         <p>
 *         $LastChangedDate: $
 */
public abstract class BaseDao extends BaseCadastro implements IBaseDao {

	/**
	 * Logger.
	 */
	private static Logger logger = Logger.getLogger(BaseCadastro.class);

	/**
	 * EntityManager.
	 */
	private EntityManager em;

	/**
	 * Construtor que carrega o EntityManager.
	 *
	 * @throws DaoException
	 */
	public BaseDao() throws DaoException {
		try {
			this.em = FactoryEntityManager.getEntityManager();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new DaoException(ERRO_ENTITY_MANAGER, ex);
		}
	}
	
	/**
	 * Salva Objeto e efetiva a sua persist�ncia no reposit�rio.
	 * 
	 * @param e BaseEntity a ser Salva.
	 * @throws DaoException 
	 */
	@Override
	public <T extends BaseEntity> T salvarComFlush(final T e) throws DaoException {
		try {
			if (e.getId() != null) {
				if (!getEntityManager().contains(e))
					if (getEntityManager().find(e.getClass(), e.getId()) == null)
						throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO_REMOVIDO, new Object[]{getClassEntity().getSimpleName()});
				getEntityManager().merge(e);
			} else
				getEntityManager().persist(e);
			getEntityManager().flush();
			return e;
		} catch (EntityExistsException ee){
			logger.error(ee.getMessage(), ee);
			throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO_EXISTE, 
					new Object[]{getClassEntity().getSimpleName(), ee.getMessage()});
		} catch (OptimisticLockException oe) {
			logger.error(oe.getMessage(), oe);
			throw oe;
			/*throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO_CONCORRENCIA, 
					new Object[]{getClassEntity().getSimpleName(), oe.getMessage()});*/
		} catch (PersistenceException pe) {
			logger.error(pe.getMessage(), pe);
			throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO, new Object[]{getClassEntity().getSimpleName(), pe.getMessage()});
		} catch(Exception ex){
			logger.error(ex.getMessage(), ex);
			if(ex.getCause() instanceof ConstraintViolationException){
				throw new DaoException(MensagensExceptionEnum.ERRO_SALVAR_OBJETO_EXISTE, 
						new Object[]{getClassEntity().getSimpleName(), ex.getMessage()});
			}else
				throw new DaoException(ex.getMessage(), ex);
		}
	}

	/**
	 * Executa o metodo getSingleResult.
	 * @param <T>
	 * @param query
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "finally" })
	protected <T> T getSingleResult(final Query query) {
		T model = null;
		try {
			model = (T) query.getSingleResult();
		} catch (Exception ex) {
			logger.error(ERRO_BUSCAR_OBJETO.getMsg() + ex.getMessage(), ex);
		}finally{
			return model;
		}
	}

	/**
	 * (non-Javadoc).
	 * @see br.metodista.service.business.BaseCadastro#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager(){
		return this.em;
	}

	/**
	 * Session do hibernate para uso somente de criteria.
	 * @return the session
	 */
	protected Criteria createCriteria(final Class<? extends BaseEntityPotiErp> entityClass) {
		logger.info("### CLASSE "+ entityClass.getName() +" ==> USO DE CRITERIA INICIADO ###");
		Session session = (Session) this.em.getDelegate();
		return session.createCriteria(entityClass);
	}
}