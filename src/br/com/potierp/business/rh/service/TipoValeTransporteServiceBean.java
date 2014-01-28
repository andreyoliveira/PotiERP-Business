package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.TipoValeTransporteDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.TipoValeTransporte;

/**
 * @author renan
 */
@Stateless(mappedName="TipoValeTransporteService", name="TipoValeTransporteService")
@Local(TipoValeTransporteService.class)
@Interceptors(DAOInterceptor.class)
public class TipoValeTransporteServiceBean implements TipoValeTransporteService {
	
	@DAO
	private TipoValeTransporteDao tipoValeTransporteDao;
	
	@Override
	public TipoValeTransporte salvarTipoValeTransporte(final TipoValeTransporte tipoValeTransporte) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(tipoValeTransporte)) {
				if (tipoValeTransporte.isNew()) {
					return incluirTipoValeTransporte(tipoValeTransporte);
				} else {
					return alterarTipoValeTransporte(tipoValeTransporte);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UM_TIPOVALETRANSPORTE_COM_ESTE_CODIGO.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(final TipoValeTransporte tipoValeTransporte) throws DaoException {
		TipoValeTransporte tipoValeTransporteCodigo = tipoValeTransporteDao.getPorCodigo(tipoValeTransporte);
		if (tipoValeTransporteCodigo != null && !tipoValeTransporteCodigo.getId().equals(tipoValeTransporte.getId())) {
			return true;
		}
		return false;
	}

	private TipoValeTransporte incluirTipoValeTransporte(final TipoValeTransporte tipoValeTransporte) throws PotiErpException {
		try {
			return tipoValeTransporteDao.salvar(tipoValeTransporte);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "TipoValeTransporte" }, e);
		}
	}

	private TipoValeTransporte alterarTipoValeTransporte(final TipoValeTransporte tipoValeTransporte) throws PotiErpException {
		try {
			return tipoValeTransporteDao.salvar(tipoValeTransporte);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "TipoValeTransporte" }, e);
		}
	}

	@Override
	public void excluirTipoValeTransporte(final TipoValeTransporte tipoValeTransporte) throws PotiErpException {
		try {
			tipoValeTransporteDao.excluir(tipoValeTransporte);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "TipoValeTransporte" }, e);
		}
	}

	@Override
	public void excluirListaTipoValeTransporte(final List<TipoValeTransporte> tiposValeTransporte) throws PotiErpException {
		try {
			for (TipoValeTransporte tipoValeTransporte : tiposValeTransporte){
				tipoValeTransporteDao.excluir(tipoValeTransporte);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "TipoValeTransporte" }, e);
		}
	}

	@Override
	public List<TipoValeTransporte> consultarTipoValeTransporte(final TipoValeTransporte tipoValeTransporte) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoValeTransporte> consultarTodosTiposValeTransporte() throws PotiErpException {
		try {
			Collection<TipoValeTransporte> collectionTipoValeTransporte = tipoValeTransporteDao.getAll();
			return new ArrayList<TipoValeTransporte>(collectionTipoValeTransporte);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "TipoValeTransporte" }, e);
		}
	}
}