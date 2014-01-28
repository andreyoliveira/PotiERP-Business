package br.com.potierp.business.financeiro.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.SolicitacaoPagamentoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.SolicitacaoPagamento;

@Stateless(mappedName = "SolicitacaoPagamentoService", name= "SolicitacaoPagamentoService")
@Local(SolicitacaoPagamentoService.class)
@Interceptors(DAOInterceptor.class)
public class SolicitacaoPagamentoServiceBean implements SolicitacaoPagamentoService{
	
	@DAO
	private SolicitacaoPagamentoDao solicitacaoPagamentoDao;

	@Override
	public SolicitacaoPagamento salvar(final SolicitacaoPagamento solicitacaoPagamento) throws PotiErpMensagensException, PotiErpException {
		try {
			verificarDuplicidade(solicitacaoPagamento);
			if(solicitacaoPagamento.isNew()){
				return this.incluir(solicitacaoPagamento);
			}
			return this.alterar(solicitacaoPagamento);
		} catch (DaoException de) {
			throw new PotiErpException(de);
		}
	}

	private void verificarDuplicidade(final SolicitacaoPagamento solicitacaoPagamento) throws DaoException, PotiErpMensagensException{
		//TODO [Batata] precisa verificar duplicidade?
	}

	private SolicitacaoPagamento incluir(final SolicitacaoPagamento solicitacaoPagamento) throws PotiErpMensagensException, PotiErpException{
		try {
			return solicitacaoPagamentoDao.salvar(solicitacaoPagamento);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[] { "SolicitacaoPagamento" }, e);
		}
	}

	private SolicitacaoPagamento alterar(final SolicitacaoPagamento solicitacaoPagamento) throws PotiErpMensagensException, PotiErpException{
		try {
			return solicitacaoPagamentoDao.salvar(solicitacaoPagamento);
		} catch (Exception e) {
			throw new PotiErpException( MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[] { "SolicitacaoPagamento" }, e);
		}
	}

	@Override
	public void excluir(final SolicitacaoPagamento solicitacaoPagamento) throws PotiErpMensagensException, PotiErpException {
		try {
			solicitacaoPagamentoDao.excluir(solicitacaoPagamento);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[] { "SolicitacaoPagamento" }, e);
		}
	}

	@Override
	public void excluir(final List<SolicitacaoPagamento> listaSolicitacaoPagamento) throws PotiErpMensagensException, PotiErpException {
		try {
			for(SolicitacaoPagamento solicitacaoPagamento : listaSolicitacaoPagamento){
				solicitacaoPagamentoDao.excluir(solicitacaoPagamento);
			}
			
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[] { "SolicitacaoPagamento" }, e);
		}
	}

	@Override
	public SolicitacaoPagamento consultarPorId(final Long idSolicitacaoPagamento) throws PotiErpException {
		try{
			return solicitacaoPagamentoDao.getByPrimaryKey(idSolicitacaoPagamento);
		} catch(Exception e){
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{}, e);
		}
	}

	@Override
	public List<SolicitacaoPagamento> consultarTodas() throws PotiErpException {
		try{
			Collection<SolicitacaoPagamento> collection = solicitacaoPagamentoDao.getAll();
			List<SolicitacaoPagamento> solicitacoes = new ArrayList<SolicitacaoPagamento>(collection);
			Collections.sort(solicitacoes);
			return solicitacoes;
		} catch(Exception e){
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{}, e);
		}
	}

}