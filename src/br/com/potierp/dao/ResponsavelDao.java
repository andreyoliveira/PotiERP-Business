package br.com.potierp.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Responsavel;

/**
 * @author Doug
 *
 */
public class ResponsavelDao extends BaseDao{

	/**
	 * @throws DaoException
	 */
	public ResponsavelDao() throws DaoException {
		super();
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.core.BaseCadastro#getNamedQueryAll()
	 */
	@Override
	protected String getNamedQueryAll() {
		return Responsavel.GET_ALL;
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.core.BaseCadastro#getNamedQueryCountAll()
	 */
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.core.BaseCadastro#getClassEntity()
	 */
	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Responsavel.class;
	}
	
	public List<Responsavel> getPorFuncionario(Long idFuncionario) throws DaoException{
		try {
			Query query = getEntityManager().createNamedQuery(Responsavel.GET_POR_FUNCIONARIO);
			query.setParameter("idFuncionario", idFuncionario);
			return getResultList(query);
		} catch (Exception ex) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] {"RESPONSAVEL", "ID"}, ex);
		}
	}
	
}
