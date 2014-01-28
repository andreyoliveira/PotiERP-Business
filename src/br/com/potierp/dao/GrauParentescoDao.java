package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.GrauParentesco;

public class GrauParentescoDao extends BaseDao {

	public GrauParentescoDao() throws DaoException {
		super();
	}
	
	public GrauParentesco getPorDescricao(final String descricao) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(GrauParentesco.GET_POR_DESCRICAO);
			query.setParameter("descricao", descricao);
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"GrauParentesco", "Descricao"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return GrauParentesco.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return GrauParentesco.class;
	}
}