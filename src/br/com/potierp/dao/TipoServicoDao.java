package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.TipoServico;

/**
 * 
 * @author Andrey Oliveira
 *
 */
public class TipoServicoDao extends BaseDao {

	public TipoServicoDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return TipoServico.GET_ALL;
	}

	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return TipoServico.class;
	}

}
