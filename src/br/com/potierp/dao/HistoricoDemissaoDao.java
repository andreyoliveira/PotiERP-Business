package br.com.potierp.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.HistoricoDemissao;

/**
 * @author Andrey Oliveira
 */
public class HistoricoDemissaoDao extends BaseDao {

	public HistoricoDemissaoDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return HistoricoDemissao.GET_ALL;
	}

	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return HistoricoDemissao.class;
	}
	
	public List<HistoricoDemissao> getPorFuncionario(final Long idFuncionario) throws DaoException {
		try {
			Query query = getEntityManager().createNamedQuery(HistoricoDemissao.GET_POR_FUNCIONARIO);
			query.setParameter("idFuncionario", idFuncionario);
			return getResultList(query);
		} catch (Exception ex) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] {"HISTORICODEMISSAO", "ID_FUNCIONARIO"}, ex);
		}
	}

}
