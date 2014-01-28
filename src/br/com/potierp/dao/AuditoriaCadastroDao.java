package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.AuditoriaCadastro;
import br.com.potierp.model.BaseEntity;

/**
 * Classe responsável pelo gerenciamento da entidade AuditoriaCadastro.
 * 
 * @author 
 *  	   <p>
 *         $LastChangedBy: renan $
 *         <p>
 *         $LastChangedDate: 2010-10-04 23:28:14 -0300 (Seg, 04 Out 2010) $
 */
public class AuditoriaCadastroDao extends BaseDao {

	/**
	 * Construtor default.
	 * @throws DaoException
	 */
	public AuditoriaCadastroDao() throws DaoException {
		super();
	}

	/**
	 * (non-Javadoc).
	 * @see br.metodista.service.business.BaseCadastro#getClassEntity()
	 */
	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return AuditoriaCadastro.class;
	}

	/**
	 * (non-Javadoc).
	 * @see br.metodista.service.business.BaseCadastro#getNamedQueryAll()
	 */
	@Override
	protected String getNamedQueryAll() {
		return AuditoriaCadastro.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}
}