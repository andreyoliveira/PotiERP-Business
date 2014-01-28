package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.AlteracaoClienteDao;
import br.com.potierp.dao.FuncionarioDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.AlteracaoCliente;
import br.com.potierp.model.Cliente;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.LocalTrabalho;

@Stateless(mappedName="AlteracaoClienteService", name="AlteracaoClienteService")
@Local(AlteracaoClienteService.class)
@Interceptors(DAOInterceptor.class)
public class AlteracaoClienteServiceBean implements AlteracaoClienteService{
	
	@DAO
	private AlteracaoClienteDao alteracaoClienteDao;
	
	@DAO
	private FuncionarioDao funcionarioDao;

	@Override
	public List<AlteracaoCliente> consultarTodos()
			throws PotiErpException {
		try {
			Collection<AlteracaoCliente> alteracoesCliente = alteracaoClienteDao.getAll();
			return new ArrayList<AlteracaoCliente>(alteracoesCliente);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"AlteracaoCliente"}, e);
		}
	}

	@Override
	public AlteracaoCliente salvar(final AlteracaoCliente alteracaoCliente) throws PotiErpException {
		try {
			Funcionario funcionario = funcionarioDao.getByPrimaryKey(alteracaoCliente.getFuncionario().getId());
		    List<LocalTrabalho> locaisTrabalho = funcionario.getLocaisTrabalho();
		    for(LocalTrabalho localTrabalho : locaisTrabalho){
		    	Cliente cliente = localTrabalho.getCliente();
		    	if(cliente.getId().equals(alteracaoCliente.getClienteAnterior().getId())){
		    		localTrabalho.setCliente(alteracaoCliente.getClienteAtual());
		    	}
		    }
			funcionario = funcionarioDao.salvar(funcionario);
			alteracaoCliente.setFuncionario(funcionario);
			return alteracaoClienteDao.salvar(alteracaoCliente);
		} catch (DaoException e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{"AlteracaoCliente"}, e);		
		}
	}

	@Override
	public void excluir(final AlteracaoCliente alteracaoCliente) throws PotiErpException {
		try {
			alteracaoClienteDao.excluir(alteracaoCliente);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"AlteracaoCliente"}, e);		
		}
	}

	@Override
	public void excluirLista(final List<AlteracaoCliente> alteracoesCliente)
			throws PotiErpException {
		try {
			for (AlteracaoCliente alteracaoCliente : alteracoesCliente) {
				alteracaoClienteDao.excluir(alteracaoCliente);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"AlteracaoCliente"}, e);		
		}
	}
}
