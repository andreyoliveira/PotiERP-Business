package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.SetorDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Setor;

@Stateless(mappedName="SetorService", name="SetorService")
@Local(SetorService.class)
@Interceptors(DAOInterceptor.class)
public class SetorServiceBean implements SetorService {
	
	@DAO
	private SetorDao setorDao;
	
	@Override
	public Setor salvarSetor(final Setor setor) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(setor)) {
				if (setor.isNew()) {
					return incluirSetor(setor);
				} else {
					return alterarSetor(setor);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UM_SETOR_COM_ESTE_NOME.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(final Setor setor) throws DaoException {
		Setor setorNome = setorDao.getPorNome(setor);
		if (setorNome != null && !setorNome.getId().equals(setor.getId())) {
			return true;
		}
		return false;
	}

	private Setor incluirSetor(final Setor setor) throws PotiErpException {
		try {
			return setorDao.salvar(setor);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "Setor" }, e);
		}
	}

	private Setor alterarSetor(final Setor setor) throws PotiErpException {
		try {
			return setorDao.salvar(setor);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "Setor" }, e);
		}
	}

	@Override
	public void excluirSetor(final Setor setor) throws PotiErpException {
		try {
			setorDao.excluir(setor);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "Setor" }, e);
		}
	}

	@Override
	public void excluirListaSetor(final List<Setor> setores) throws PotiErpException {
		try {
			for (Setor setor : setores) {
				setorDao.excluir(setor);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "Setor" }, e);
		}
	}

	@Override
	public List<Setor> consultarSetor(final Setor setor) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Setor> consultarTodosSetores()
			throws PotiErpException {
		try {
			Collection<Setor> collectionSetor = setorDao.getAll();
			List<Setor> setores = new ArrayList<Setor>(collectionSetor);
			Collections.sort(setores);
			return setores;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "Setor" }, e);
		}
	}
}