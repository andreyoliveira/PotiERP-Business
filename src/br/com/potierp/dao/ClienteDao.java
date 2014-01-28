package br.com.potierp.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.time.DateUtils;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Cliente;
import br.com.potierp.util.EntityUtils;

public class ClienteDao extends BaseDao {

	public ClienteDao() throws DaoException {
		super();
	}
	
	public Cliente getByCpfCnpj(final Cliente cliente) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Cliente.GET_BY_CPF_CNPJ);
			query.setParameter("cpfCnpj", cliente.getCpfCnpj());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"CLIENTE", "CPF/CNPJ"}, ex);
		}
	}
	
	public Cliente getByCodigo(final Cliente cliente) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Cliente.GET_BY_CODIGO);
			query.setParameter("codigo", cliente.getCodigo());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"CLIENTE", "CODIGO"}, ex);
		}
	}
	
	public List<Cliente> getAtivos() throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Cliente.GET_ATIVOS);
			query.setParameter("data", DateUtils.truncate(new Date(), Calendar.DATE));
			return getResultList(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"CLIENTE"}, ex);
		}
	}
	
	public List<Cliente> getAtivos(int firstRow, int lastRow) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Cliente.GET_ATIVOS);
			query.setParameter("data", DateUtils.truncate(new Date(), Calendar.DATE));
			query.setFirstResult(firstRow);
			query.setMaxResults(lastRow - firstRow);
			return getResultList(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"CLIENTE"}, ex);
		}
	}
	
	public List<Cliente> getInativos() throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Cliente.GET_INATIVOS);
			query.setParameter("data", DateUtils.truncate(new Date(), Calendar.DATE));
			return getResultList(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"CLIENTE"}, ex);
		}
	}
	
	public List<Cliente> getInativos(int firstRow, int lastRow) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Cliente.GET_INATIVOS);
			query.setParameter("data", DateUtils.truncate(new Date(), Calendar.DATE));
			query.setFirstResult(firstRow);
			query.setMaxResults(lastRow - firstRow);
			return getResultList(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"CLIENTE"}, ex);
		}
	}
	
	private String getNativeQueryGetAll() {
		StringBuffer nativeQuery = new StringBuffer();
		String barraN = "\n";
		
		nativeQuery.append("SELECT c.id, c.codigo, c.nomeFantasia ").append(barraN);
		nativeQuery.append("FROM cliente c ").append(barraN);
		
		return nativeQuery.toString();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Cliente> getAllNative() throws DaoException {
		try {
			String nativeQuerySugestao = getNativeQueryGetAll();
			Query query = getEntityManager().createNativeQuery(nativeQuerySugestao);
			List<Object[]> result = query.getResultList();
			List<Cliente> listCliente = new ArrayList<Cliente>();
			for(Object[] object : result) {
				Cliente cliente = new Cliente();
				cliente.setId(EntityUtils.getLong(object[0]));
				cliente.setCodigo(EntityUtils.getString(object[1]));
				cliente.setNomeFantasia(EntityUtils.getString(object[2]));
				listCliente.add(cliente);
			}
			return listCliente;
		} catch (Exception ex) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"CLIENTE"}, ex);
		}
	}

	protected String getNamedQueryAll() {
		return Cliente.GET_ALL;
	}
	
	protected String getNamedQueryCountAll(){
		return Cliente.COUNT_ALL;
	}

	protected Class<? extends BaseEntity> getClassEntity() {
		return Cliente.class;
	}

	public Number getTotalAtivos() throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Cliente.GET_COUNT_ATIVOS);
			query.setParameter("data", DateUtils.truncate(new Date(), Calendar.DATE));
			return (Number) query.getSingleResult();
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_TOTAL_ENTIDADE, new Object[]{"CLIENTE"}, ex);
		}
	}

	public Number getTotalInativos() throws DaoException {
		try{
			Query query = getEntityManager().createNamedQuery(Cliente.GET_COUNT_INATIVOS);
			query.setParameter("data", DateUtils.truncate(new Date(), Calendar.DATE));
			return (Number) query.getSingleResult();
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_TOTAL_ENTIDADE, new Object[]{"CLIENTE"}, ex);
		}
	}
	
}