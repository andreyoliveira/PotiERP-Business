package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.DataProgramacaoVisita;

public class DataProgramacaoVisitaDao extends BaseDao {
	
	public DataProgramacaoVisitaDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return DataProgramacaoVisita.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}
	
	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return DataProgramacaoVisita.class;
	}
}