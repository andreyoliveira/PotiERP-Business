package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.FormaPagamento;

public interface FormaPagamentoService {
	
	FormaPagamento salvarFormaPagamento(FormaPagamento formaPagamento)throws PotiErpMensagensException, PotiErpException;
	
	void excluirFormaPagamento(FormaPagamento formaPagamento)throws PotiErpException;
	
	void excluirListaFormaPagamento(List<FormaPagamento> formasPagamentos) throws PotiErpException;
	
	List<FormaPagamento> consultarFormaPagamento(FormaPagamento formaPagamento)throws PotiErpException;
	
	List<FormaPagamento> consultarTodasFormasPagamentos() throws PotiErpException;
}