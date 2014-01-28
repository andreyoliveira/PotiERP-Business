package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.DependenteDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Dependente;

@Stateless(mappedName="DependenteService", name="DependenteService")
@Local(DependenteService.class)
@Interceptors(DAOInterceptor.class)
public class DependenteServiceBean implements DependenteService {
	
	@DAO
	private DependenteDao dependenteDao;
	
	@Override
	public Dependente salvarDependente(final Dependente dependente) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(dependente)) {
				if (dependente.isNew()) {
					return incluirDependente(dependente);
				} else {
					return alterarDependente(dependente);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UM_DEPENDENTE_COM_ESTE_NOME_PARA_ESTE_FUNCIONARIO.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(final Dependente dependente) throws DaoException {
		Dependente dependenteNomeEFuncionario = dependenteDao.getPorNomeEFuncionario(dependente);
		if (dependenteNomeEFuncionario != null && !dependenteNomeEFuncionario.getId().equals(dependente.getId())) {
			return true;
		}
		return false;
	}

	private Dependente incluirDependente(final Dependente dependente) throws PotiErpException {
		try {
			return dependenteDao.salvar(dependente);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "Dependente" }, e);
		}
	}

	private Dependente alterarDependente(final Dependente dependente) throws PotiErpException {
		try {
			return dependenteDao.salvar(dependente);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "Dependente" }, e);
		}
	}

	@Override
	public void excluirDependente(final Dependente dependente) throws PotiErpException {
		try {
			dependenteDao.excluir(dependente);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "Dependente" }, e);
		}
	}

	@Override
	public void excluirListaDependente(final List<Dependente> dependentes) throws PotiErpException {
		try {
			for (Dependente dependente : dependentes) {
				dependenteDao.excluir(dependente);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "Dependente" }, e);
		}
	}

	@Override
	public List<Dependente> consultarDependente(final Dependente dependente) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dependente> consultarTodosDependentes()
			throws PotiErpException {
		try {
			Collection<Dependente> collectionDependente = dependenteDao.getAll();
			return new ArrayList<Dependente>(collectionDependente);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "Dependente" }, e);
		}
	}
}