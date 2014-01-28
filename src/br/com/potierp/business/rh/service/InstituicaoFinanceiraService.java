package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.Agencia;
import br.com.potierp.model.Banco;

public interface InstituicaoFinanceiraService {
	
	Banco salvarBanco(Banco banco)throws PotiErpException;
	
	void excluirBanco(Banco banco)throws PotiErpException;
	
	List<Banco> consultarBanco(Banco banco)throws PotiErpException;
	
	Agencia salvarAgencia(Agencia agencia)throws PotiErpException;
	
	void excluirAgencia(Agencia agencia)throws PotiErpException;
	
	List<Agencia> consultarAgencia(Agencia agencia)throws PotiErpException;
}