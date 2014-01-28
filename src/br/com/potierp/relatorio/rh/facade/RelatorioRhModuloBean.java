package br.com.potierp.relatorio.rh.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import br.com.potierp.business.rh.facade.RhModuloBean;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.model.CalculoValeRefeicao;
import br.com.potierp.model.CalculoValeTransporte;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.Responsavel;
import br.com.potierp.relatorio.rh.service.AutorizacaoDescContribAssitencialFamiliarService;
import br.com.potierp.relatorio.rh.service.AutorizacaoDescontoRefeicaoMundialService;
import br.com.potierp.relatorio.rh.service.AutorizacaoDescontoRefeicaoService;
import br.com.potierp.relatorio.rh.service.AutorizacaoDescontoUniformesService;
import br.com.potierp.relatorio.rh.service.CartaoPontoService;
import br.com.potierp.relatorio.rh.service.ContratoExperienciaService;
import br.com.potierp.relatorio.rh.service.DeclaracaoEncargoService;
import br.com.potierp.relatorio.rh.service.DesvioFuncaoService;
import br.com.potierp.relatorio.rh.service.FichaRegistroService;
import br.com.potierp.relatorio.rh.service.MapaValeRefeicaoService;
import br.com.potierp.relatorio.rh.service.MapaValeTransporteService;
import br.com.potierp.relatorio.rh.service.ProtocoloEntregaAtestadoMedicoService;
import br.com.potierp.relatorio.rh.service.ReciboDevolucaoCTPSService;
import br.com.potierp.relatorio.rh.service.ReciboValeRefeicaoService;
import br.com.potierp.relatorio.rh.service.ReciboValeTransporteService;
import br.com.potierp.relatorio.rh.service.SalarioFamiliaService;
import br.com.potierp.relatorio.rh.service.SolicitacaoValeTransporteService;
import br.com.potierp.relatorio.rh.service.TermoAutorizacaoDescontoEquipamentoService;
import br.com.potierp.relatorio.rh.service.TermoDescontoRefeicaoService;
import br.com.potierp.relatorio.rh.service.TermoResponsabilidaCrachaService;
import br.com.potierp.relatorio.rh.service.VencimentoExperienciaService;

/**
 * @author Doug
 *
 */
@Stateless(mappedName="RelatorioRhModulo", name="RelatorioRhModulo")
@Remote(RelatorioRhModulo.class)
public class RelatorioRhModuloBean implements RelatorioRhModulo {
	
	private static Logger log = Logger.getLogger(RhModuloBean.class);
	
	@EJB
	private FichaRegistroService fichaRegistroService;
	
	@EJB
	private ReciboValeTransporteService reciboValeTransporteService;
	
	@EJB
	private MapaValeTransporteService mapaValeTransporteService;
	
	@EJB
	private MapaValeRefeicaoService mapaValeRefeicaoService;
	
	@EJB
	private ReciboValeRefeicaoService reciboValeRefeicaoService;
	
	@EJB
	private ContratoExperienciaService contratoExeperienciaService;

	@EJB
	private SalarioFamiliaService salarioFamiliaService;
	
	@EJB
	private SolicitacaoValeTransporteService solicitacaoValeTransporteService;
	
	@EJB
	private VencimentoExperienciaService vencimentoExperienciaService;
	
	@EJB
	private DeclaracaoEncargoService declaracaoEncargoService;
	
	@EJB
	private DesvioFuncaoService desvioFuncaoService;
		
	@EJB
	private TermoDescontoRefeicaoService termoDescontoRefeicaoService;
	
	@EJB
	private AutorizacaoDescontoRefeicaoMundialService autorizacaoDescontoRefeicaoMundialService;
	
	@EJB
	private AutorizacaoDescontoRefeicaoService autorizacaoDescontoRefeicaoService;
	
	@EJB
	private AutorizacaoDescontoUniformesService autorizacaoDescontoUniformesService;
	
	@EJB
	private AutorizacaoDescContribAssitencialFamiliarService autorizacaoDescContribAssitencialFamiliarService;
	
	@EJB
	private TermoResponsabilidaCrachaService termoResponsabilidaCrachaService;
	
	@EJB
	private ReciboDevolucaoCTPSService reciboDevolucaoCTPSService;
	
	@EJB
	private ProtocoloEntregaAtestadoMedicoService protocoloEntregaAtestadoMedicoService;

	@EJB
	private TermoAutorizacaoDescontoEquipamentoService termoAutorizacaoDescontoEquipamentoService;
	
