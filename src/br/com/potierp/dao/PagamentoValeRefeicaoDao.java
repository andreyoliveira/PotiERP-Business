package br.com.potierp.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.PagamentoValeRefeicao;

public class PagamentoValeRefeicaoDao extends BaseDao {

	public PagamentoValeRefeicaoDao() throws DaoException {
		super();
	}
	
	public List<PagamentoValeRefeicao> getPorCalculo(final Long idCalculo) throws Exception{
		try{
			Query query = getEntityManager().createNamedQuery(PagamentoValeRefeicao.GET_POR_CALCULO);
			query.setParameter("idCalculo", idCalculo);
			return getResultList(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"PAGAMENTO VR"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return PagamentoValeRefeicao.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return PagamentoValeRefeicao.class;
	}
	
	public List<?> getReciboValeRefeicao(final Date dataInicial, final Date dataFinal) {
		Query query = getEntityManager().createNamedQuery(PagamentoValeRefeicao.GET_RECIBO_VALE_REFEICAO);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		List<?> listRecibo = query.getResultList(); 
		return listRecibo;
	}
}