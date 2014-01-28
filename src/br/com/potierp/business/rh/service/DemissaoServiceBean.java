package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.DemissaoDao;
import br.com.potierp.dao.TipoDemissaoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Demissao;
import br.com.potierp.model.TipoDemissao;

/**
 * @author renan
 */
@Stateless(mappedName="DemissaoService", name="DemissaoService")
@Local(DemissaoService.class)
@Interceptors(DAOInterceptor.class)
public class DemissaoServiceBean implements DemissaoService {
	
	@DAO
	private DemissaoDao demissaoDao;
	
	@DAO
	private TipoDemissaoDao tipoDemissaoDao;
	
	public Demissao salvarDemissao(Demissao demissao) throws PotiErpException {
		try {
			if(demissao.isNew()){
				return incluirDemissao(demissao);
			}else{
				return alterarDemissao(demissao);
			}
		} catch (Exception e) {
			throw new PotiErpException(e);
		}
	}
	
	private Demissao incluirDemissao(Demissao demissao) throws PotiErpException {
		try {
			return demissaoDao.salvar(demissao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{"Demissao"}, e);		
		}
	}

	private Demissao alterarDemissao(Demissao demissao) throws PotiErpException {
		try {
			return demissaoDao.salvar(demissao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"Demissao"}, e);		
		}
	}

	public void excluirDemissao(Demissao demissao) throws PotiErpException {
		try {
			demissaoDao.salvar(demissao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"Demissao"}, e);		
		}
	}
	
	public void excluirListaDemissao(List<Demissao> demissoes)throws PotiErpException{
		try {
			for(Demissao demissao : demissoes){
				demissaoDao.excluir(demissao);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"Demissao"}, e);
		}
	}
	
	public List<Demissao> consultarDemissao(Demissao demissao)
			throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Demissao> consultarTodasDemissoes() throws PotiErpException {
		try {
			Collection<Demissao> collectionDemissao = demissaoDao.getAll();
			return new ArrayList<Demissao>(collectionDemissao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Demissao"}, e);
		}
	}
	
	public TipoDemissao salvarTipoDemissao(TipoDemissao tipoDemissao)
			throws PotiErpMensagensException, PotiErpException {
		try {
			if(!isDuplicado(tipoDemissao)){
				if(tipoDemissao.isNew()){
					return incluirTipoDemissao(tipoDemissao);
				}else{
					return alterarTipoDemissao(tipoDemissao);
				}
			}else{
				throw new PotiErpMensagensException(MensagensExceptionEnum.ERRO_JA_EXISTE_UM_TIPO_DEMISSAO_COM_ESTE_NOME.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}
	
	private boolean isDuplicado(TipoDemissao tipoDemissao) throws DaoException {
		TipoDemissao tipoDemissaoDesc = tipoDemissaoDao.getByNome(tipoDemissao);
		if(tipoDemissaoDesc != null && !tipoDemissaoDesc.getId().equals(tipoDemissao.getId())){
			return true;
		}
		return false;
	}
	
	private TipoDemissao incluirTipoDemissao(TipoDemissao tipoDemissao) throws PotiErpException {
		try {
			return tipoDemissaoDao.salvar(tipoDemissao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, e);
		}
	}

	private TipoDemissao alterarTipoDemissao(TipoDemissao tipoDemissao) throws PotiErpException {
		try {
			return tipoDemissaoDao.salvar(tipoDemissao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, e);		
		}
	}
	
	public void excluirTipoDemissao(TipoDemissao tipoDemissao)
			throws PotiErpException {
		try {
			tipoDemissaoDao.excluir(tipoDemissao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"TipoDemissao"}, e);		
		}
	}
	
	public void excluirListaTipoDemissao(List<TipoDemissao> tiposDemissoes)throws PotiErpException{
		try {
			for(TipoDemissao tipoDemissao : tiposDemissoes){
				tipoDemissaoDao.excluir(tipoDemissao);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"TipoDemissao"}, e);
		}
	}
	
	public List<TipoDemissao> consultarTipoDemissao(TipoDemissao tipoDemissao)
			throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<TipoDemissao> consultarTodosTiposDemissoes() throws PotiErpException {
		try {
			Collection<TipoDemissao> collectionTipoDemissao = tipoDemissaoDao.getAll();
			return new ArrayList<TipoDemissao>(collectionTipoDemissao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"TipoDemissao"}, e);
		}
	}
}