package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.ValeRefeicao;

public class ValeRefeicaoDao extends BaseDao {

	public ValeRefeicaoDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return ValeRefeicao.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return ValeRefeicao.class;
	}
}