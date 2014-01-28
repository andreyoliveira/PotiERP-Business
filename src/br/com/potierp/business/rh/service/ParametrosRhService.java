package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.ParametrosRh;

public interface ParametrosRhService {
	
	ParametrosRh salvarParametrosRh(ParametrosRh parametrosRh)throws PotiErpMensagensException, PotiErpException;
	
	List<ParametrosRh> consultarTodosParametrosRh() throws PotiErpException;
}