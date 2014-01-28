package br.com.potierp.dao;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.EnderecoRepositorio;

public class EnderecoRepositorioDao extends BaseDao {

	public EnderecoRepositorioDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return EnderecoRepositorio.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return EnderecoRepositorio.class;
	}
}