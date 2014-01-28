package br.com.potierp.business.comercial.facade;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Cliente;
import br.com.potierp.model.HistoricoComercial;

/**
 * Módulo Comercial
 * @author felipe
 *
 */
public interface ComercialModulo {
	
	HistoricoComercial salvar(HistoricoComercial historicoComercial) throws PotiErpMensagensException, PotiErpException;
	
	void excluir(HistoricoComercial historicoComercial) throws PotiErpMensagensException, PotiErpException;
	
	void excluir(List<HistoricoComercial> listaHistoricoComercial) throws PotiErpMensagensException, PotiErpException;
	
	HistoricoComercial consultarPorId(Long idHistoricoComercial) throws PotiErpException;
	
	List<HistoricoComercial> consultarTodos() throws PotiErpException;
	
	List<Cliente> consultarTodosClientesComNomeFantasiaCodigo() throws PotiErpException;

}