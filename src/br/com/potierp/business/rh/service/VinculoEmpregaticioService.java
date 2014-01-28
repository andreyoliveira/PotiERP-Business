package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.VinculoEmpregaticio;

public interface VinculoEmpregaticioService {
	
	VinculoEmpregaticio salvarVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio)throws PotiErpMensagensException, PotiErpException;
	
	void excluirVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio)throws PotiErpException;
	
	void excluirListaVinculoEmpregaticio(List<VinculoEmpregaticio> vinculosEmpregaticios) throws PotiErpException;
	
	List<VinculoEmpregaticio> consultarVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio)throws PotiErpException;
	
	List<VinculoEmpregaticio> consultarTodosVinculosEmpregaticio() throws PotiErpException;
}