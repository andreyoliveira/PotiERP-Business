package br.com.potierp.business.operacional.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;

import br.com.potierp.business.operacional.service.ProgramacaoServicoService;
import br.com.potierp.business.operacional.service.ProgramacaoVisitaService;
import br.com.potierp.business.operacional.service.TipoServicoService;
import br.com.potierp.business.rh.service.ClienteService;
import br.com.potierp.dao.TipoServicoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.log.TraceInfo;
import br.com.potierp.model.Cliente;
import br.com.potierp.model.DataProgramacaoVisita;
import br.com.potierp.model.ProgramacaoServico;
import br.com.potierp.model.ProgramacaoVisita;
import br.com.potierp.model.Responsavel;
import br.com.potierp.model.TipoServico;

/**
 * @author Ralph
 */
@Stateless(mappedName="OperacionalModulo", name="OperacionalModulo")
@Remote(OperacionalModulo.class)
@Interceptors(DAOInterceptor.class)
public class OperacionalModuloBean implements OperacionalModulo {
	
	private static Logger log = Logger.getLogger(OperacionalModuloBean.class);
	
	@EJB
	private ProgramacaoVisitaService programacaoVisitaService;
	
	@EJB
	private ProgramacaoServicoService programacaoServicoService;
	
	@EJB
	private ClienteService clienteService;
	
	@EJB
	private TipoServicoService tipoServicoService;
	
	@DAO
	private TipoServicoDao tipoServicoDao;
	
	@Override
	public List<ProgramacaoVisita> consultarTodasProgramacoesVisita() throws PotiErpException {
		try {
			return programacaoVisitaService.consultarTotasProgramacoesVisita();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<ProgramacaoVisita> consultarPorResponsavel(final Long idResponsavel) throws PotiErpException {
		try {
			return programacaoVisitaService.consultarPorResponsavel(idResponsavel);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public ProgramacaoVisita consultarPorResponsavelECliente(final Long idResponsavel, final Long idCliente) throws PotiErpException {
		try {
			return programacaoVisitaService.consultarPorResponsavelECliente(idResponsavel, idCliente);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<ProgramacaoVisita> consultarPorDataProgramada(final Date dataProgramada) throws PotiErpException {
		try {
			return programacaoVisitaService.getBy(dataProgramada);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void salvarProgramacaoVisita(final ProgramacaoVisita programacaoVisita) throws PotiErpException {
		try {
			programacaoVisitaService.salvar(programacaoVisita);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirProgramacaoVisita(final ProgramacaoVisita programacaoVisita) throws PotiErpException {
		try {
			programacaoVisitaService.excluir(programacaoVisita);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaProgramacaoVisita(final List<ProgramacaoVisita> listaProgramacaoVisitas) throws PotiErpException {
		try {
			programacaoVisitaService.excluirLista(listaProgramacaoVisitas);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public void salvar(final DataProgramacaoVisita dataProgramacaoVisita) throws PotiErpException {
		try {
			programacaoVisitaService.salvar(dataProgramacaoVisita);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<TipoServico> consultarTodosTipoServico() throws PotiErpException{
		try {
			Collection<TipoServico> tiposServicos = tipoServicoDao.getAll();
			return new ArrayList<TipoServico>(tiposServicos);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PotiErpException(e);
		}
	}
	
	@Override
	public void salvarTipoServico(final TipoServico tipoServico) throws PotiErpException {
		try {
			tipoServicoDao.salvar(tipoServico);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PotiErpException(e);
		}
	}

	@Override
	public void excluirTipoServico(final TipoServico tipoServico) throws PotiErpException {
		try {
			tipoServicoDao.excluir(tipoServico);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PotiErpException(e);
		}
	}

	@Override
	public void excluirListaTipoServico(final List<TipoServico> listaTipoServico) throws PotiErpException {
		try {
			for(TipoServico tipoServico : listaTipoServico)
				tipoServicoDao.excluir(tipoServico);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PotiErpException(e);
		}
	}

	@Override
	public ProgramacaoServico salvarProgramacaoServico(
			final ProgramacaoServico programacaoServico, final TraceInfo traceInfo)
			throws PotiErpException {
		try {
			return programacaoServicoService.salvar(programacaoServico);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<ProgramacaoServico> consultarTodasProgramacoesServico(
			final TraceInfo traceInfo) throws PotiErpException {
		try {
			return programacaoServicoService.consultarTodasProgramacoesServico();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<ProgramacaoServico> consultarListProgramacaoServicoPorCliente(
			final Long idCliente, final TraceInfo traceInfo) throws PotiErpException {
		try {
			return programacaoServicoService.consultarListProgramacaoServicoPorCliente(idCliente);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaProgramacaoServico(
			final List<ProgramacaoServico> programacoes, final TraceInfo traceInfo) throws PotiErpException {
		try {
			programacaoServicoService.excluirLista(programacoes);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Cliente> getAllCliente(final TraceInfo traceInfo)
			throws PotiErpException {
		try {
			return clienteService.consultarTodos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<TipoServico> getAllTipoServico(final TraceInfo traceInfo)
			throws PotiErpException {
		try {
			return this.tipoServicoService.consultarTodos();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Cliente> consultarTodosClientesComNomeFantasiaCodigo()
			throws PotiErpException {
		try {
			return clienteService.consultarTodosComNomeFantasiaCodigo();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public byte[] getRelatorioProgramacaoVisita(final List<ProgramacaoVisita> listaProgramacaoVisita, 
			final Responsavel responsavel, final int mesSelecionado) throws PotiErpException {
		return programacaoVisitaService.getRelatorioProgramacaoVisita(listaProgramacaoVisita, responsavel, mesSelecionado);
	}
	
	@Override
	public byte[] getRelatorioAcompanhamentoVisita(final List<ProgramacaoVisita> listaProgramacaoVisita, 
			final Responsavel responsavel, final int mesSelecionado) throws PotiErpException {
		return programacaoVisitaService.getRelatorioAcompanhamentoVisita(listaProgramacaoVisita, responsavel, mesSelecionado);
	}
}
