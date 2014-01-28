package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.VinculoEmpregaticioDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.VinculoEmpregaticio;

@Stateless(mappedName="VinculoEmpregaticioService", name="VinculoEmpregaticioService")
@Local(VinculoEmpregaticioService.class)
@Interceptors(DAOInterceptor.class)
public class VinculoEmpregaticioServiceBean implements VinculoEmpregaticioService {
	
	@DAO
	private VinculoEmpregaticioDao vinculoEmpregaticioDao;
	
	public VinculoEmpregaticio salvarVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(vinculoEmpregaticio)) {
				if (vinculoEmpregaticio.isNew()) {
					return incluirVinculoEmpregaticio(vinculoEmpregaticio);
				} else {
					return alterarVinculoEmpregaticio(vinculoEmpregaticio);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UM_VINCULOEMPREGATICIO_COM_ESTE_CODIGO.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(VinculoEmpregaticio vinculoEmpregaticio) throws DaoException {
		VinculoEmpregaticio vinculoEmpregaticioCodigo = vinculoEmpregaticioDao.getPorCodigo(vinculoEmpregaticio);
		if (vinculoEmpregaticioCodigo != null && !vinculoEmpregaticioCodigo.getId().equals(vinculoEmpregaticio.getId())) {
			return true;
		}
		return false;
	}

	private VinculoEmpregaticio incluirVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio) throws PotiErpException {
		try {
			return vinculoEmpregaticioDao.salvar(vinculoEmpregaticio);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "VinculoEmpregaticio" }, e);
		}
	}

	private VinculoEmpregaticio alterarVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio) throws PotiErpException {
		try {
			return vinculoEmpregaticioDao.salvar(vinculoEmpregaticio);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "VinculoEmpregaticio" }, e);
		}
	}

	public void excluirVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio) throws PotiErpException {
		try {
			vinculoEmpregaticioDao.excluir(vinculoEmpregaticio);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "VinculoEmpregaticio" }, e);
		}
	}

	public void excluirListaVinculoEmpregaticio(List<VinculoEmpregaticio> vinculosEmpregaticio) throws PotiErpException {
		try {
			for (VinculoEmpregaticio vinculoEmpregaticio : vinculosEmpregaticio) {
				vinculoEmpregaticioDao.excluir(vinculoEmpregaticio);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "VinculoEmpregaticio" }, e);
		}
	}

	public List<VinculoEmpregaticio> consultarVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<VinculoEmpregaticio> consultarTodosVinculosEmpregaticio()
			throws PotiErpException {
		try {
			Collection<VinculoEmpregaticio> collectionVinculoEmpregaticio = vinculoEmpregaticioDao.getAll();
			return new ArrayList<VinculoEmpregaticio>(collectionVinculoEmpregaticio);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "VinculoEmpregaticio" }, e);
		}
	}
}