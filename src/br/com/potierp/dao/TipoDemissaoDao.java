package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.TipoDemissao;

public class TipoDemissaoDao extends BaseDao {

	public TipoDemissaoDao() throws DaoException {
		super();
	}
	
	public TipoDemissao getByNome(final TipoDemissao tipoDemissao) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(TipoDemissao.GET_BY_NOME);
			query.setParameter("nome", tipoDemissao.getNome().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"TIPODEMISSAO", "NOME"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return TipoDemissao.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return TipoDemissao.class;
	}
}