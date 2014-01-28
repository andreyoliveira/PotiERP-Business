package br.com.potierp.business.endereco.service;

import java.util.List;

import br.com.potierp.core.BaseModulo;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.Estado;
import br.com.potierp.model.Pais;

/**
 * @author felipe
 *
 */
public interface EnderecoService extends BaseModulo{
	
	List<Pais> buscarTodosPaises() throws PotiErpException;
	
	List<Estado> buscarEstadosPorPais(Pais pais) throws PotiErpException;
	
	Pais buscarPaisPorSigla(String sigla) throws PotiErpException;

}