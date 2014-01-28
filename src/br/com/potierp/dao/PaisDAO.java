package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Pais;

public class PaisDAO extends BaseDao{

	public PaisDAO() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return Pais.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Pais.class;
	}
	
	public Pais getPaisPorSigla(String sigla) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Pais.GET_POR_SIGLA);
			query.setParameter("sigla", sigla);
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"ESTADO", "PAIS"}, ex);
		}
	}
	
}