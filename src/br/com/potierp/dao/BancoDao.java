package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.Banco;
import br.com.potierp.model.BaseEntity;

public class BancoDao extends BaseDao{

	public BancoDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return Banco.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Banco.class;
	}
}