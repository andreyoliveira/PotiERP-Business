package br.com.potierp.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Usuario;

public class UsuarioDAO extends BaseDao{

	public UsuarioDAO() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return Usuario.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Usuario.class;
	}
	
	public Usuario getUsuarioPorCredenciais(final Usuario usuario) throws DaoException, NoResultException{
		try{
			Query query = this.getEntityManager().createNamedQuery(Usuario.GET_USUARIO_POR_CREDENCIAIS);
			query.setParameter("username", usuario.getUsername());
			query.setParameter("password", usuario.getPassword());
			return (Usuario) query.getSingleResult();
		}catch(NoResultException nre){
			throw nre;
		}catch(Exception ex){
			throw new DaoException(ex.getMessage(), ex);
		}
	}
	
	public Usuario getPorUserName(final Usuario usuario) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Usuario.GET_BY_USERNAME);
			query.setParameter("username", usuario.getUsername().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"USUARIO", "USERNAME"}, ex);
		}
	}
	
}