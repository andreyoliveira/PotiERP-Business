package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Demissao;

public class DemissaoDao extends BaseDao {

	public DemissaoDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return Demissao.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Demissao.class;
	}

}
