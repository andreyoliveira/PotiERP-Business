package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.Ferias;

/**
 * @author Andrey Oliveira
 */
public interface FeriasService {
	
	List<Ferias> consultarTodasFerias() throws PotiErpException;
	
	Ferias salvar(Ferias ferias) throws PotiErpException;
	
	void excluir(Ferias ferias) throws PotiErpException;
	
	void excluirLista(List<Ferias> listFerias) throws PotiErpException;
	
	List<Ferias> buscarPorFuncionario(Long idFuncionario) throws PotiErpException;

}
