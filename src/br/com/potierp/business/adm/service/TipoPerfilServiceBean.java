package br.com.potierp.business.adm.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.FuncionalidadeDao;
import br.com.potierp.dao.TipoPerfilDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Funcionalidade;
import br.com.potierp.model.TipoPerfilErp;

@Stateless(mappedName = "TipoPerfilService", name="TipoPerfilService")
@Local(TipoPerfilService.class)
@Interceptors(DAOInterceptor.class)
public class TipoPerfilServiceBean implements TipoPerfilService{
	
	/**
	 * Constante para utilizacão nas mensagens.
	 */
	private static final String FUNCIONALIDADE = "Funcionalidade";

	/**
	 * Constante para utilizacão nas mensagens.
	 */
	private static final String PERFIL = "Perfil";

	@DAO
	TipoPerfilDao tipoPerfilDao;
	
	@DAO
	FuncionalidadeDao funcionalidadeDao;

	public TipoPerfilErp salvar(TipoPerfilErp tipoPerfil) throws PotiErpException {
		try{
			if(isDuplicado(tipoPerfil)){
				throw new PotiErpMensagensException(MensagensExceptionEnum.ERRO_JA_EXISTE_UM_PERFIL_COM_ESTA_DESCRICAO.getMsg());
			}else{
				if(tipoPerfil.isNew()){
					return this.incluirTipoPerfil(tipoPerfil);
				}else{
					return this.alterarTipoPerfil(tipoPerfil);
				}
			}
		}catch(DaoException ex){
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{PERFIL}, ex);
		}
	}
		
	private boolean isDuplicado(TipoPerfilErp tipoPerfil) throws DaoException {
		TipoPerfilErp tipoPerfilTemp = tipoPerfilDao.getByDescricao(tipoPerfil.getDescricao());
		if(tipoPerfilTemp != null && !tipoPerfilTemp.getId().equals(tipoPerfil.getId()) ){
			return true;
		}
		return false;
	}
	
	private TipoPerfilErp incluirTipoPerfil(TipoPerfilErp tipoPerfil) throws PotiErpException {
		try {
			return tipoPerfilDao.salvar(tipoPerfil);
		} catch (Exception de) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{PERFIL}, de);		
		}
	}

	private TipoPerfilErp alterarTipoPerfil(TipoPerfilErp tipoPerfil) throws PotiErpException {
		try {
			return tipoPerfilDao.salvar(tipoPerfil);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{PERFIL}, e);		
		}
	}

	public void excluir(TipoPerfilErp tipoPerfil) throws PotiErpException {
		try{
			tipoPerfilDao.excluir(tipoPerfil);
		}catch(Exception ex){
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{PERFIL}, ex);
		}
	}

	public TipoPerfilErp atribuirFuncionalidades(TipoPerfilErp tipoPerfil, List<Funcionalidade> funcionalidades) throws PotiErpException{
		try{
			tipoPerfil.setFuncionalidades(funcionalidades);
			return tipoPerfilDao.salvar(tipoPerfil);
		}catch(Exception ex){
			throw new PotiErpException("Erro ao atribuir perfis ao TipoPerfil", ex);
		}
	}
	
	public List<TipoPerfilErp> buscarTodos() throws PotiErpException {
		try{
			Collection<TipoPerfilErp> tipoPerfilLista = tipoPerfilDao.getAll();
			if(tipoPerfilLista != null && !tipoPerfilLista.isEmpty()){
				for(TipoPerfilErp tipoPerfil : tipoPerfilLista){
					tipoPerfil.getFuncionalidades().size();
				}
			}
			return new ArrayList<TipoPerfilErp>(tipoPerfilLista);
		}catch(Exception ex){
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{PERFIL}, ex);
		}
	}
	
	public List<Funcionalidade> buscarTodasFuncionalidades() throws PotiErpException {
		try{
			Collection<Funcionalidade> funcionalidades = funcionalidadeDao.getAll();
			return new LinkedList<Funcionalidade>(funcionalidades);
		}catch(Exception ex){
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{FUNCIONALIDADE}, ex);
		}
	}
	
	public void excluirLista(List<TipoPerfilErp> listaPerfis) throws PotiErpException {
		try {
			for(TipoPerfilErp perfil : listaPerfis){
				tipoPerfilDao.excluir(perfil);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{PERFIL}, e);
		}
	}
	
	public TipoPerfilErp buscarPorId(Long id) throws PotiErpException {
		try {
			TipoPerfilErp tipoPerfil = tipoPerfilDao.getByPrimaryKey(id);
			tipoPerfil.getFuncionalidades().size();
			return tipoPerfil;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{PERFIL}, e);
		}
	}
}