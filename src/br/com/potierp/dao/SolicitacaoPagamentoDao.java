package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.SolicitacaoPagamento;

public class SolicitacaoPagamentoDao extends BaseDao {

	public SolicitacaoPagamentoDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return SolicitacaoPagamento.GET_ALL;
	}

	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return SolicitacaoPagamento.class;
	}

}
