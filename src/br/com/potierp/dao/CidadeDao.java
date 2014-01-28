package br.com.potierp.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Estado;

public class CidadeDao extends BaseDao {

	public CidadeDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return Cidade.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}
	
	public Cidade getPorNomeIdEstado(final Cidade cidade) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Cidade.GET_POR_NOME_ESTADO);
			query.setParameter("nome", cidade.getNome().trim());
			query.setParameter("idEstado", cidade.getEstado().getId());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"CIDADE", "NOME, ID_ESTADO"}, ex);
		}
	}
	
	public List<Cidade> getPorEstado(final Estado estado) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Cidade.GET_POR_ESTADO);
			query.setParameter("idEstado", estado.getId());
			return getResultList(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"CIDADE", "ID_ESTADO"}, ex);
		}
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Cidade.class;
	}
}