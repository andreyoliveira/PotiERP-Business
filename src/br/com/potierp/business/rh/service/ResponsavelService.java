package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.Responsavel;

public interface ResponsavelService {

	List<Responsavel> consultarTodosResponsaveis() throws PotiErpException;
	
	Responsavel salvar(Responsavel responsavel) throws PotiErpException;
	
	void excluir(Responsavel responsavel) throws PotiErpException;
	
	void excluirLista(List<Responsavel> responsaveis) throws PotiErpException;
	
	List<Responsavel> buscarPorFuncionario(Long idFuncionario) throws PotiErpException;
}
