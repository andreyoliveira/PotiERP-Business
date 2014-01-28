package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.SituacaoFuncionario;

public interface SituacaoFuncionarioService {
	
	SituacaoFuncionario salvarSituacaoFuncionario(SituacaoFuncionario situacaoFuncionario)throws PotiErpMensagensException, PotiErpException;
	
	void excluirSituacaoFuncionario(SituacaoFuncionario situacaoFuncionario)throws PotiErpException;
	
	void excluirListaSituacaoFuncionario(List<SituacaoFuncionario> situacoesFuncionarios) throws PotiErpException;
	
	List<SituacaoFuncionario> consultarSituacaoFuncionario(SituacaoFuncionario situacaoFuncionario)throws PotiErpException;
	
	List<SituacaoFuncionario> consultarTodasSituacoesFuncionario() throws PotiErpException;
}