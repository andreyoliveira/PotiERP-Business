package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.TipoValeRefeicao;

public class TipoValeRefeicaoDao extends BaseDao {

	public TipoValeRefeicaoDao() throws DaoException {
		super();
	}
	
	public TipoValeRefeicao getPorCodigo(final TipoValeRefeicao tipoValeRefeicao) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(TipoValeRefeicao.GET_BY_CODIGO);
			query.setParameter("codigo", tipoValeRefeicao.getCodigo().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"TIPOVALEREFEICAO", "CODIGO"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return TipoValeRefeicao.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return TipoValeRefeicao.class;
	}
}