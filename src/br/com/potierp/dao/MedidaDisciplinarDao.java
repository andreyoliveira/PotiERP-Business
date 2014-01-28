package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.MedidaDisciplinar;

/**
 * 
 * @author Andrey Oliveira
 * @since 19/03/2012
 *
 */
public class MedidaDisciplinarDao extends BaseDao {

	public MedidaDisciplinarDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return MedidaDisciplinar.GET_ALL;
	}

	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return MedidaDisciplinar.class;
	}

}
