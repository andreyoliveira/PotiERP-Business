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
import br.com.potierp.model.Empresa;

public class EmpresaDao extends BaseDao {

	public EmpresaDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return Empresa.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Empresa> getEmpresasPorExemplo(final Empresa empresa) throws DaoException{
		try{
			Empresa emp = empresa != null? empresa:new Empresa();
			Criteria criteria = createCriteria(Empresa.class);
			criteria.add(Example.create(emp));
			return criteria.list();
		}catch(NoResultException nre){
			return null;
		}catch(PersistenceException pex) {
			throw new DaoException(pex.getMessage(), pex);
		}catch(Exception ex){
			throw new DaoException(ex.getMessage(), ex);
		}
	}
	
	public Empresa getByCnpj(final Empresa empresa) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Empresa.GET_BY_CNPJ);
			query.setParameter("cnpj", empresa.getCnpj());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"EMPRESA", "CNPJ"}, ex);
		}
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Empresa.class;
	}
}