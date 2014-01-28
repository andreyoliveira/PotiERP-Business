package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.ParametrosRhDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.ParametrosRh;

/**
 * @author renan
 */
@Stateless(mappedName="ParametrosRhService", name="ParametrosRhService")
@Local(ParametrosRhService.class)
@Interceptors(DAOInterceptor.class)
public class ParametrosRhServiceBean implements ParametrosRhService {

	@DAO
	private ParametrosRhDao parametrosRhDao;
	
	@Override
	public ParametrosRh salvarParametrosRh(final ParametrosRh parametrosRh)
			throws PotiErpMensagensException, PotiErpException {
		return alterarParametrosRh(parametrosRh);
	}
	
	private ParametrosRh alterarParametrosRh(final ParametrosRh parametrosRh) throws PotiErpException {
		try {
			return parametrosRhDao.salvar(parametrosRh);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, e);		
		}
	}
	
	@Override
	public List<ParametrosRh> consultarTodosParametrosRh() throws PotiErpException {
		try {
			Collection<ParametrosRh> collectionParametrosRh = parametrosRhDao.getAll();
			return new ArrayList<ParametrosRh>(collectionParametrosRh);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"ParametrosRh"}, e);
		}
	}
}