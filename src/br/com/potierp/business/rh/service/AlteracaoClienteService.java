package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.AlteracaoCliente;

public interface AlteracaoClienteService {

	List<AlteracaoCliente> consultarTodos() throws PotiErpException;
	
	AlteracaoCliente salvar(AlteracaoCliente alteracaoCliente) throws PotiErpException;
	
	void excluir(AlteracaoCliente alteracaoCliente) throws PotiErpException;
	
	void excluirLista(List<AlteracaoCliente> alteracoescliente) throws PotiErpException;
}
