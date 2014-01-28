package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.TipoValeRefeicaoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.TipoValeRefeicao;

/**
 * @author renan
 */
@Stateless(mappedName="TipoValeRefeicaoService", name="TipoValeRefeicaoService")
@Local(TipoValeRefeicaoService.class)
@Interceptors(DAOInterceptor.class)
public class TipoValeRefeicaoServiceBean implements TipoValeRefeicaoService {
	
	@DAO
	private TipoValeRefeicaoDao tipoValeRefeicaoDao;
	
	@Override
	public TipoValeRefeicao salvarTipoValeRefeicao(final TipoValeRefeicao tipoValeRefeicao) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(tipoValeRefeicao)) {
				if (tipoValeRefeicao.isNew()) {
					return incluirTipoValeRefeicao(tipoValeRefeicao);
				} else {
					return alterarTipoValeRefeicao(tipoValeRefeicao);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UM_TIPOVALEREFEICAO_COM_ESTE_CODIGO.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(final TipoValeRefeicao tipoValeRefeicao) throws DaoException {
		TipoValeRefeicao tipoValeRefeicaoCodigo = tipoValeRefeicaoDao.getPorCodigo(tipoValeRefeicao);
		if (tipoValeRefeicaoCodigo != null && !tipoValeRefeicaoCodigo.getId().equals(tipoValeRefeicao.getId())) {
			return true;
		}
		return false;
	}

	private TipoValeRefeicao incluirTipoValeRefeicao(final TipoValeRefeicao tipoValeRefeicao) throws PotiErpException {
		try {
			return tipoValeRefeicaoDao.salvar(tipoValeRefeicao);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "TipoValeRefeicao" }, e);
		}
	}

	private TipoValeRefeicao alterarTipoValeRefeicao(final TipoValeRefeicao tipoValeRefeicao) throws PotiErpException {
		try {
			return tipoValeRefeicaoDao.salvar(tipoValeRefeicao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "TipoValeRefeicao" }, e);
		}
	}

	@Override
	public void excluirTipoValeRefeicao(final TipoValeRefeicao tipoValeRefeicao) throws PotiErpException {
		try {
			tipoValeRefeicaoDao.excluir(tipoValeRefeicao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "TipoValeRefeicao" }, e);
		}
	}

	@Override
	public void excluirListaTipoValeRefeicao(final List<TipoValeRefeicao> tiposValeRefeicao) throws PotiErpException {
		try {
			for (TipoValeRefeicao tipoValeRefeicao : tiposValeRefeicao){
				tipoValeRefeicaoDao.excluir(tipoValeRefeicao);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "TipoValeRefeicao" }, e);
		}
	}

	@Override
	public List<TipoValeRefeicao> consultarTipoValeRefeicao(final TipoValeRefeicao tipoValeRefeicao) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoValeRefeicao> consultarTodosTiposValeRefeicao() throws PotiErpException {
		try {
			Collection<TipoValeRefeicao> collectionTipoValeRefeicao = tipoValeRefeicaoDao.getAll();
			return new ArrayList<TipoValeRefeicao>(collectionTipoValeRefeicao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "TipoValeRefeicao" }, e);
		}
	}
}