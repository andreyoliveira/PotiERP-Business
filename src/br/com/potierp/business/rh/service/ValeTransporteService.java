package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.CalculoValeTransporte;
import br.com.potierp.model.PagamentoValeTransporte;

public interface ValeTransporteService {
	
	CalculoValeTransporte calcular(final CalculoValeTransporte calculoValeTransporte)
			throws PotiErpMensagensException, PotiErpException;
	
	void gravar(final CalculoValeTransporte calculoValeTransporte)
			throws PotiErpMensagensException, PotiErpException;
	
	void excluirCalculo(final CalculoValeTransporte calculoValeTransporte)
			throws PotiErpMensagensException, PotiErpException;
	
	List<CalculoValeTransporte> consultarTodos() throws PotiErpException;
	
	List<PagamentoValeTransporte> getPagamentosPorCalculo(Long idCalculo)
			throws PotiErpMensagensException, PotiErpException;
}