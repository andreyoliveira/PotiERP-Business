package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Encargo;

public interface EncargoService {
	
	Encargo salvarEncargo(Encargo encargo)throws PotiErpMensagensException, PotiErpException;
	
	void excluirEncargo(Encargo encargo)throws PotiErpException;
	
	void excluirListaEncargo(List<Encargo> encargos) throws PotiErpException;
	
	List<Encargo> consultarEncargo(Encargo encargo)throws PotiErpException;
	
	List<Encargo> consultarTodosEncargos() throws PotiErpException;
}
