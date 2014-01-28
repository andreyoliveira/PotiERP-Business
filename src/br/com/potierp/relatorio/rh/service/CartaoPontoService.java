package br.com.potierp.relatorio.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.Funcionario;

/**
 * @author Andrey Oliveira
 */
public interface CartaoPontoService {

	byte[] getRelatorio(final List<Funcionario> listFuncionario, final String mes, final String ano) throws PotiErpException;
	
}
