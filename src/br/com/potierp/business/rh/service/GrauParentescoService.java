package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.GrauParentesco;

public interface GrauParentescoService {
	
	GrauParentesco salvarGrauParentesco(GrauParentesco grauParentesco) throws PotiErpMensagensException, PotiErpException;

	void excluirGrauParentesco(GrauParentesco grauParentesco) throws PotiErpException;

	void excluirListaGrauParentesco(List<GrauParentesco> valesRefeicao) throws PotiErpException;

	List<GrauParentesco> consultarGrauParentesco(GrauParentesco grauParentesco) throws PotiErpException;
	
	List<GrauParentesco> consultarTodosGrauParentescos() throws PotiErpException;
}