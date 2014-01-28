package br.com.potierp.relatorio.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.Funcionario;

/**
 * @author Doug
 *
 */
public interface SolicitacaoValeTransporteService {

	byte[] getRelatorio(List<Funcionario> listFuncionario) throws PotiErpException;
}
