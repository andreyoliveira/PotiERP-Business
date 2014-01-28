package br.com.potierp.relatorio.rh.service;

import java.util.Date;
import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.Responsavel;

/**
 * @author Doug
 *
 */
public interface VencimentoExperienciaService {
	
	byte[] getRelatorio(List<Funcionario> listFuncionario, Date dataInicio,
			Date dataFim, Cidade cidade, Responsavel responsavel)
			throws PotiErpException;
	
}
