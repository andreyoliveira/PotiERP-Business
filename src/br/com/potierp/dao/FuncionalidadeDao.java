package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Funcionalidade;

public class FuncionalidadeDao extends BaseDao {

	public FuncionalidadeDao() throws DaoException {
		super();
	}
	
	@Override
	protected String getNamedQueryAll() {
		return Funcionalidade.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Funcionalidade.class;
	}

}
