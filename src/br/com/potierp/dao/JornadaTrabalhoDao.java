package br.com.potierp.dao;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.JornadaTrabalho;

public class JornadaTrabalhoDao extends BaseDao {

	public JornadaTrabalhoDao() throws DaoException {
		super();
	}
	
	public JornadaTrabalho getByNome(final JornadaTrabalho jornadaTrabalho) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(JornadaTrabalho.GET_BY_NOME);
			query.setParameter("nome", jornadaTrabalho.getNome().trim());
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"JORNADATRABALHO", "NOME"}, ex);
		}
	}
	
	public JornadaTrabalho getPorIdComIntervalo(Long idJornada) throws DaoException {
		try{
			Query query = getEntityManager().createNamedQuery(JornadaTrabalho.GET_POR_ID_COM_INTERVALO);
			query.setParameter("id", idJornada);
			return getSingleResult(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"JORNADATRABALHO", "ID"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return JornadaTrabalho.GET_ALL;
	}
	
	@Override
	protected String getNamedQueryCountAll() {
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return JornadaTrabalho.class;
	}
}