package br.com.potierp.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.BaseEntity;
import br.com.potierp.model.LocalTrabalho;

public class LocalTrabalhoDao extends BaseDao {

	public LocalTrabalhoDao() throws DaoException {
		super();
	}
	
	public void excluirLista(final List<LocalTrabalho> locaisTrabalho) throws DaoException{
		try{
			for(LocalTrabalho localTrabalho : locaisTrabalho){
				excluir(localTrabalho);
			}
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"LOCALTRABALHO"}, ex);
		}
	}
	
	public void salvarLista(final List<LocalTrabalho> locaisTrabalho) throws DaoException{
		try{
			for(LocalTrabalho localTrabalho : locaisTrabalho){
				salvar(localTrabalho);
			}
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_ADICIONAR_LISTA, new Object[]{"LOCALTRABALHO"}, ex);
		}
	}
	
	public List<LocalTrabalho> getPorFuncionarioComSetor(final Long codigoRegistro) throws DaoException{
		try{
			Query query = getEntityManager().createNamedQuery(LocalTrabalho.GET_POR_FUNCIONARIO_COM_SETOR);
			query.setParameter("codigoRegistro", codigoRegistro);
			return getResultList(query);
		}catch(Exception ex){
			throw new DaoException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"LOCALTRABALHO"}, ex);
		}
	}

	@Override
	protected String getNamedQueryAll() {
		return null;
	}
	
	@Override
	protected String getNamedQueryCountAll(){
		return null;
	}

	@Override
	protected Class<? extends BaseEntity> getClassEntity() {
		return LocalTrabalho.class;
	}
}