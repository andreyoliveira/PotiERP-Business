package br.com.potierp.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Fornecedor;

public class FornecedorDao extends BaseDao {

	public FornecedorDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return Fornecedor.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> getFornecedoresPorExemplo(final Fornecedor fornecedor) throws DaoException{
		try{
			Fornecedor forn = fornecedor != null? fornecedor:new Fornecedor();
			Criteria criteria = createCriteria(Fornecedor.class);
			criteria.add(Example.create(forn));
			return criteria.list();
		}catch(NoResultException nre){
			return null;
		}catch(PersistenceException pex) {
			throw new DaoException(pex.getMessage(), pex);
		}catch(Exception ex){
			throw new DaoException(ex.getMessage(), ex);
		}
	}
	
	public Fornecedor getByCnpj(final Fornecedor fornecedor) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Fornecedor.GET_BY_CNPJ);
			query.setParameter("cpfCnpj", fornecedor.getCpfCnpj());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"FORNECEDOR", "CPFCNPJ"}, ex);
		}
	}
	
	public Fornecedor getByCodigo(final Fornecedor fornecedor) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Fornecedor.GET_BY_CODIGO);
			query.setParameter("codigo", fornecedor.getCodigo());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"FORNECEDOR", "CODIGO"}, ex);
		}
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Fornecedor.class;
	}
}