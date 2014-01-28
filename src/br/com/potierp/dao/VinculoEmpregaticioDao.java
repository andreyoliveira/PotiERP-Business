package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.VinculoEmpregaticio;

public class VinculoEmpregaticioDao extends BaseDao {

	public VinculoEmpregaticioDao() throws DaoException {
		super();
	}
	
	public VinculoEmpregaticio getPorCodigo(final VinculoEmpregaticio vinculoEmpregaticio) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(VinculoEmpregaticio.GET_BY_CODIGO);
			query.setParameter("codigo", vinculoEmpregaticio.getCodigo());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"VINCULOEMPREGATICIO"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return VinculoEmpregaticio.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return VinculoEmpregaticio.class;
	}
}