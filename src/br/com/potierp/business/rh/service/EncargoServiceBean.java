package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.EncargoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Encargo;

@Stateless(mappedName="EncargoService", name="EncargoService")
@Local(EncargoService.class)
@Interceptors(DAOInterceptor.class)
public class EncargoServiceBean implements EncargoService {
	
	@DAO
	private EncargoDao encargoDao;
	
	public Encargo salvarEncargo(Encargo encargo) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(encargo)) {
				if (encargo.isNew()) {
					return incluirEncargo(encargo);
				} else {
					return alterarEncargo(encargo);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UM_ENCARGO_COM_ESTE_NOME.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(Encargo encargo) throws DaoException {
		Encargo encargoNome = encargoDao.getPorNome(encargo);
		if (encargoNome != null && !encargoNome.getId().equals(encargo.getId())) {
			return true;
		}
		return false;
	}

	private Encargo incluirEncargo(Encargo encargo) throws PotiErpException {
		try {
			return encargoDao.salvar(encargo);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "Encargo" }, e);
		}
	}

	private Encargo alterarEncargo(Encargo encargo) throws PotiErpException {
		try {
			return encargoDao.salvar(encargo);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "Encargo" }, e);
		}
	}

	public void excluirEncargo(Encargo encargo) throws PotiErpException {
		try {
			encargoDao.excluir(encargo);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "Encargo" }, e);
		}
	}

	public void excluirListaEncargo(List<Encargo> encargos) throws PotiErpException {
		try {
			for (Encargo encargo : encargos) {
				encargoDao.excluir(encargo);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "Encargo" }, e);
		}
	}

	public List<Encargo> consultarEncargo(Encargo encargo) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Encargo> consultarTodosEncargos()
			throws PotiErpException {
		try {
			Collection<Encargo> collectionEncargo = encargoDao.getAll();
			return new ArrayList<Encargo>(collectionEncargo);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "Encargo" }, e);
		}
	}
}