package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.AfastamentoDao;
import br.com.potierp.dao.FuncionarioDao;
import br.com.potierp.dao.SituacaoFuncionarioDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Afastamento;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.SituacaoFuncionario;

@Stateless(mappedName="AfastamentoService", name="AfastamentoService")
@Local(AfastamentoService.class)
@Interceptors(DAOInterceptor.class)
public class AfastamentoServiceBean implements AfastamentoService {
	
	@DAO
	private AfastamentoDao afastamentoDao;
	
	@DAO
	private FuncionarioDao funcionarioDao;
	
	@DAO
	private SituacaoFuncionarioDao situacaoFuncionarioDao;

	@Override
	public Afastamento salvar(Afastamento afastamento) throws PotiErpException {
		try {
			SituacaoFuncionario situacaoFuncionario = afastamento.getFuncionario().getSituacaoFuncionario();
			
			Funcionario funcionario = funcionarioDao.getByPrimaryKey(afastamento.getFuncionario().getId());
			if(situacaoFuncionario.getId() != null) {
				situacaoFuncionario = situacaoFuncionarioDao.getByPrimaryKey(situacaoFuncionario.getId());
			} else {
				situacaoFuncionario = situacaoFuncionarioDao.getByPrimaryKey(funcionario.getSituacaoFuncionario().getId());
			}
			funcionario.setSituacaoFuncionario(situacaoFuncionario);
			afastamento.setFuncionario(funcionario);
			return afastamentoDao.salvar(afastamento);
		} catch (DaoException e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"Afastamento"}, e);
		}
	}

	@Override
	public List<Afastamento> consultarTodosAfastamento() throws PotiErpException {
		try {
			Collection<Afastamento> afastamento = afastamentoDao.getAll();
			return new ArrayList<Afastamento>(afastamento);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Afastamento"}, e);
		}
	}

	@Override
	public void excluirLista(List<Afastamento> afastamentos) throws PotiErpException {
		try {
			for(Afastamento afastamento : afastamentos) {
				afastamentoDao.excluir(afastamento);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"Afastamento"}, e);
		}
	}

}
