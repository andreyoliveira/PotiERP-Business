package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Feriado;

/**
 * @author Doug
 *
 */
public interface FeriadoService {

	Feriado salvar(Feriado feriado) throws PotiErpMensagensException, PotiErpException;
	
	List<Feriado> consultarTodosFeriados() throws PotiErpMensagensException, PotiErpException;
	
	Feriado consultar(Long idFeriado) throws PotiErpMensagensException, PotiErpException;
	
	void excluir(Feriado feriado) throws PotiErpMensagensException, PotiErpException;
	
	void excluirLista(List<Feriado> feriados) throws PotiErpMensagensException, PotiErpException;
}
