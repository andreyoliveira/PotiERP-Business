package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.FormaPagamento;

public class FormaPagamentoDao extends BaseDao {

	public FormaPagamentoDao() throws DaoException {
		super();
	}
	
	public FormaPagamento getPorNome(final FormaPagamento formaPagamento) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(FormaPagamento.GET_BY_NOME);
			query.setParameter("formaPagamento", formaPagamento.getNome().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"FORMAPAGAMENTO"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return FormaPagamento.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return FormaPagamento.class;
	}
}