package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Encargo;

public class EncargoDao extends BaseDao {

	public EncargoDao() throws DaoException {
		super();
	}
	
	public Encargo getPorNome(final Encargo encargo) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Encargo.GET_BY_NOME);
			query.setParameter("nome", encargo.getNome().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"ENCARGO", "NOME"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return Encargo.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Encargo.class;
	}
}