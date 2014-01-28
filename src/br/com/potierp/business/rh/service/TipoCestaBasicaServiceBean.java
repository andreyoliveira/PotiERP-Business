package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.TipoCestaBasicaDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.TipoCestaBasica;

/**
 * @author renan
 */
@Stateless(mappedName="TipoCestaBasicaService", name="TipoCestaBasicaService")
@Local(TipoCestaBasicaService.class)
@Interceptors(DAOInterceptor.class)
public class TipoCestaBasicaServiceBean implements TipoCestaBasicaService {
	
	@DAO
	private TipoCestaBasicaDao tipoCestaBasicaDao;
	
	@Override
	public TipoCestaBasica salvarTipoCestaBasica(final TipoCestaBasica tipoCestaBasica) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(tipoCestaBasica)) {
				if (tipoCestaBasica.isNew()) {
					return incluirTipoCestaBasica(tipoCestaBasica);
				} else {
					return alterarTipoCestaBasica(tipoCestaBasica);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UM_TIPOCESTABASICA_COM_ESTE_CODIGO.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(final TipoCestaBasica tipoCestaBasica) throws DaoException {
		TipoCestaBasica tipoCestaBasicaCodigo = tipoCestaBasicaDao.getPorCodigo(tipoCestaBasica);
		if (tipoCestaBasicaCodigo != null && !tipoCestaBasicaCodigo.getId().equals(tipoCestaBasica.getId())) {
			return true;
		}
		return false;
	}

	private TipoCestaBasica incluirTipoCestaBasica(final TipoCestaBasica tipoCestaBasica) throws PotiErpException {
		try {
			return tipoCestaBasicaDao.salvar(tipoCestaBasica);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "TipoCestaBasica" }, e);
		}
	}

	private TipoCestaBasica alterarTipoCestaBasica(final TipoCestaBasica tipoCestaBasica) throws PotiErpException {
		try {
			return tipoCestaBasicaDao.salvar(tipoCestaBasica);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "TipoCestaBasica" }, e);
		}
	}

	@Override
	public void excluirTipoCestaBasica(final TipoCestaBasica tipoCestaBasica) throws PotiErpException {
		try {
			tipoCestaBasicaDao.excluir(tipoCestaBasica);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "TipoCestaBasica" }, e);
		}
	}

	@Override
	public void excluirListaTipoCestaBasica(final List<TipoCestaBasica> tiposCestaBasica) throws PotiErpException {
		try {
			for (TipoCestaBasica tipoCestaBasica : tiposCestaBasica){
				tipoCestaBasicaDao.excluir(tipoCestaBasica);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "TipoCestaBasica" }, e);
		}
	}

	@Override
	public List<TipoCestaBasica> consultarTipoCestaBasica(final TipoCestaBasica tipoCestaBasica) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoCestaBasica> consultarTodosTiposCestaBasica() throws PotiErpException {
		try {
			Collection<TipoCestaBasica> collectionTipoCestaBasica = tipoCestaBasicaDao.getAll();
			return new ArrayList<TipoCestaBasica>(collectionTipoCestaBasica);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "TipoCestaBasica" }, e);
		}
	}
}