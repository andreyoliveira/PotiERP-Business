package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.Afastamento;

public interface AfastamentoService {

	Afastamento salvar(Afastamento afastamento) throws PotiErpException;

	List<Afastamento> consultarTodosAfastamento() throws PotiErpException;

	void excluirLista(List<Afastamento> afastamentos) throws PotiErpException;
}
