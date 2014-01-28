package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.TipoValeTransporte;

public interface TipoValeTransporteService {
	
	TipoValeTransporte salvarTipoValeTransporte(TipoValeTransporte tipoValeTransporte) throws PotiErpMensagensException, PotiErpException;

	void excluirTipoValeTransporte(TipoValeTransporte tipoValeTransporte) throws PotiErpException;

	void excluirListaTipoValeTransporte(List<TipoValeTransporte> tiposValeTransporte) throws PotiErpException;

	List<TipoValeTransporte> consultarTipoValeTransporte(TipoValeTransporte tipoValeTransporte) throws PotiErpException;
	
	List<TipoValeTransporte> consultarTodosTiposValeTransporte() throws PotiErpException;
}