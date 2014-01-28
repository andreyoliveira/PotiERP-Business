package br.com.potierp.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.ProgramacaoServico;

/**
 * 
 * @author Andrey Oliveira
 *
 */
public class ProgramacaoServicoDao extends BaseDao {

	public ProgramacaoServicoDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return ProgramacaoServico.GET_ALL;
	}

	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return ProgramacaoServico.class;
	}
	
	public List<ProgramacaoServico> getPorCliente(final Long idCliente) throws DaoException {
		try {
			Query query = getEntityManager().createNamedQuery(ProgramacaoServico.GET_POR_CLIENTE);
			query.setParameter("idCliente", idCliente);
			return getResultList(query);
		} catch (Exception ex) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] {"PROGRAMACAOSERVICO", "ID_CLIENTE"}, ex);
		}
	}
	
	public List<ProgramacaoServico> getPorTipoServico(final Long idTipoServico) throws DaoException {
		try {
			Query query = getEntityManager().createNamedQuery(ProgramacaoServico.GET_POR_TIPO_SERVICO);
			query.setParameter("idTipoServico", idTipoServico);
			return getResultList(query);
		} catch (Exception ex) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] {"PROGRAMACAOSERVICO", "ID_TIPOSERVICO"}, ex);
		}
	}

}
