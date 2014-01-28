package br.com.potierp.business.operacional.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.TipoServico;

/**
 * 
 * @author Andrey Oliveira
 *
 */
public interface TipoServicoService {
	
	List<TipoServico> consultarTodos() throws PotiErpException;

}
