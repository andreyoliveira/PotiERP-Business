package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Empresa;

/**
 * @author renan
 */
public interface EmpresaService {
	
	Empresa salvar(Empresa empresa)throws PotiErpMensagensException, PotiErpException; 
	
	void excluir(Empresa empresa)throws PotiErpException;
	
	void excluirLista(List<Empresa> empresas)throws PotiErpException;
	
	List<Empresa> consultar(Empresa empresa)throws PotiErpException;
	
	List<Empresa> consultarTodos() throws PotiErpException;
}
