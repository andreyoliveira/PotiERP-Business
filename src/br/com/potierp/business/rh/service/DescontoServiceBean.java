package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.DescontoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Desconto;

@Stateless(mappedName="DescontoService", name="DescontoService")
@Local(DescontoService.class)
@Interceptors(DAOInterceptor.class)
public class DescontoServiceBean implements DescontoService {
	
	@DAO
	private DescontoDao descontoDao;
	
	public Desconto salvarDesconto(Desconto desconto) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(desconto)) {
				if (desconto.isNew()) {
					return incluirDesconto(desconto);
				} else {
					return alterarDesconto(desconto);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UM_DESCONTO_COM_ESTE_NOME.getMsg());
			}
		} catch (Exception e) {
			throw new PotiErpException(e);
		}
	}

	private boolean isDuplicado(Desconto desconto) throws DaoException {
		Desconto descontoNome = descontoDao.getPorNome(desconto);
		if (descontoNome != null && !descontoNome.getId().equals(desconto.getId())) {
			return true;
		}
		return false;
	}

	private Desconto incluirDesconto(Desconto desconto) throws PotiErpException {
		try {
			return descontoDao.salvar(desconto);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "Desconto" }, e);
		}
	}

	private Desconto alterarDesconto(Desconto desconto) throws PotiErpException {
		try {
			return descontoDao.salvar(desconto);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "Desconto" }, e);
		}
	}

	public void excluirDesconto(Desconto desconto) throws PotiErpException {
		try {
			descontoDao.excluir(desconto);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "Desconto" }, e);
		}
	}

	public void excluirListaDesconto(List<Desconto> descontos) throws PotiErpException {
		try {
			for (Desconto desconto : descontos) {
				descontoDao.excluir(desconto);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "Desconto" }, e);
		}
	}

	public List<Desconto> consultarDesconto(Desconto desconto) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Desconto> consultarTodosDescontos()
			throws PotiErpException {
		try {
			Collection<Desconto> collectionDesconto = descontoDao.getAll();
			return new ArrayList<Desconto>(collectionDesconto);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "Desconto" }, e);
		}
	}
}