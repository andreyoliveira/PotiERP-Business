package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.TipoPerfilErp;

public class TipoPerfilDao extends BaseDao {
	
	private static final String PERFIL = "Perfil";
	
	private static final String DESCRICAO = "Descrição";

	/**
	 * Parâmetro de NamedQuery para busca de Perfis por Descrição.
	 */
	private static final String PARAM_DESCRICAO = "descricao";

	public TipoPerfilDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return TipoPerfilErp.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return TipoPerfilErp.class;
	}
	
	public TipoPerfilErp getByDescricao(final String descricao) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(TipoPerfilErp.GET_BY_DESCRICAO);
			query.setParameter(PARAM_DESCRICAO, descricao);
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{PERFIL, DESCRICAO}, ex);
		}
	}

}