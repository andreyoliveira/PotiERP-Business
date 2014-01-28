package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Setor;

public class SetorDao extends BaseDao {

	public SetorDao() throws DaoException {
		super();
	}
	
	public Setor getPorNome(final Setor setor) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Setor.GET_BY_NOME);
			query.setParameter("setor", setor.getNome().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"SETOR"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return Setor.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Setor.class;
	}
}