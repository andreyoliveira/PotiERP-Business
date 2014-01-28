package br.com.potierp.business.rh.service;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.TipoValeRefeicao;

public interface TipoValeRefeicaoService {
	
	TipoValeRefeicao salvarTipoValeRefeicao(TipoValeRefeicao tipoValeRefeicao) throws PotiErpMensagensException, PotiErpException;

	void excluirTipoValeRefeicao(TipoValeRefeicao tipoValeRefeicao) throws PotiErpException;

	void excluirListaTipoValeRefeicao(List<TipoValeRefeicao> tiposValeRefeicao) throws PotiErpException;

	List<TipoValeRefeicao> consultarTipoValeRefeicao(TipoValeRefeicao tipoValeRefeicao) throws PotiErpException;
	
	List<TipoValeRefeicao> consultarTodosTiposValeRefeicao() throws PotiErpException;
}