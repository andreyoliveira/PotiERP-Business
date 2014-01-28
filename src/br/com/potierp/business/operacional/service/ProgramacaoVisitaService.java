package br.com.potierp.business.operacional.service;

import java.util.Date;
import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.DataProgramacaoVisita;
import br.com.potierp.model.ProgramacaoVisita;
import br.com.potierp.model.Responsavel;

/**
 * @author Ralph
 */
public interface ProgramacaoVisitaService {
	
	List<ProgramacaoVisita> consultarTotasProgramacoesVisita() throws PotiErpException;
	
	List<ProgramacaoVisita> consultarPorResponsavel(Long idResponsavel) throws PotiErpException;
	
	ProgramacaoVisita consultarPorResponsavelECliente(Long idResponsavel, Long idCliente) throws PotiErpException;
	
	List<ProgramacaoVisita> getBy(Date dataProgramada) throws PotiErpException;
	
	void salvar(ProgramacaoVisita programacaoVisita) throws PotiErpException;
	
	void excluir(ProgramacaoVisita programacaoVisita) throws PotiErpException;
	
	void excluirLista(List<ProgramacaoVisita> listaProgramacaoVisitas) throws PotiErpException; 
	
	void salvar(final DataProgramacaoVisita dataProgramacaoVisita) throws PotiErpException;
	
	byte[] getRelatorioProgramacaoVisita(List<ProgramacaoVisita> listaProgramacaoVisita, Responsavel responsavel, int mesSelecionado) throws PotiErpException;
	
	byte[] getRelatorioAcompanhamentoVisita(List<ProgramacaoVisita> listaProgramacaoVisita, Responsavel responsavel, int mesSelecionado) throws PotiErpException;
}
