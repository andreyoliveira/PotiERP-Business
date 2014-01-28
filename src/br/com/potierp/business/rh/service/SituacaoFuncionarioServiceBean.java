package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.SituacaoFuncionarioDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.SituacaoFuncionario;

@Stateless(mappedName="SituacaoFuncionarioService", name="SituacaoFuncionarioService")
@Local(SituacaoFuncionarioService.class)
@Interceptors(DAOInterceptor.class)
public class SituacaoFuncionarioServiceBean implements SituacaoFuncionarioService {
	
	@DAO
	private SituacaoFuncionarioDao situacaoFuncionarioDao;
	
	public SituacaoFuncionario salvarSituacaoFuncionario(SituacaoFuncionario situacaoFuncionario) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(situacaoFuncionario)) {
				if (situacaoFuncionario.isNew()) {
					return incluirSituacaoFuncionario(situacaoFuncionario);
				} else {
					return alterarSituacaoFuncionario(situacaoFuncionario);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UMA_SITUACAO_COM_ESTE_CODIGO.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(SituacaoFuncionario situacaoFuncionario) throws DaoException {
		SituacaoFuncionario situacaoFuncionarioCodigo = situacaoFuncionarioDao.getPorCodigo(situacaoFuncionario);
		if (situacaoFuncionarioCodigo != null && !situacaoFuncionarioCodigo.getId().equals(situacaoFuncionario.getId())) {
			return true;
		}
		return false;
	}

	private SituacaoFuncionario incluirSituacaoFuncionario(SituacaoFuncionario situacaoFuncionario) throws PotiErpException {
		try {
			return situacaoFuncionarioDao.salvar(situacaoFuncionario);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "SituacaoFuncionario" }, e);
		}
	}

	private SituacaoFuncionario alterarSituacaoFuncionario(SituacaoFuncionario situacaoFuncionario) throws PotiErpException {
		try {
			return situacaoFuncionarioDao.salvar(situacaoFuncionario);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "SituacaoFuncionario" }, e);
		}
	}

	public void excluirSituacaoFuncionario(SituacaoFuncionario situacaoFuncionario) throws PotiErpException {
		try {
			situacaoFuncionarioDao.excluir(situacaoFuncionario);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "SituacaoFuncionario" }, e);
		}
	}

	public void excluirListaSituacaoFuncionario(List<SituacaoFuncionario> situacoesFuncionario) throws PotiErpException {
		try {
			for (SituacaoFuncionario situacaoFuncionario : situacoesFuncionario) {
				situacaoFuncionarioDao.excluir(situacaoFuncionario);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "SituacaoFuncionario" }, e);
		}
	}

	public List<SituacaoFuncionario> consultarSituacaoFuncionario(SituacaoFuncionario situacaoFuncionario) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SituacaoFuncionario> consultarTodasSituacoesFuncionario()
			throws PotiErpException {
		try {
			Collection<SituacaoFuncionario> collectionSituacaoFuncionario = situacaoFuncionarioDao.getAll();
			return new ArrayList<SituacaoFuncionario>(collectionSituacaoFuncionario);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "SituacaoFuncionario" }, e);
		}
	}
}