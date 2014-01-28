package br.com.potierp.business.operacional.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.ProgramacaoServicoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.ProgramacaoServico;

/**
 * 
 * @author Andrey Oliveira
 *
 */
@Stateless(mappedName="ProgramacaoServicoService", name="ProgramacaoServicoService")
@Local(ProgramacaoServicoService.class)
@Interceptors(DAOInterceptor.class)
public class ProgramacaoServicoServiceBean implements ProgramacaoServicoService {
	
	@DAO
	private ProgramacaoServicoDao programacaoServicoDao;

	@Override
	public ProgramacaoServico salvar(ProgramacaoServico programacaoServico)
			throws PotiErpException {
		try {
			return programacaoServicoDao.salvar(programacaoServico);
		} catch (DaoException e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"ProgramacaoServico"}, e);
		}
	}

	@Override
	public List<ProgramacaoServico> consultarTodasProgramacoesServico()
			throws PotiErpException {
		try {
			Collection<ProgramacaoServico> programacoes = this.programacaoServicoDao.getAll();
			return new ArrayList<ProgramacaoServico>(programacoes);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"ProgramacaoServico"}, e);
		}
	}

	@Override
	public List<ProgramacaoServico> consultarListProgramacaoServicoPorCliente(
			Long idCliente) throws PotiErpException {
		try {
			return this.programacaoServicoDao.getPorCliente(idCliente);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"ProgramacaoServico", "idCliente"}, e);
		}
	}

	@Override
	public void excluirLista(List<ProgramacaoServico> programacoes)
			throws PotiErpException {
		try {
			for(ProgramacaoServico p : programacoes) {
				this.programacaoServicoDao.excluir(p);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"ProgramacaoServico"}, e);
		}
	}

}
