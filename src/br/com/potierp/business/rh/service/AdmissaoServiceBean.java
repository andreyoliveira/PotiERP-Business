package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.TipoAdmissaoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.TipoAdmissao;

/**
 * @author renan
 */
@Stateless(mappedName="AdmissaoService", name="AdmissaoService")
@Local(AdmissaoService.class)
@Interceptors(DAOInterceptor.class)
public class AdmissaoServiceBean implements AdmissaoService {

	@DAO
	private TipoAdmissaoDao tipoAdmissaoDao;
	
	public TipoAdmissao salvarTipoAdmissao(TipoAdmissao tipoAdmissao)
			throws PotiErpMensagensException, PotiErpException {
		try {
			if(!isDuplicado(tipoAdmissao)){
				if(tipoAdmissao.isNew()){
					return incluirTipoAdmissao(tipoAdmissao);
				}else{
					return alterarTipoAdmissao(tipoAdmissao);
				}
			}else{
				throw new PotiErpMensagensException(MensagensExceptionEnum.ERRO_JA_EXISTE_UM_TIPO_ADMISSAO_COM_ESTE_NOME.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}
	
	private boolean isDuplicado(TipoAdmissao tipoAdmissao) throws DaoException {
		TipoAdmissao tipoAdmissaoDesc = tipoAdmissaoDao.getByNome(tipoAdmissao);
		if(tipoAdmissaoDesc != null && !tipoAdmissaoDesc.getId().equals(tipoAdmissao.getId())){
			return true;
		}
		return false;
	}
	
	private TipoAdmissao incluirTipoAdmissao(TipoAdmissao tipoAdmissao) throws PotiErpException {
		try {
			return tipoAdmissaoDao.salvar(tipoAdmissao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, e);
		}
	}

	private TipoAdmissao alterarTipoAdmissao(TipoAdmissao tipoAdmissao) throws PotiErpException {
		try {
			return tipoAdmissaoDao.salvar(tipoAdmissao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, e);		
		}
	}
	
	public void excluirTipoAdmissao(TipoAdmissao tipoAdmissao)
			throws PotiErpException {
		try {
			tipoAdmissaoDao.excluir(tipoAdmissao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"TipoAdmissao"}, e);		
		}
	}
	
	public void excluirListaTipoAdmissao(List<TipoAdmissao> tiposAdmissoes)throws PotiErpException{
		try {
			for(TipoAdmissao tipoAdmissao : tiposAdmissoes){
				tipoAdmissaoDao.excluir(tipoAdmissao);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"TipoAdmissao"}, e);
		}
	}
	
	public List<TipoAdmissao> consultarTipoAdmissao(TipoAdmissao tipoAdmissao)
			throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<TipoAdmissao> consultarTodosTiposAdmissoes() throws PotiErpException {
		try {
			Collection<TipoAdmissao> collectionTipoAdmissao = tipoAdmissaoDao.getAll();
			return new ArrayList<TipoAdmissao>(collectionTipoAdmissao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"TipoAdmissao"}, e);
		}
	}
}