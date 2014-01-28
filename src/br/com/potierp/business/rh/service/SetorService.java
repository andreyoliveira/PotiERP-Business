package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Setor;

public interface SetorService {
	
	Setor salvarSetor(Setor setor)throws PotiErpMensagensException, PotiErpException;
	
	void excluirSetor(Setor setor)throws PotiErpException;
	
	void excluirListaSetor(List<Setor> setores) throws PotiErpException;
	
	List<Setor> consultarSetor(Setor setor)throws PotiErpException;
	
	List<Setor> consultarTodosSetores() throws PotiErpException;
}