	@EJB
	private CartaoPontoService cartaoPontoService;
	
	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.facade.FichaRegistroModulo#getRelatorio(java.util.List)
	 */
	@Override
	public byte[] getFichaRegistro(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			return fichaRegistroService.getRelatorio(listFuncionario);
		}  catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.facade.RelatorioRhModulo#getReciboValeTransporte(java.util.List)
	 */
	@Override
	public byte[] getReciboValeTransporte(
			final List<CalculoValeTransporte> listCalculoValeTransporte)
			throws PotiErpException {
		try {
			return reciboValeTransporteService.getRelatorio(listCalculoValeTransporte);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.facade.RelatorioRhModulo#getMapaValeTransporte(java.util.List)
	 */
	@Override
	public byte[] getMapaValeTransporte(
			final List<CalculoValeTransporte> listCalculoValeTransporte)
			throws PotiErpException {
		try {
			return mapaValeTransporteService.getRelatorio(listCalculoValeTransporte);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.facade.RelatorioRhModulo#getReciboValeRefeicao(java.util.List)
	 */
	@Override
	public byte[] getReciboValeRefeicao(
			final List<CalculoValeRefeicao> listCalculoValeRefeicao)
			throws PotiErpException {
		try {
			return reciboValeRefeicaoService.getRelatorio(listCalculoValeRefeicao);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.facade.RelatorioRhModulo#getMapaValeRefeicao(java.util.List)
	 */
	@Override
	public byte[] getMapaValeRefeicao(
			final List<CalculoValeRefeicao> listCalculoValeRefeicao)
			throws PotiErpException {
		try {
			return mapaValeRefeicaoService.getRelatorio(listCalculoValeRefeicao);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.facade.RelatorioRhModulo#getContratoExperiencia(java.util.List)
	 */
	@Override
	public byte[] getContratoExperiencia(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			return contratoExeperienciaService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.facade.RelatorioRhModulo#getSalarioFamilia(java.util.List)
	 */
	@Override
	public byte[] getSalarioFamilia(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			return salarioFamiliaService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.facade.RelatorioRhModulo#getSolicitacaoValeTransporte(java.util.List)
	 */
	@Override
	public byte[] getSolicitacaoValeTransporte(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			return solicitacaoValeTransporteService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.facade.RelatorioRhModulo#getVencimentoExperiencia(java.util.List)
	 */
	@Override
	public byte[] getVencimentoExperiencia(
			final List<Funcionario> listFuncionario, final Date dataInicio,
			final Date dataFim, final Cidade cidade,
			final Responsavel responsavel) throws PotiErpException {
		try {
			return vencimentoExperienciaService.getRelatorio(listFuncionario, dataInicio, dataFim, cidade, responsavel);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		
	}

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.facade.RelatorioRhModulo#getDeclaracaoEncargos(java.util.List)
	 */
	@Override
	public byte[] getDeclaracaoEncargos(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			return declaracaoEncargoService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.facade.RelatorioRhModulo#getDeclaracaoEncargos(java.util.List)
	 */
	@Override
	public byte[] getDesvioFuncao(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			return desvioFuncaoService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public byte[] getAutorizacaoDescontoRefeicaoCesari(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			return termoDescontoRefeicaoService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public byte[] getAutorizacaoDescontoRefeicaoMundial(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			return autorizacaoDescontoRefeicaoMundialService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public byte[] getAutorizacaoDescontoRefeicao(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			return autorizacaoDescontoRefeicaoService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public byte[] getAutorizacaoDescontoUniformes(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			return autorizacaoDescontoUniformesService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override	  
	public byte[] getAutorizacaoDescContribAssitencialFamiliar(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			return autorizacaoDescContribAssitencialFamiliarService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override	  
	public byte[] getTermoResponsabilidadeCracha(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			return termoResponsabilidaCrachaService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public byte[] getReciboDevolucaoCTPS(final List<Funcionario> listFuncionario)
			throws PotiErpException {
		try {
			return reciboDevolucaoCTPSService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public byte[] getProtocoloEntregaAtestadoMedico(
			final List<Funcionario> listFuncionario) throws PotiErpException {
		try {
			return protocoloEntregaAtestadoMedicoService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public byte[] getTermoAutorizacaoDescontoEquipamento(
			final List<Funcionario> listFuncionario) throws PotiErpException {
		try {
			return termoAutorizacaoDescontoEquipamentoService.getRelatorio(listFuncionario);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public byte[] getCartaoPonto(List<Funcionario> listFuncionario, String mes, String ano) throws PotiErpException {
		try {
			return cartaoPontoService.getRelatorio(listFuncionario, mes, ano);
		} catch (PotiErpException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}