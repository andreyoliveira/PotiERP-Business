package br.com.potierp.business.adm.service;

import java.util.List;

import br.com.potierp.core.BaseModulo;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Pessoa;
import br.com.potierp.model.TipoPerfilErp;
import br.com.potierp.model.Usuario;

/**
 * @author felipe
 *
 */
public interface UsuarioService extends BaseModulo{
	
	Usuario autenticar(Usuario usuario) throws PotiErpException;
	
	Usuario salvar(Usuario usuario) throws PotiErpMensagensException, PotiErpException;
	
	void excluir(Usuario usuario) throws PotiErpException;
	
	Usuario atribuirPerfis(Usuario usuario, List<TipoPerfilErp> perfis) throws PotiErpException;
	
	Usuario atribuirPessoa(Usuario usuario, Pessoa pessoa) throws PotiErpException;
	
	Usuario alterarSenha(Usuario usuario, String novaSenha) throws PotiErpException;
	
	List<Usuario> buscarTodos() throws PotiErpException;

	void excluirLista(List<Usuario> listaUsuarios) throws PotiErpException;
	
	Usuario buscarUsuarioPorId(Long id) throws PotiErpException;

}
