package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.HorariosJornada;

public class HorariosJornadaDao extends BaseDao {

	public HorariosJornadaDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return HorariosJornada.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return HorariosJornada.class;
	}
}