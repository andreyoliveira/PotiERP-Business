package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.ClienteDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Cliente;

/**
 * @author renan
 */
@Stateless(mappedName="ClienteService", name="ClienteService")
@Local(ClienteService.class)
@Interceptors(DAOInterceptor.class)
public class ClienteServiceBean implements ClienteService {
	
	@DAO
	private ClienteDao clienteDao;

	@Override
	public Cliente salvar(final Cliente cliente) throws PotiErpMensagensException, PotiErpException {
		try {
			verificarDuplicidade(cliente);
			if(cliente.isNew()){
				return incluirCliente(cliente);
			}else{
				return alterarCliente(cliente);
			}
		} catch (DaoException e) {
			throw new PotiErpException(e);
		}
	}
	
	private void verificarDuplicidade(final Cliente cliente) throws DaoException, PotiErpMensagensException {
		Cliente clienteCodigo = clienteDao.getByCodigo(cliente);
		if(clienteCodigo != null && !clienteCodigo.getId().equals(cliente.getId())){
			throw new PotiErpMensagensException(MensagensExceptionEnum.ERRO_JA_EXISTE_UM_CLIENTE_COM_ESTE_CODIGO.getMsg());
		}
	}
	
	private Cliente incluirCliente(final Cliente cliente) throws PotiErpException {
		try {
			return clienteDao.salvar(cliente);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{"Cliente"}, e);		
		}
	}
	
	private Cliente alterarCliente(final Cliente cliente) throws PotiErpException {
		try {
			cliente.atualizarValorContrato();
			return clienteDao.salvar(cliente);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"Cliente"}, e);
		}
	}

	@Override
	public void excluir(final Cliente cliente) throws PotiErpException {
		try {
			clienteDao.excluir(cliente);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"Cliente"}, e);		
		}	
	}
	
	@Override
	public void excluirLista(final List<Cliente> clientes)throws PotiErpException{
		try {
			for(Cliente cliente: clientes){
				clienteDao.excluir(cliente);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"Clientes"}, e);
		}
	}

	@Override
	public Cliente consultar(final Long idCliente) throws PotiErpException {
		try{
			//TODO [Estevao] : Arrumar a consulta para não ter que da cliente.size() lazyException
			Cliente cliente = clienteDao.getByPrimaryKey(idCliente);
			cliente.getSetores().size();
			cliente.getJornadasTrabalho().size();
			cliente.getContratosCliente().size();
			cliente.getReajustesCliente().size();
			return cliente;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"Cliente"}, e);
		}
	}
	
	@Override
	public List<Cliente> consultarTodos() throws PotiErpException {
		try {
			Collection<Cliente> collectionCliente = clienteDao.getAll();
			List<Cliente> clientes = new ArrayList<Cliente>(collectionCliente);
			Collections.sort(clientes);
			return clientes;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Cliente"}, e);
		}
	}
	
	@Override
	public List<Cliente> consultarAtivos() throws PotiErpException{
		try {
			List<Cliente> clientes = clienteDao.getAtivos();
			Collections.sort(clientes);
			return clientes;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Cliente"}, e);
		}
	}
	
	@Override
	public List<Cliente> consultarInativos() throws PotiErpException{
		try {
			List<Cliente> clientes = clienteDao.getInativos();
			Collections.sort(clientes);
			return clientes;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Cliente"}, e);
		}
	}

	@Override
	public List<Cliente> consultarTodos(final int firstRow, final int lastRow) throws PotiErpException {
		try {
			Collection<Cliente> collectionCliente = clienteDao.getAll(firstRow, lastRow);
			List<Cliente> clientes = new ArrayList<Cliente>(collectionCliente);
			Collections.sort(clientes);
			return clientes;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Cliente"}, e);
		}
	}

	@Override
	public List<Cliente> consultarAtivos(final int firstRow, final int lastRow) throws PotiErpException {
		try {
			List<Cliente> clientes = clienteDao.getAtivos(firstRow, lastRow);
			Collections.sort(clientes);
			return clientes;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Cliente"}, e);
		}
	}

	@Override
	public List<Cliente> consultarInativos(final int firstRow, final int lastRow) throws PotiErpException {
		try {
			List<Cliente> clientes = clienteDao.getInativos(firstRow, lastRow);
			Collections.sort(clientes);
			return clientes;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Cliente"}, e);
		}
	}

	@Override
	public Number consultarTotalClientes() throws PotiErpException {
		try{
			return clienteDao.getCountAll();
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_TOTAL_ENTIDADE, new Object[]{"Cliente"}, e);
		}
	}

	@Override
	public Number consultarTotalAtivos() throws PotiErpException {
		try{
			return clienteDao.getTotalAtivos();
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_TOTAL_ENTIDADE, new Object[]{"Cliente"}, e);
		}
	}

	@Override
	public Number consultarTotalInativos() throws PotiErpException {
		try{
			return clienteDao.getTotalInativos();
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_TOTAL_ENTIDADE, new Object[]{"Cliente"}, e);
		}
	}

	@Override
	public List<Cliente> consultarTodosComNomeFantasiaCodigo()
			throws PotiErpException {
		try {
			Collection<Cliente> collectionCliente = clienteDao.getAllNative();
			List<Cliente> clientes = new ArrayList<Cliente>(collectionCliente);
			return clientes;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Cliente"}, e);
		}
	}
	
	
}