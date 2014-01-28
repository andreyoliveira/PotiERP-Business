package br.com.potierp.business.operacional.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.ProgramacaoServico;

/**
 * 
 * @author Andrey Oliveira
 *
 */
public interface ProgramacaoServicoService {

	ProgramacaoServico salvar(ProgramacaoServico programacaoServico) throws PotiErpException;
	
	List<ProgramacaoServico> consultarTodasProgramacoesServico() throws PotiErpException;
	
	List<ProgramacaoServico> consultarListProgramacaoServicoPorCliente(final Long idCliente)
		throws PotiErpException;
	
	void excluirLista(List<ProgramacaoServico> programacoes) throws PotiErpException;
	
}
