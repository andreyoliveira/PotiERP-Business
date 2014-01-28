package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Desconto;

public interface DescontoService {
	
	Desconto salvarDesconto(Desconto desconto) throws PotiErpMensagensException, PotiErpException;

	void excluirDesconto(Desconto desconto) throws PotiErpException;

	void excluirListaDesconto(List<Desconto> descontos) throws PotiErpException;

	List<Desconto> consultarDesconto(Desconto desconto) throws PotiErpException;
	
	List<Desconto> consultarTodosDescontos() throws PotiErpException;
}
