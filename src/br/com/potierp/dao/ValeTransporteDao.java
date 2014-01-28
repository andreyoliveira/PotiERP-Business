package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.ValeTransporte;

public class ValeTransporteDao extends BaseDao {

	public ValeTransporteDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return ValeTransporte.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return ValeTransporte.class;
	}
}