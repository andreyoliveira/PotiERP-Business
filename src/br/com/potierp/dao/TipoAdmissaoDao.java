package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.TipoAdmissao;

public class TipoAdmissaoDao extends BaseDao {

	public TipoAdmissaoDao() throws DaoException {
		super();
	}
	
	public TipoAdmissao getByNome(final TipoAdmissao tipoAdmissao) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(TipoAdmissao.GET_BY_NOME);
			query.setParameter("nome", tipoAdmissao.getNome().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"TIPOADMISSAO", "NOME"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return TipoAdmissao.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return TipoAdmissao.class;
	}
}