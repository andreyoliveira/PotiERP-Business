package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.ParametrosRh;

public class ParametrosRhDao extends BaseDao {

	public ParametrosRhDao() throws DaoException {
		super();
	}
	
	public ParametrosRh getPorNome(final ParametrosRh parametrosRh) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(ParametrosRh.GET_BY_NOME);
			query.setParameter("nome", parametrosRh.getNome().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"parametrosRh", "NOME"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return ParametrosRh.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return ParametrosRh.class;
	}
}