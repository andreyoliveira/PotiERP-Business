package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.MedidaDisciplinar;

public interface MedidaDisciplinarService {

	MedidaDisciplinar salvar(MedidaDisciplinar medidaDisciplinar) throws PotiErpException;
	
	List<MedidaDisciplinar> consultarTodasMedidasDisciplinares() throws PotiErpException;
	
	void excluirLista(List<MedidaDisciplinar> medidas) throws PotiErpException;
}
