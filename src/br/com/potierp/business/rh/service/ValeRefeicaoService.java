package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.CalculoValeRefeicao;
import br.com.potierp.model.PagamentoValeRefeicao;

public interface ValeRefeicaoService {
	
	CalculoValeRefeicao calcular(final CalculoValeRefeicao calculoValeRefeicao)
			throws PotiErpMensagensException, PotiErpException;
	
	void gravar(final CalculoValeRefeicao calculoValeRefeicao)
			throws PotiErpMensagensException, PotiErpException;
	
	void excluirCalculo(final CalculoValeRefeicao calculoValeRefeicao)
			throws PotiErpMensagensException, PotiErpException;
	
	List<CalculoValeRefeicao> consultarTodos() throws PotiErpException;
	
	List<PagamentoValeRefeicao> getPagamentosPorCalculo(Long idCalculo)
			throws PotiErpMensagensException, PotiErpException;
}