package br.com.potierp.relatorio.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.CalculoValeRefeicao;

/**
 * @author Doug
 *
 */
public interface MapaValeRefeicaoService {

	byte[] getRelatorio(List<CalculoValeRefeicao> listCalculoValeRefeicao) throws PotiErpException;
}
