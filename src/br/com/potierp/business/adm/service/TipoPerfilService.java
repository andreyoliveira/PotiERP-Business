package br.com.potierp.business.adm.service;

import java.util.List;

import br.com.potierp.core.BaseModulo;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.Funcionalidade;
import br.com.potierp.model.TipoPerfilErp;

/**
 * Classe que representa os Perfis dos usuários que utilizam o sistema.
 * @author felipe
 *
 */
public interface TipoPerfilService extends BaseModulo{
	
	TipoPerfilErp salvar(TipoPerfilErp tipoPerfil) throws PotiErpException;
	
	void excluir(TipoPerfilErp tipoPerfil) throws PotiErpException;
	
	TipoPerfilErp atribuirFuncionalidades(TipoPerfilErp tipoPerfil, List<Funcionalidade> funcionalidades) throws PotiErpException;
	
	List<TipoPerfilErp> buscarTodos() throws PotiErpException;
	
	List<Funcionalidade> buscarTodasFuncionalidades() throws PotiErpException;
	
	void excluirLista(List<TipoPerfilErp> listaPerfis) throws PotiErpException;
	
	TipoPerfilErp buscarPorId(Long id) throws PotiErpException;
	
}
