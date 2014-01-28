/**
 * 
 */
package br.com.potierp.business.endereco.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.EstadoDAO;
import br.com.potierp.dao.PaisDAO;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.Estado;
import br.com.potierp.model.Pais;
/**
 * @author felipe
 *
 */
@Stateless(mappedName = "EnderecoService", name="EnderecoService")
@Local(EnderecoService.class)
@Interceptors(DAOInterceptor.class)
public class EnderecoServiceBean implements EnderecoService {
	
	@DAO
	private EstadoDAO estadoDao;
	
	@DAO
	private PaisDAO paisDao;

	@Override
	public List<Pais> buscarTodosPaises() throws PotiErpException {
		try {
			Collection<Pais> paises = paisDao.getAll();
			List<Pais> listPaises = new ArrayList<Pais>(paises);
			Collections.sort(listPaises);
			return listPaises;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"Pais"}, e);
		}
	}
	
	public List<Estado> buscarEstadosPorPais(Pais pais) throws PotiErpException {
		try {
			Collection<Estado> estados = estadoDao.getEstadosPorPais(pais.getId());
			List<Estado> listEstados = new ArrayList<Estado>(estados);
			Collections.sort(listEstados);
			return listEstados;
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"Estado", "Pais"}, e);
		}
	}
	
	@Override
	public Pais buscarPaisPorSigla(String sigla) throws PotiErpException {
		try {
			return paisDao.getPaisPorSigla(sigla);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"Pais", "Sigla"}, e);
		}
	}
	
}