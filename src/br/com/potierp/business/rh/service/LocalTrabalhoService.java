package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.LocalTrabalho;

public interface LocalTrabalhoService {

	List<LocalTrabalho> getPorReComSetores(final Long codigoRegistro)
			throws PotiErpException;
}
