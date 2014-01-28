package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.Agencia;
import br.com.potierp.model.BaseEntity;

public class AgenciaDao extends BaseDao {

	public AgenciaDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return Agencia.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Agencia.class;
	}
}