package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Periodicidade;

public class PeriodicidadeDao extends BaseDao {

	public PeriodicidadeDao() throws DaoException {
		super();
	}
	
	@Override
	protected String getNamedQueryAll() {
		return Periodicidade.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll(){
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Periodicidade.class;
	}
}