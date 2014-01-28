package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Verba;

public class VerbaDao extends BaseDao {

	public VerbaDao() throws DaoException {
		super();
	}
	
	public Verba getPorCodigo(final Verba verba) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Verba.GET_BY_CODIGO);
			query.setParameter("codigo", verba.getCodigo().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"VERBA", "CODIGO"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return Verba.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Verba.class;
	}
}