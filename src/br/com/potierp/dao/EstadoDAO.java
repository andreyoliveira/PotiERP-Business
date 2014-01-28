package br.com.potierp.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Estado;

public class EstadoDAO extends BaseDao{

	public EstadoDAO() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return Estado.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Estado.class;
	}
	
	public List<Estado> getEstadosPorPais(Long idPais) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Estado.GET_POR_PAIS);
			query.setParameter("idPais", idPais);
			return getResultList(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"ESTADO", "PAIS"}, ex);
		}
	}
	
}