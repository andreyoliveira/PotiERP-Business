package br.com.potierp.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.HistoricoComercial;

public class HistoricoComercialDao extends BaseDao {

	public HistoricoComercialDao() throws DaoException {
		super();
	}
	
	public List<HistoricoComercial> getPorCliente(final Long idCliente) throws DaoException{
		try {
			Query query = getEntityManager().createNamedQuery(HistoricoComercial.GET_BY_CLIENTE);
			query.setParameter("idCliente", idCliente);
			return getResultList(query);
		}catch (Exception ex) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] {"HISTORICOCOMERCIAL", "ID_CLIENTE"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return HistoricoComercial.GET_ALL;
	}

	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return HistoricoComercial.class;
	}

}
