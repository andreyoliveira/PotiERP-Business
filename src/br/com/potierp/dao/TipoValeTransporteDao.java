package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.TipoValeTransporte;

public class TipoValeTransporteDao extends BaseDao {

	public TipoValeTransporteDao() throws DaoException {
		super();
	}
	
	public TipoValeTransporte getPorCodigo(final TipoValeTransporte tipoValeTransporte) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(TipoValeTransporte.GET_BY_CODIGO);
			query.setParameter("codigo", tipoValeTransporte.getCodigo().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"TIPOVALETRANSPORTE", "CODIGO"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return TipoValeTransporte.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return TipoValeTransporte.class;
	}
}