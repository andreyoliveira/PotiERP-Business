package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.CalculoValeTransporte;

public class CalculoValeTransporteDao extends BaseDao {

	public CalculoValeTransporteDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return CalculoValeTransporte.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return CalculoValeTransporte.class;
	}
}