package br.com.potierp.business.comercial.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.HistoricoComercial;

public interface ComercialService {
	
	HistoricoComercial salvar(HistoricoComercial historicoComercial) throws PotiErpMensagensException, PotiErpException;
	
	void excluir(HistoricoComercial historicoComercial) throws PotiErpMensagensException, PotiErpException;
	
	void excluir(List<HistoricoComercial> listaHistoricoComercial) throws PotiErpMensagensException, PotiErpException;
	
	HistoricoComercial consultarPorId(Long idHistoricoComercial) throws PotiErpException;
	
	List<HistoricoComercial> consultarTodos() throws PotiErpException;
	
	List<HistoricoComercial> consultarPorCliente(Long idCliente) throws PotiErpException;

}