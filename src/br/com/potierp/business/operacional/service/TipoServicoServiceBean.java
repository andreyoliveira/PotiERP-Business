package br.com.potierp.business.operacional.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.TipoServicoDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.TipoServico;

/**
 * 
 * @author Andrey Oliveira
 *
 */
@Stateless(mappedName="TipoServicoService", name="TipoServicoService")
@Local(TipoServicoService.class)
@Interceptors(DAOInterceptor.class)
public class TipoServicoServiceBean implements TipoServicoService {
	
	@DAO
	private TipoServicoDao tipoServicoDao;

	@Override
	public List<TipoServico> consultarTodos() throws PotiErpException {
		try {
			Collection<TipoServico> tiposServico = this.tipoServicoDao.getAll();
			return new ArrayList<TipoServico>(tiposServico);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"TipoServico"}, e);
		}
	}

}
