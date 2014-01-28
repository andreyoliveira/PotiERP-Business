package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Adicional;
import br.com.potierp.model.BaseEntity;

public class AdicionalDao extends BaseDao {

	public AdicionalDao() throws DaoException {
		super();
	}
	
	public Adicional getPorNome(final Adicional adicional) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Adicional.GET_BY_NOME);
			query.setParameter("nome", adicional.getNome().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"ADICIONAL", "NOME"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return Adicional.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Adicional.class;
	}
}