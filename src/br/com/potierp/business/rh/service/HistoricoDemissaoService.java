package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.HistoricoDemissao;

/**
 * @author Andrey Oliveira
 */
public interface HistoricoDemissaoService {
	
	List<HistoricoDemissao> consultarTodosHistoricosDemissoes() throws PotiErpException;
	
	HistoricoDemissao salvar(HistoricoDemissao historicoDemissao) throws PotiErpException;
	
	void excluir(HistoricoDemissao historicoDemissao) throws PotiErpException;
	
	void excluirLista(List<HistoricoDemissao> listHistoricoDemissao) throws PotiErpException;
	
	List<HistoricoDemissao> buscarPorFuncionario(Long idFuncionario) throws PotiErpException;
}
