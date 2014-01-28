package br.com.potierp.business.operacional.facade;

import java.util.Date;
import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
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
public interface OperacionalModulo {
	
	List<ProgramacaoVisita> consultarTodasProgramacoesVisita() throws PotiErpException;
	
	List<ProgramacaoVisita> consultarPorResponsavel(Long idResponsavel) throws PotiErpException;
	
	ProgramacaoVisita consultarPorResponsavelECliente(Long idResponsavel, Long idCliente) throws PotiErpException;
	
	List<ProgramacaoVisita> consultarPorDataProgramada(final Date dataProgramada) throws PotiErpException;
	
	void salvarProgramacaoVisita(ProgramacaoVisita programacaoVisita) throws PotiErpException;
	
	void excluirProgramacaoVisita(ProgramacaoVisita programacaoVisita) throws PotiErpException;
	
	void excluirListaProgramacaoVisita(List<ProgramacaoVisita> listaProgramacaoVisitas) throws PotiErpException;
	
	void salvar(final DataProgramacaoVisita dataProgramacaoVisita) throws PotiErpException;
	
	ProgramacaoServico salvarProgramacaoServico(final ProgramacaoServico programacaoServico, final TraceInfo traceInfo) throws PotiErpException;
	
	List<ProgramacaoServico> consultarTodasProgramacoesServico(final TraceInfo traceInfo) throws PotiErpException;
	
	List<ProgramacaoServico> consultarListProgramacaoServicoPorCliente(final Long idCliente, final TraceInfo traceInfo) throws PotiErpException;
	
	void excluirListaProgramacaoServico(final List<ProgramacaoServico> programacoes, final TraceInfo traceInfo) throws PotiErpException;
	
	List<Cliente> getAllCliente(final TraceInfo traceInfo) throws PotiErpException;
	
	List<TipoServico> getAllTipoServico(final TraceInfo traceInfo) throws PotiErpException;
	
	List<TipoServico> consultarTodosTipoServico() throws PotiErpException;
	
	List<Cliente> consultarTodosClientesComNomeFantasiaCodigo() throws PotiErpException;
	
	void salvarTipoServico(TipoServico tipoServico) throws PotiErpException;
	
	void excluirTipoServico(TipoServico tipoServico) throws PotiErpException;
	
	void excluirListaTipoServico(List<TipoServico> listaTipoServico) throws PotiErpException;
	
	byte[] getRelatorioProgramacaoVisita(List<ProgramacaoVisita> listaProgramacaoVisita, Responsavel responsavel, int mesSelecionado) throws PotiErpException;
	
	byte[] getRelatorioAcompanhamentoVisita(List<ProgramacaoVisita> listaProgramacaoVisita, Responsavel responsavel, int mesSelecionado) throws PotiErpException;
}
