package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.Afastamento;
import br.com.potierp.model.BaseEntity;

public class AfastamentoDao extends BaseDao{

	public AfastamentoDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return Afastamento.GET_ALL;
	}

	@Override
	protected String getNamedQueryCountAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Afastamento.class;
	}

}
