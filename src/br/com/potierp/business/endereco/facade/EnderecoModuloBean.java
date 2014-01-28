package br.com.potierp.business.endereco.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import br.com.potierp.business.endereco.service.CidadeService;
import br.com.potierp.business.endereco.service.EnderecoService;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Estado;
import br.com.potierp.model.Pais;

/**
 */
@Stateless(mappedName="EnderecoModulo", name="EnderecoModulo")
@Remote(EnderecoModulo.class)
public class EnderecoModuloBean implements EnderecoModulo{
	
	private static Logger log = Logger.getLogger(EnderecoModuloBean.class);
	
	@EJB
	private EnderecoService enderecoService;
	
	@EJB
	private CidadeService cidadeService;
	

	@Override
	public List<Pais> buscarTodosPaises() throws PotiErpException {
		try{
			return enderecoService.buscarTodosPaises();
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Estado> buscarEstadosPorPais(final Pais pais) throws PotiErpException {
		try{
			return enderecoService.buscarEstadosPorPais(pais);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Pais buscarPaisPorSigla(final String sigla) throws PotiErpException {
		try{
			return enderecoService.buscarPaisPorSigla(sigla);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public Cidade salvarCidade(final Cidade cidade) throws PotiErpMensagensException, PotiErpException {
		try {
			return cidadeService.salvarCidade(cidade);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirCidade(final Cidade cidade) throws PotiErpMensagensException, PotiErpException {
		try {
			cidadeService.excluirCidade(cidade);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void excluirListaCidade(final List<Cidade> cidades) throws PotiErpMensagensException, PotiErpException {
		try {
			cidadeService.excluirListaCidade(cidades);
		} catch (PotiErpMensagensException pme) {
			log.error(pme.getMessage(), pme);
			throw pme;
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Cidade> consultarCidade(final Cidade cidade) throws PotiErpException {
		try {
			return cidadeService.consultarCidade(cidade);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public List<Cidade> consultarTodasCidades() throws PotiErpException {
		try {
			return cidadeService.consultarTodasCidades();
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public List<Cidade> consultarPorEstado(final Estado estado) throws PotiErpException {
		try {
			return cidadeService.consultarPorEstado(estado);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}