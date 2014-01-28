package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Fornecedor;

/**
 * @author renan
 */
public interface FornecedorService {
	
	Fornecedor salvar(Fornecedor fornecedor)throws PotiErpMensagensException, PotiErpException; 
	
	void excluir(Fornecedor fornecedor)throws PotiErpException;
	
	void excluirLista(List<Fornecedor> fornecedores)throws PotiErpException;
	
	List<Fornecedor> consultar(Fornecedor fornecedor)throws PotiErpException;
	
	List<Fornecedor> consultarTodos() throws PotiErpException;
}
