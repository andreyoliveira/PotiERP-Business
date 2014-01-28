package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Dependente;

public interface DependenteService {
	
	Dependente salvarDependente(Dependente dependente)throws PotiErpMensagensException, PotiErpException;
	
	void excluirDependente(Dependente dependente)throws PotiErpException;
	
	void excluirListaDependente(List<Dependente> dependentes) throws PotiErpException;
	
	List<Dependente> consultarDependente(Dependente dependente)throws PotiErpException;
	
	List<Dependente> consultarTodosDependentes() throws PotiErpException;
}