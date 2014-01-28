package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Beneficio;

public class BeneficioDao extends BaseDao {

	public BeneficioDao() throws DaoException {
		super();
	}
	
	public Beneficio getPorNome(final Beneficio beneficio) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Beneficio.GET_BY_NOME);
			query.setParameter("nome", beneficio.getNome().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"BENEFICIO", "NOME"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return Beneficio.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Beneficio.class;
	}
}