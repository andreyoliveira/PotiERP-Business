package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.EnderecoRepositorio;

/**
 * @author renan
 */
public interface LogradouroService {
	
	EnderecoRepositorio salvarEndereco(EnderecoRepositorio endereco)throws PotiErpException;
	
	void excluirEndereco(EnderecoRepositorio endereco)throws PotiErpException;
	
	List<EnderecoRepositorio> consultarEndereco(EnderecoRepositorio endereco)throws PotiErpException;
	
	EnderecoRepositorio consultarEnderecoPorCep(String cep)throws PotiErpException;
}