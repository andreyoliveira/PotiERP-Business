package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Cargo;

public class CargoDao extends BaseDao {

	public CargoDao() throws DaoException {
		super();
	}
	
	public Cargo getPorNome(final Cargo cargo) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Cargo.GET_BY_NOME);
			query.setParameter("cargo", cargo.getCargo().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"CARGO"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return Cargo.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Cargo.class;
	}
}