package br.com.potierp.business.adm.facade;

import java.util.List;

import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Funcionalidade;
import br.com.potierp.model.Pessoa;
import br.com.potierp.model.TipoPerfilErp;
import br.com.potierp.model.Usuario;

public interface AdmModulo{

	Usuario autenticarUsuario(Usuario usuario) throws PotiErpException;
	
	Usuario salvarUsuario(Usuario usuario) throws PotiErpMensagensException, PotiErpException;
	
	void excluirUsuario(Usuario usuario) throws PotiErpException;
	
	Usuario atribuirPerfisAoUsuario(Usuario usuario, List<TipoPerfilErp> perfis) throws PotiErpException;
	
	Usuario atribuirPessoaAoUsuario(Usuario usuario, Pessoa pessoa) throws PotiErpException;
	
	Usuario alterarSenhaDoUsuario(Usuario usuario, String novaSenha) throws PotiErpException;
	
	List<Usuario> buscarTodosUsuarios() throws PotiErpException;
	
	TipoPerfilErp salvarTipoPerfil(TipoPerfilErp tipoPerfil) throws PotiErpException;
	
	void excluirTipoPerfil(TipoPerfilErp tipoPerfil) throws PotiErpException;
	
	TipoPerfilErp atribuirFuncionalidadesAoTipoPerfil(TipoPerfilErp tipoPerfil, List<Funcionalidade> funcionalidades) throws PotiErpException;
	
	List<TipoPerfilErp> buscarTodosTipoPerfil() throws PotiErpException;
	
	List<Funcionalidade> buscarTodasFuncionalidades() throws PotiErpException;
	
	void excluirListaUsuarios(List<Usuario> listaUsuarios) throws PotiErpException;
	
	Usuario buscarUsuarioPorId(Long id) throws PotiErpException;
	
	void excluirListaPerfis(List<TipoPerfilErp> listaPerfis) throws PotiErpException;
	
	TipoPerfilErp buscarTipoPerfilPorId(Long id) throws PotiErpException;
}
