package br.com.potierp.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.Feriado;
import br.com.potierp.util.CriteriaUtils;

/**
 * @author Doug
 *
 */
public class FeriadoDao extends BaseDao{

	/**
	 * @throws DaoException
	 */
	public FeriadoDao() throws DaoException {
		super();
	}

	@Override
	protected String getNamedQueryAll() {
		return Feriado.GET_ALL;
	}

	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return Feriado.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Feriado> getPorCriterios(final Feriado feriado) throws DaoException{
		try{
			Criteria c = createCriteria(Feriado.class);
			c.createAlias("cidade", "cid");
			c.createAlias("estado", "est");
			c.createAlias("pais", "p");
			
			if(feriado.getCidade() == null || feriado.getCidade().getId() == null) {
				CriteriaUtils.addIsNull(c, "cid.id");
			} else {
				CriteriaUtils.addEq(c, "cid.id", feriado.getCidade().getId());
			}
			
			if(feriado.getEstado() == null || feriado.getEstado().getId() == null) {
				CriteriaUtils.addIsNull(c, "est.id");
			} else {
				CriteriaUtils.addEq(c, "est.id", feriado.getEstado().getId());
			}
			
			if(feriado.getPais() == null || feriado.getPais().getId() == null) {
				CriteriaUtils.addIsNull(c, "p.id");
			} else {
				CriteriaUtils.addEq(c, "p.id", feriado.getPais().getId());
			}
			c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return c.list();
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"FERIADO", "CRITERIOS"}, ex);
		}
	}
	
	public List<Feriado> getPorPeriodo(final Date dataInicial, final Date dataFinal) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(Feriado.GET_ALL);//TODO[RENAN] VERIFICAR REGRA PARA FERIADOS
			return getResultList(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"Data Inicial", "Data Final"}, ex);
		}
	}
}
