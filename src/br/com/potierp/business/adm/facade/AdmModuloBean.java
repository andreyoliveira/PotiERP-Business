package br.com.potierp.business.adm.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import br.com.potierp.business.adm.service.TipoPerfilService;
import br.com.potierp.business.adm.service.UsuarioService;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Funcionalidade;
import br.com.potierp.model.Pessoa;
import br.com.potierp.model.TipoPerfilErp;
import br.com.potierp.model.Usuario;

/**
 * Session Bean implementation class UsuarioModuloBean
 */
@Stateless(mappedName="AdmModulo", name="AdmModulo")
@Remote(AdmModulo.class)
public class AdmModuloBean implements AdmModulo{
	
	private static Logger log = Logger.getLogger(AdmModuloBean.class);
	
	@EJB
	UsuarioService usuarioService;
	
	@EJB
	TipoPerfilService tipoPerfilService;

	public Usuario autenticarUsuario(Usuario usuario) throws PotiErpException {
		try{
			return usuarioService.autenticar(usuario);
		}catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	public Usuario salvarUsuario(Usuario usuario) throws PotiErpMensagensException, PotiErpException {
		try{
			return usuarioService.salvar(usuario);
		}catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	public void excluirUsuario(Usuario usuario) throws PotiErpException {
		try{
			usuarioService.excluir(usuario);
		}catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	public Usuario atribuirPerfisAoUsuario(Usuario usuario, List<TipoPerfilErp> perfis) throws PotiErpException {
		try{
			return usuarioService.atribuirPerfis(usuario, perfis);
		}catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	public Usuario atribuirPessoaAoUsuario(Usuario usuario, Pessoa pessoa) throws PotiErpException {
		try{
			return usuarioService.atribuirPessoa(usuario, pessoa);
		}catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	public Usuario alterarSenhaDoUsuario(Usuario usuario, String novaSenha) throws PotiErpException {
		try{
			return usuarioService.alterarSenha(usuario, novaSenha);
		}catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	public TipoPerfilErp salvarTipoPerfil(TipoPerfilErp tipoPerfil) throws PotiErpException {
		try{
			return tipoPerfilService.salvar(tipoPerfil);
		}catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	public void excluirTipoPerfil(TipoPerfilErp tipoPerfil) throws PotiErpException {
		try{
			tipoPerfilService.excluir(tipoPerfil);
		}catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	public TipoPerfilErp atribuirFuncionalidadesAoTipoPerfil(TipoPerfilErp tipoPerfil, 
			List<Funcionalidade> funcionalidades) throws PotiErpException {
		try{
			return tipoPerfilService.atribuirFuncionalidades(tipoPerfil, funcionalidades);
		}catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public List<Usuario> buscarTodosUsuarios() throws PotiErpException {
		try{
			return usuarioService.buscarTodos();
		}catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public List<TipoPerfilErp> buscarTodosTipoPerfil() throws PotiErpException {
		try{
			return tipoPerfilService.buscarTodos();
		}catch(PotiErpException e){
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public List<Funcionalidade> buscarTodasFuncionalidades() throws PotiErpException {
		try{
			return tipoPerfilService.buscarTodasFuncionalidades();
		}catch(PotiErpException e){
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	public void excluirListaUsuarios(List<Usuario> listaUsuarios) throws PotiErpException {
		try{
			usuarioService.excluirLista(listaUsuarios);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public Usuario buscarUsuarioPorId(Long id) throws PotiErpException {
		try{
			return usuarioService.buscarUsuarioPorId(id);
		}catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public void excluirListaPerfis(List<TipoPerfilErp> listaPerfis) throws PotiErpException {
		try{
			tipoPerfilService.excluirLista(listaPerfis);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public TipoPerfilErp buscarTipoPerfilPorId(Long id) throws PotiErpException {
		try{
			return tipoPerfilService.buscarPorId(id);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}