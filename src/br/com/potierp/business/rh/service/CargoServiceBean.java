package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.CargoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Cargo;

@Stateless(mappedName="CargoService", name="CargoService")
@Local(CargoService.class)
@Interceptors(DAOInterceptor.class)
public class CargoServiceBean implements CargoService {
	
	@DAO
	private CargoDao cargoDao;
	
	@Override
	public Cargo salvarCargo(final Cargo cargo) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(cargo)) {
				if (cargo.isNew()) {
					return incluirCargo(cargo);
				} else {
					return alterarCargo(cargo);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UM_CARGO_COM_ESTE_NOME.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(final Cargo cargo) throws DaoException {
		Cargo cargoNome = cargoDao.getPorNome(cargo);
		if (cargoNome != null && !cargoNome.getId().equals(cargo.getId())) {
			return true;
		}
		return false;
	}

	private Cargo incluirCargo(final Cargo cargo) throws PotiErpException {
		try {
			return cargoDao.salvar(cargo);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "Cargo" }, e);
		}
	}

	private Cargo alterarCargo(final Cargo cargo) throws PotiErpException {
		try {
			return cargoDao.salvar(cargo);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "Cargo" }, e);
		}
	}

	@Override
	public void excluirCargo(final Cargo cargo) throws PotiErpException {
		try {
			cargoDao.excluir(cargo);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "Cargo" }, e);
		}
	}

	@Override
	public void excluirListaCargo(final List<Cargo> cargos) throws PotiErpException {
		try {
			for (Cargo cargo : cargos) {
				cargoDao.excluir(cargo);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "Cargo" }, e);
		}
	}

	@Override
	public Cargo consultarCargo(final Cargo cargo) throws PotiErpException {
		try {
			return cargoDao.getByPrimaryKey(cargo.getId());
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[] { "Cargo" }, e);
		}
	}

	@Override
	public List<Cargo> consultarTodosCargos()
			throws PotiErpException {
		try {
			Collection<Cargo> collectionCargo = cargoDao.getAll();
			return new ArrayList<Cargo>(collectionCargo);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "Cargo" }, e);
		}
	}
}