package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Desconto;

public class DescontoDao extends BaseDao {

	public DescontoDao() throws DaoException {
		super();
	}
	
	public Desconto getPorNome(final Desconto desconto) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Desconto.GET_BY_NOME);
			query.setParameter("nome", desconto.getNome().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"DESCONTO", "NOME"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return Desconto.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Desconto.class;
	}
}