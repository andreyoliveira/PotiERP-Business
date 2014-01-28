package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.FeriadoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Feriado;

/**
 * @author Doug
 *
 */
@Stateless(mappedName="FeriadoService", name="FeriadoService")
@Local(FeriadoService.class)
@Interceptors(DAOInterceptor.class)
public class FeriadoServiceBean implements FeriadoService{
	
	@DAO
	private FeriadoDao feriadoDao;

	/* (non-Javadoc)
	 * @see br.com.potierp.business.rh.service.FeriadoService#salvar(br.com.potierp.model.Feriado)
	 */
	@Override
	public Feriado salvar(final Feriado feriado) throws PotiErpMensagensException,
			PotiErpException {
		try {
			//TODO: Verificar essa rotina de valida��o verificarDuplicidade(feriado);
			if(feriado.isNew()){
				return incluirFeriado(feriado);
			}else{
				return alterarFeriado(feriado);
			}
		} catch (Exception e) {
			throw new PotiErpException(e);
		}
	}

	/**
	 * @param feriado
	 * @return
	 * @throws PotiErpException 
	 */
	private Feriado alterarFeriado(final Feriado feriado) throws PotiErpException {
		try {
			return feriadoDao.salvar(feriado);
		} catch (Exception de) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ADICIONAR_ENTIDADE, new Object[]{"Feriado"}, de);		
		}
	}

	/**
	 * @param feriado
	 * @return
	 * @throws PotiErpException 
	 */
	private Feriado incluirFeriado(final Feriado feriado) throws PotiErpException {
		try {
			return feriadoDao.salvar(feriado);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"Feriado"}, e);		
		}
	}

	/**
	 * @param feriado
	 * @throws PotiErpMensagensException 
	 * @throws DaoException 
	 */
	/*private void verificarDuplicidade(Feriado feriado) throws PotiErpMensagensException, DaoException {
		List<Feriado> listFeriado = feriadoDao.getPorCriterios(feriado);
		if(listFeriado.size() > 0) {
			throw new PotiErpMensagensException(MensagensExceptionEnum.ERRO_JA_EXISTE_FERIADO_COM_ESSES_CRITERIOS.getMsg());
		}
	}*/

	@Override
	public List<Feriado> consultarTodosFeriados()
			throws PotiErpMensagensException, PotiErpException {
		try {
			Collection<Feriado> collectionFeriado = feriadoDao.getAll();
			return new ArrayList<Feriado>(collectionFeriado);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Feriado"}, e);
		}
	}
	
	@Override
	public Feriado consultar(final Long idFeriado) throws PotiErpException {
		try{
			return feriadoDao.getByPrimaryKey(idFeriado);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"Feriado"}, e);
		}
	}

	@Override
	public void excluir(final Feriado feriado) throws PotiErpMensagensException,
			PotiErpException {
		try {
			feriadoDao.excluir(feriado);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"Feriado"}, e);
		}
	}

	@Override
	public void excluirLista(final List<Feriado> feriados)
	throws PotiErpMensagensException, PotiErpException {
		try {
			for(Feriado feriado : feriados){
				feriadoDao.excluir(feriado);
			}
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA, new Object[]{"Feriados"}, e);
		}		
	}

}
