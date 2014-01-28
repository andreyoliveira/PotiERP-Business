package br.com.potierp.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.ProgramacaoVisita;

/**
 * @author Ralph
 */
public class ProgramacaoVisitaDao extends BaseDao {
	
	private static Logger log = Logger.getLogger(ProgramacaoVisitaDao.class);

	public ProgramacaoVisitaDao() throws DaoException {
		super();
	}

	/**
	 * N�o implementado.
	 */
	@Override
	protected String getNamedQueryAll() {
		return ProgramacaoVisita.GET_ALL;
	}
	
	public List<ProgramacaoVisita> consultarPorResponsavel(final Long idResponsavel) throws DaoException {
		try {
			Query query = getEntityManager().createNamedQuery(ProgramacaoVisita.GET_POR_RESPONSAVEL);
			query.setParameter("idResponsavel", idResponsavel);
			return carregarClientesResponsavel(query);
		} catch (NoResultException ne) {
			log.error("Entidade PROGRAMACAOVISITA nao encontrada para o parametro: IDRESPONSAVEL="+idResponsavel+"; Retornando null.");
			return null;
		} catch (Exception e) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] {"PROGRAMACAOVISITA", "ID"}, e);
		}
	}
	
	public ProgramacaoVisita consultarPorResponsavelECliente(final Long idResponsavel, final Long idCliente) throws DaoException {
		try {
			Query query = getEntityManager().createNamedQuery(ProgramacaoVisita.GET_POR_RESPONSAVEL_E_CLIENTE);
			query.setParameter("idResponsavel", idResponsavel);
			query.setParameter("idCliente", idCliente);
			return (ProgramacaoVisita)query.getSingleResult();
		} catch (NoResultException ne) {
			log.info("Entidade PROGRAMACAOVISITA nao encontrada para os parametros: IDRESPONSAVEL="+idResponsavel+"; IDCLIENTE="+idCliente+"; Retornando null.");
			return null;
		} catch (Exception e) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[] {"PROGRAMACAOVISITA", "ID"}, e);
		}
	}
	
	public List<ProgramacaoVisita> getBy(final Date dataProgramada) throws DaoException {
		try {
			Query query = getEntityManager().createNamedQuery(ProgramacaoVisita.GET_POR_DATAPROGRAMADA);
			query.setParameter("dataProgramada", dataProgramada);
			return carregarClientesResponsavel(query);
		} catch (NoResultException ne) {
			log.error("Entidade PROGRAMACAOVISITA nao encontrada para o parametro: dataProgramada="+dataProgramada+"; Retornando null.");
			return null;
		} catch (Exception e) {
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] {"PROGRAMACAOVISITA", "DATAPROGRAMADA"}, e);
		}
	}

	private List<ProgramacaoVisita> carregarClientesResponsavel(final Query query) {
		List<ProgramacaoVisita> programacao = getResultList(query);
		for(ProgramacaoVisita  programacaoVisita : programacao){
			programacaoVisita.getResponsavel().getClientes().size();
		}
		return programacao;
	}

	/**
	 * N�o implementado.
	 */
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}
	
	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return ProgramacaoVisita.class;
	}
}
