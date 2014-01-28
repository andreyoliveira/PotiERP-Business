package br.com.potierp.business.endereco.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.CidadeDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Estado;

/**
 * @author renan
 */
@Stateless(mappedName="CidadeService", name="CidadeService")
@Local(CidadeService.class)
@Interceptors(DAOInterceptor.class)
public class CidadeServiceBean implements CidadeService {
	
	@DAO
	private CidadeDao cidadeDao;
	
	@Override
	public Cidade salvarCidade(final Cidade cidade) throws PotiErpMensagensException, PotiErpException {
		try {
			if (!isDuplicado(cidade)) {
				if (cidade.isNew()) {
					return incluirCidade(cidade);
				} else {
					return alterarCidade(cidade);
				}
			} else {
				throw new PotiErpMensagensException(
						MensagensExceptionEnum.ERRO_JA_EXISTE_UMA_CIDADE_PARA_ESTE_ESTADO_COM_ESTE_NOME.getMsg());
			}
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private boolean isDuplicado(final Cidade cidade) throws DaoException {
		Cidade cidadeNomeIdEstado = cidadeDao.getPorNomeIdEstado(cidade);
		if (cidadeNomeIdEstado != null && !cidadeNomeIdEstado.getId().equals(cidade.getId())) {
			return true;
		}
		return false;
	}

	private Cidade incluirCidade(final Cidade cidade) throws PotiErpException {
		try {
			return cidadeDao.salvar(cidade);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "Cidade" }, e);
		}
	}

	private Cidade alterarCidade(final Cidade cidade) throws PotiErpException {
		try {
			return cidadeDao.salvar(cidade);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "Cidade" }, e);
		}
	}

	@Override
	public void excluirCidade(final Cidade cidade) throws PotiErpException {
		try {
			cidadeDao.excluir(cidade);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE,new Object[] { "Cidade" }, e);
		}
	}

	@Override
	public void excluirListaCidade(final List<Cidade> cidades) throws PotiErpException {
		try {
			for (Cidade cidade : cidades){
				cidadeDao.excluir(cidade);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "Cidade" }, e);
		}
	}

	@Override
	public List<Cidade> consultarCidade(final Cidade cidade) throws PotiErpException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cidade> consultarTodasCidades() throws PotiErpException {
		try {
			Collection<Cidade> collectionCidade = cidadeDao.getAll();
			return new ArrayList<Cidade>(collectionCidade);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "Cidade" }, e);
		}
	}
	
	@Override
	public List<Cidade> consultarPorEstado(final Estado estado) throws PotiErpException {
		try {
			Collection<Cidade> collectionCidade = cidadeDao.getPorEstado(estado);
			return new ArrayList<Cidade>(collectionCidade);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[] { "Cidade" }, e);
		}
	}
}