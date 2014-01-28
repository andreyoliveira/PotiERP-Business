package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.FuncionarioDao;
import br.com.potierp.dao.HistoricoDemissaoDao;
import br.com.potierp.dao.SituacaoFuncionarioDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.HistoricoDemissao;
import br.com.potierp.model.SituacaoFuncionario;

/**
 * @author Andrey Oliveira
 */
@Stateless(mappedName="HistoricoDemissaoService", name="HistoricoDemissaoService")
@Local(HistoricoDemissaoService.class)
@Interceptors(DAOInterceptor.class)
public class HistoricoDemissaoServiceBean implements HistoricoDemissaoService {
	
	@DAO
	private HistoricoDemissaoDao historicoDemissaoDao;
	
	@DAO
	private FuncionarioDao funcionarioDao;
	
	@DAO
	private SituacaoFuncionarioDao situacaoFuncionarioDao;

	@Override
	public List<HistoricoDemissao> consultarTodosHistoricosDemissoes()
			throws PotiErpException {
		try {
			Collection<HistoricoDemissao> historicoDemissoes = historicoDemissaoDao.getAll();
			return new ArrayList<HistoricoDemissao>(historicoDemissoes);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"HistoricoDemissao"}, e);
		}
	}

	@Override
	public HistoricoDemissao salvar(HistoricoDemissao historicoDemissao)
			throws PotiErpException {
		try {
			SituacaoFuncionario situacaoFuncionario = situacaoFuncionarioDao.getByPrimaryKey(historicoDemissao.getFuncionario().getSituacaoFuncionario().getId());
			Funcionario funcionario = funcionarioDao.getByPrimaryKey(historicoDemissao.getFuncionario().getId());
			funcionario.setSituacaoFuncionario(situacaoFuncionario);
			historicoDemissao.setFuncionario(funcionario);
			return historicoDemissaoDao.salvar(historicoDemissao);
		}  catch (DaoException e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"HistoricoDemissao"}, e);
		}
	}

	@Override
	public void excluir(HistoricoDemissao historicoDemissao)
			throws PotiErpException {
		try {
			historicoDemissaoDao.excluir(historicoDemissao);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"HistoricoDemissao"}, e);
		}
	}

	@Override
	public void excluirLista(List<HistoricoDemissao> listHistoricoDemissao)
			throws PotiErpException {
		try {
			for(HistoricoDemissao d : listHistoricoDemissao) {
				historicoDemissaoDao.excluir(d);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"HistoricoDemissao"}, e);
		}
		
	}

	@Override
	public List<HistoricoDemissao> buscarPorFuncionario(Long idFuncionario)
			throws PotiErpException {
		try {
			return historicoDemissaoDao.getPorFuncionario(idFuncionario);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"HistoricoDemissao", "idFuncionario"}, e);
		}
	}
}
