package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.FormaPagamentoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.FormaPagamento;

@Stateless(mappedName="FormaPagamentoService", name="FormaPagamentoService")
@Local(FormaPagamentoService.class)
@Interceptors(DAOInterceptor.class)
public class FormaPagamentoServiceBean implements FormaPagamentoService {
	
	@DAO
	private FormaPagamentoDao formaPagamentoDao;
	
	public FormaPagamento salvarFormaPagamento(FormaPagamento formaPagamento) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(formaPagamento)) {
				if (formaPagamento.isNew()) {
					return incluirFormaPagamento(formaPagamento);
				} else {
					return alterarFormaPagamento(formaPagamento);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UMA_FORMAPAGAMENTO_COM_ESTE_NOME.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(FormaPagamento formaPagamento) throws DaoException {
		FormaPagamento formaPagamentoNome = formaPagamentoDao.getPorNome(formaPagamento);
		if (formaPagamentoNome != null && !formaPagamentoNome.getId().equals(formaPagamento.getId())) {
			return true;
		}
		return false;
	}

	private FormaPagamento incluirFormaPagamento(FormaPagamento formaPagamento) throws PotiErpException {
		try {
			return formaPagamentoDao.salvar(formaPagamento);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "FormaPagamento" }, e);
		}
	}

	private FormaPagamento alterarFormaPagamento(FormaPagamento formaPagamento) throws PotiErpException {
		try {
			return formaPagamentoDao.salvar(formaPagamento);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "FormaPagamento" }, e);
		}
	}

	public void excluirFormaPagamento(FormaPagamento formaPagamento) throws PotiErpException {
		try {
			formaPagamentoDao.excluir(formaPagamento);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "FormaPagamento" }, e);
		}
	}

	public void excluirListaFormaPagamento(List<FormaPagamento> formaPagamentos) throws PotiErpException {
		try {
			for (FormaPagamento formaPagamento : formaPagamentos) {
				formaPagamentoDao.excluir(formaPagamento);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "FormaPagamento" }, e);
		}
	}

	public List<FormaPagamento> consultarFormaPagamento(FormaPagamento formaPagamento) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FormaPagamento> consultarTodasFormasPagamentos()
			throws PotiErpException {
		try {
			Collection<FormaPagamento> collectionFormaPagamento = formaPagamentoDao.getAll();
			return new ArrayList<FormaPagamento>(collectionFormaPagamento);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "FormaPagamento" }, e);
		}
	}
}