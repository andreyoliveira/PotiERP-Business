package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Cliente;

/**
 * @author renan
 */
public interface ClienteService {
	
	Cliente salvar(Cliente cliente)throws PotiErpMensagensException, PotiErpException; 
	
	void excluir(Cliente cliente)throws PotiErpException;
	
	void excluirLista(List<Cliente> clientes)throws PotiErpException;
	
	Cliente consultar(Long idCliente)throws PotiErpException;
	
	List<Cliente> consultarTodos() throws PotiErpException;
	
	List<Cliente> consultarAtivos() throws PotiErpException;
	
	List<Cliente> consultarInativos() throws PotiErpException;
	
	List<Cliente> consultarTodos(int firstRow, int lastRow) throws PotiErpException;
	
	List<Cliente> consultarAtivos(int firstRow, int lastRow) throws PotiErpException;
	
	List<Cliente> consultarInativos(int firstRow, int lastRow) throws PotiErpException;
	
	List<Cliente> consultarTodosComNomeFantasiaCodigo() throws PotiErpException;

	Number consultarTotalClientes() throws PotiErpException;
	
	Number consultarTotalAtivos() throws PotiErpException;
	
	Number consultarTotalInativos() throws PotiErpException;
}