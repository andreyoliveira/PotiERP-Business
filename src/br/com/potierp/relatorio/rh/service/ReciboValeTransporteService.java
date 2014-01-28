package br.com.potierp.relatorio.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.CalculoValeTransporte;

/**
 * @author Doug
 *
 */
public interface ReciboValeTransporteService {

	byte[] getRelatorio(List<CalculoValeTransporte> listCalculoValeTransporte) throws PotiErpException;
}
