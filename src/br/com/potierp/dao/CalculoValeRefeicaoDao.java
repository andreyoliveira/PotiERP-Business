package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.CalculoValeRefeicao;

public class CalculoValeRefeicaoDao extends BaseDao {

	public CalculoValeRefeicaoDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return CalculoValeRefeicao.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return CalculoValeRefeicao.class;
	}
}