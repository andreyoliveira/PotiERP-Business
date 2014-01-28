package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.FornecedorDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Fornecedor;

/**
 * @author renan
 */
@Stateless(mappedName="FornecedorService", name="FornecedorService")
@Local(FornecedorService.class)
@Interceptors(DAOInterceptor.class)
public class FornecedorServiceBean implements FornecedorService {
	
	@DAO
	private FornecedorDao fornecedorDao;

	public Fornecedor salvar(Fornecedor fornecedor) throws PotiErpMensagensException, PotiErpException {
		try {
			verificarDuplicidade(fornecedor);
			if(fornecedor.isNew()){
				return incluirFornecedor(fornecedor);
			}else{
				return alterarFornecedor(fornecedor);
			}
		} catch (DaoException e) {
			throw new PotiErpException(e);
		}
	}
	
	private void verificarDuplicidade(Fornecedor fornecedor) throws DaoException, PotiErpMensagensException {
		Fornecedor fornecedorCnpj = fornecedorDao.getByCnpj(fornecedor);
		if(fornecedorCnpj != null && !fornecedorCnpj.getId().equals(fornecedor.getId())){
			throw new PotiErpMensagensException(MensagensExceptionEnum.ERRO_JA_EXISTE_FORNECEDOR_COM_ESTE_CPFCNPJ.getMsg());
		}else{
			Fornecedor fornecedorCodigo = fornecedorDao.getByCodigo(fornecedor);
			if(fornecedorCodigo != null && !fornecedorCodigo.getId().equals(fornecedor.getId())){
				throw new PotiErpMensagensException(MensagensExceptionEnum.ERRO_JA_EXISTE_UM_FORNECEDOR_COM_ESTE_CODIGO.getMsg());
			}
		}
	}

	private Fornecedor incluirFornecedor(Fornecedor fornecedor) throws PotiErpException {
		try {
			return fornecedorDao.salvar(fornecedor);
		} catch (Exception de) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{"Fornecedor"}, de);		
		}
	}

	private Fornecedor alterarFornecedor(Fornecedor fornecedor) throws PotiErpException {
		try {
			return fornecedorDao.salvar(fornecedor);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"Fornecedor"}, e);		
		}
	}

	public void excluir(Fornecedor fornecedor) throws PotiErpException {
		try {
			fornecedorDao.excluir(fornecedor);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"Fornecedor"}, e);
		}
	}
	
	public void excluirLista(List<Fornecedor> fornecedores)throws PotiErpException{
		try {
			for(Fornecedor fornecedor : fornecedores){
				fornecedorDao.excluir(fornecedor);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"Fornecedores"}, e);
		}
	}

	public List<Fornecedor> consultar(Fornecedor fornecedor) throws PotiErpException {
		try {
			return fornecedorDao.getFornecedoresPorExemplo(fornecedor);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"Fornecedor"}, e);
		}
	}
	
	public List<Fornecedor> consultarTodos() throws PotiErpException {
		try {
			Collection<Fornecedor> collectionFornecedor = fornecedorDao.getAll();
			return new ArrayList<Fornecedor>(collectionFornecedor);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Fornecedor"}, e);
		}
	}
}