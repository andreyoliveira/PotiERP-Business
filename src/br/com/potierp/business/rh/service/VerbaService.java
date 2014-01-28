package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Verba;

public interface VerbaService {
	
	Verba salvarVerba(Verba verba) throws PotiErpMensagensException, PotiErpException;

	void excluirVerba(Verba verba) throws PotiErpException;

	void excluirListaVerba(List<Verba> verbas) throws PotiErpException;

	List<Verba> consultarVerba(Verba verba) throws PotiErpException;
	
	List<Verba> consultarTodasVerbas() throws PotiErpException;
}