/**
 * 
 */
package br.com.potierp.business.adm.service;

import static br.com.potierp.infra.msg.MensagensExceptionEnum.ERRO_BUSCAR_LOGIN;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.NoResultException;

import br.com.potierp.dao.UsuarioDAO;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Pessoa;
import br.com.potierp.model.TipoPerfilErp;
import br.com.potierp.model.Usuario;
import br.com.potierp.util.DateUtilService;
/**
 * @author felipe
 *
 */
@Stateless(mappedName = "UsuarioService", name="UsuarioService")
@Local(UsuarioService.class)
@Interceptors(DAOInterceptor.class)
public class UsuarioServiceBean implements UsuarioService {
	
	@DAO
	private UsuarioDAO usuarioDao;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario autenticar(Usuario usuario) throws PotiErpException{
		try{
			Usuario usuarioLogado = usuarioDao.getUsuarioPorCredenciais(usuario);
			if(possuiExpiracaoSenha(usuarioLogado) && isSenhaExpirada(usuarioLogado)){
				usuario.setSenhaExpirada(true);
				usuarioLogado.setAutenticado(false);
			}else{
				usuarioLogado.setAutenticado(true);
			}
			usuarioLogado.setPassword(null);
			inicializarPerfisEFuncionalidades(usuarioLogado);
			return usuarioLogado;
		}catch(NoResultException nre){
			throw new PotiErpException("Usuário e/ou senha inválidos");
		}catch (Exception ex) {
			throw new PotiErpException(ERRO_BUSCAR_LOGIN, ex);
		}
	}

	private void inicializarPerfisEFuncionalidades(Usuario usuario) {
		for(TipoPerfilErp tipoPerfil : usuario.getPerfis()){
			tipoPerfil.getFuncionalidades().size();
		}
	}

	private boolean possuiExpiracaoSenha(Usuario usuarioLogado) {
		return usuarioLogado.getDataExpiraSenha() != null;
	}

	private boolean isSenhaExpirada(Usuario usuarioLogado) {
		return DateUtilService.compararDatasSemHoras(new Date(), usuarioLogado.getDataExpiraSenha()) >= 0;
	}
	
	public Usuario salvar(Usuario usuario) throws PotiErpMensagensException, PotiErpException {
		try{
			if (!isDuplicado(usuario)) {
				if(!usuario.isNew()){
					Usuario usuarioTemp = usuarioDao.getByPrimaryKey(usuario.getId());
					usuario.setPassword(usuarioTemp.getPassword());
				}
			}else{
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UM_USUARIO_COM_ESTE_NOME.getMsg());
			}
			return persistir(usuario);
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}
	
	private boolean isDuplicado(Usuario usuario) throws DaoException {
		Usuario usuarioNome = usuarioDao.getPorUserName(usuario);
		if (usuarioNome != null && !usuarioNome.getId().equals(usuario.getId())) {
			return true;
		}
		return false;
	}

	private Usuario persistir(Usuario usuario) throws DaoException {
		Date dataAtual = new Date();
		usuario.setDataUltimaAlteracao(dataAtual);
		return usuarioDao.salvar(usuario);
	}
	
	public void excluir(Usuario usuario) throws PotiErpException {
		try{
			usuarioDao.excluir(usuario);
		}catch(Exception ex){
			//TODO [FELIPE] Definir a parte de Logs
			throw new PotiErpException("Erro ao excluir usuário");
		}
	}
	
	public Usuario atribuirPerfis(Usuario usuario, List<TipoPerfilErp> perfis) throws PotiErpException {
		try{
			usuario.setPerfis(perfis);
			return persistir(usuario);
		}catch(Exception ex){
			//TODO [FELIPE] Definir a parte de Logs
			throw new PotiErpException("Erro ao atribuir perfis ao Usuário");
		}
	}
	
	public Usuario atribuirPessoa(Usuario usuario, Pessoa pessoa) throws PotiErpException{
		try{
			usuario.setPessoa(pessoa);
			return persistir(usuario);
		}catch(Exception ex){
			//TODO [FELIPE] Definir a parte de Logs
			throw new PotiErpException("Erro ao atribuir pessoa ao Usuário");
		}
	}
	
	public Usuario alterarSenha(Usuario usuario, String novaSenha) throws PotiErpException {
		try{
			usuario.setPassword(novaSenha);
			return persistir(usuario);
		}catch(Exception ex){
			//TODO [FELIPE] Definir a parte de Logs
			throw new PotiErpException("Erro ao atribuir pessoa ao Usuário");
		}
	}
	
	public List<Usuario> buscarTodos() throws PotiErpException {
		try{
			Collection<Usuario> usuarios = usuarioDao.getAll();
			return new ArrayList<Usuario>(usuarios);
		}catch (DaoException dex) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_OBJETO, new Object[]{"Usuario"}, dex);
		}
	}
	
	public void excluirLista(List<Usuario> listaUsuarios) throws PotiErpException {
		try {
			for(Usuario usuario : listaUsuarios){
				usuarioDao.excluir(usuario);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"Usuario"}, e);
		}
	}
	
	public Usuario buscarUsuarioPorId(Long id) throws PotiErpException {
		try{
			return usuarioDao.getByPrimaryKey(id);
		}catch (DaoException dex) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_OBJETO, new Object[]{"Usuario"}, dex);
		}
	}
}