package br.com.potierp.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Ferias;

/**
 * @author Andrey Oliveira
 */
public class FeriasDao extends BaseDao {

	public FeriasDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return Ferias.GET_ALL;
	}

	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Ferias.class;
	}
	
	public List<Ferias> getPorFuncionario(Long idFuncionario) throws DaoException {
		try {
			Query query = getEntityManager().createNamedQuery(Ferias.GET_POR_FUNCIONARIO);
			query.setParameter("idFuncionario", idFuncionario);
			return getResultList(query);
		} catch (Exception ex) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] {"FERIAS", "ID_FUNCIONARIO"}, ex);
		}
	}

}
