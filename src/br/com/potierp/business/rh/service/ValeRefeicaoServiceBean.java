package br.com.potierp.business.rh.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.CalculoValeRefeicaoDao;
import br.com.potierp.dao.FeriadoDao;
import br.com.potierp.dao.FuncionarioDao;
import br.com.potierp.dao.PagamentoValeRefeicaoDao;
import br.com.potierp.dao.ParametrosRhDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.DaoException;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.exception.PotiErpMensagensException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.model.CalculoValeRefeicao;
import br.com.potierp.model.Cidade;
import br.com.potierp.model.Cliente;
import br.com.potierp.model.Estado;
import br.com.potierp.model.Feriado;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.LocalTrabalho;
import br.com.potierp.model.PagamentoValeRefeicao;
import br.com.potierp.model.ParametrosRh;
import br.com.potierp.model.SituacaoValeRefeicaoEnum;
import br.com.potierp.model.TipoFeriadoEnum;
import br.com.potierp.model.ValeRefeicao;
import br.com.potierp.util.DateUtil;

/**
 * @author renan
 */
@Stateless(mappedName="ValeRefeicaoService", name="ValeRefeicaoService")
@Local(ValeRefeicaoService.class)
@Interceptors(DAOInterceptor.class)
public class ValeRefeicaoServiceBean implements ValeRefeicaoService {

	@DAO
	private FeriadoDao feriadoDao;

	@DAO
	private FuncionarioDao funcionarioDao;
	
	@DAO
	private CalculoValeRefeicaoDao calculoValeRefeicaoDao;
	
	@DAO
	private PagamentoValeRefeicaoDao pagamentoValeRefeicaoDao;
	
	@DAO
	private ParametrosRhDao parametrosRhDao;
	
	@Override
	public CalculoValeRefeicao calcular(final CalculoValeRefeicao calculoValeRefeicao) throws PotiErpMensagensException,
			PotiErpException {
		try{
			List<Feriado> feriados = buscarFeriados(calculoValeRefeicao.getDataInicio(), calculoValeRefeicao.getDataFim());

			List<Funcionario> funcionarios = buscarFuncionariosTrabalhando();

			List<PagamentoValeRefeicao> pagamentos = realizarCalculo(
					calculoValeRefeicao, calculoValeRefeicao.getDataInicio(),
					calculoValeRefeicao.getDataFim(), feriados, funcionarios);

			calculoValeRefeicao.setPagamentosValeRefeicao(pagamentos);
			calculoValeRefeicao.setSituacao(SituacaoValeRefeicaoEnum.CALCULADO);
			calculoValeRefeicao.setMesAno(getMesAno(calculoValeRefeicao.getDataInicio()));
			calculoValeRefeicao.somarPagamentos();
			calculoValeRefeicao.setDataRecibo(getDataRecibo(feriados, calculoValeRefeicao.getDataInicio()));
			calculoValeRefeicaoDao.salvar(calculoValeRefeicao);
			CalcularValeRefeicao calcularValeRefeicao = new CalcularValeRefeicao(calculoValeRefeicao);
			calcularValeRefeicao.calcular();
			return calculoValeRefeicao;
		}catch(Exception ex){
			throw new PotiErpException(ex);
		}
	}
	
	private Date getDataRecibo(final List<Feriado> feriados, final Date dataReferencia) throws Exception {
		CalculoDiaUtil calculoDiaUtil = new CalculoDiaUtil(5, dataReferencia, feriados);
		calculoDiaUtil.calcular();
		return calculoDiaUtil.getDiaUtilDoMes();
	}

	private String getMesAno(final Date dataInicioCalculo) {
		return DateUtil.convertDateToReferencia(dataInicioCalculo);
	}

	@Override
	public void gravar(final CalculoValeRefeicao calculoValeRefeicao)
			throws PotiErpMensagensException, PotiErpException {
		try{
			calculoValeRefeicao.setSituacao(SituacaoValeRefeicaoEnum.GRAVADO);
			calculoValeRefeicaoDao.salvar(calculoValeRefeicao);
		}catch(Exception ex){
			throw new PotiErpException(ex);
		}
	}
	
	@Override
	public void excluirCalculo(final CalculoValeRefeicao calculoValeRefeicao)
			throws PotiErpMensagensException, PotiErpException {
		try{
			calculoValeRefeicaoDao.excluir(calculoValeRefeicao);
		}catch(Exception ex){
			throw new PotiErpException(ex);
		}
	}
	
	@Override
	public List<CalculoValeRefeicao> consultarTodos() throws PotiErpException{
		try{
			Collection<CalculoValeRefeicao> calculos = calculoValeRefeicaoDao.getAll();
			return new ArrayList<CalculoValeRefeicao>(calculos);
		}catch(Exception ex){
			throw new PotiErpException(ex);
		}
	}

	private List<PagamentoValeRefeicao> realizarCalculo(final CalculoValeRefeicao calculoValeRefeicao, final Date dataInicioCalculo,
			final Date dataFinalCalculo, final List<Feriado> feriados,
			final List<Funcionario> funcionarios) throws Exception {

		List<PagamentoValeRefeicao> pagamentos = new ArrayList<PagamentoValeRefeicao>();
		
		Long quantidadeHoras = getHorasParamentoValeRefeicao();

		for(Funcionario funcionario : funcionarios){

			if(funcionario.getValesRefeicao() != null 
					&& funcionario.getValesRefeicao().size() > 0){

				List<ValeRefeicao> valesRefeicaos = new ArrayList<ValeRefeicao>(funcionario.getValesRefeicao());
				List<LocalTrabalho> locaisTrabalhos = new ArrayList<LocalTrabalho>(funcionario.getLocaisTrabalho());

				Calendar dataInicio = DateUtil.convertDateToCalendar((Date)dataInicioCalculo.clone());
				Calendar dataFinal = DateUtil.convertDateToCalendar((Date)dataFinalCalculo.clone());
				while(dataInicio.compareTo(dataFinal) <= 0){
					verificarPagamento(calculoValeRefeicao,
							dataInicio.getTime(), feriados, valesRefeicaos,
							locaisTrabalhos, pagamentos, quantidadeHoras);
					dataInicio.add(Calendar.DAY_OF_MONTH, 1);
				}
			}
		}
		return pagamentos;
	}

	private Long getHorasParamentoValeRefeicao() throws Exception {
		ParametrosRh parametrosRh = new ParametrosRh();
		parametrosRh.setNome("HORAS_PAGAMENTO_VR");
		Long quantidadeHoras = Long.valueOf(parametrosRhDao.getPorNome(parametrosRh).getValor().trim());
		return quantidadeHoras;
	}

	private void verificarPagamento(
			final CalculoValeRefeicao calculoValeRefeicao,
			final Date dataCalculo, final List<Feriado> feriados,
			final List<ValeRefeicao> valesRefeicaos,
			final List<LocalTrabalho> locaisTrabalhos,
			final List<PagamentoValeRefeicao> pagamentos,
			final Long quantidadeHoras) {

		List<LocalTrabalho> locais = getLocalTrabalhoDoDia(dataCalculo, locaisTrabalhos);
		if(locais.size() > 0){

			Feriado feriado = getFeriado(dataCalculo, feriados);
			Boolean isDiaEhSabado = isDiaEhSabado(dataCalculo);
			Boolean isDiaEhDomingo = isDiaEhDomingo(dataCalculo);

			Long horas = 0L;
			for(LocalTrabalho localTrabalho : locais){
				Cliente cliente = localTrabalho.getCliente();
				if(feriado != null && !isFazerPagamentoFeriado(feriado, cliente)){
					continue;
				}
				if((isDiaEhSabado && !cliente.getIsSabadoDiaUtil())
						|| (isDiaEhDomingo && !cliente.getIsDomingoDiaUtil())){
					continue;
				}
				
				if(localTrabalho.getJornadaTrabalho().isDiaSeguinte()){
					horas = horas
					+ (Long.valueOf(localTrabalho.getJornadaTrabalho().getHorarioFinal()) + 2400
					- Long.valueOf(localTrabalho.getJornadaTrabalho().getHorarioInicial() ));
				}else{
					horas = horas
					+ (Long.valueOf(localTrabalho.getJornadaTrabalho().getHorarioFinal())
					- Long.valueOf(localTrabalho.getJornadaTrabalho().getHorarioInicial()));
				}
					
				if(horas.compareTo(quantidadeHoras) == 1){
					pagar(calculoValeRefeicao, dataCalculo, cliente, valesRefeicaos, pagamentos);
					break;
				}
			}
		}
	}
	
	private boolean isFazerPagamentoFeriado(final Feriado feriado, final Cliente cliente) {
		Boolean isNacional = feriado.getTipoFeriado().equals(TipoFeriadoEnum.NACIONAL);
		if(isNacional && cliente.getIsTrabalhaFeriado()){
			return true;
		}
		Boolean isEstadual = feriado.getTipoFeriado().equals(TipoFeriadoEnum.ESTADUAL);
		if(isEstadual){
			Estado estadoCliente = cliente.getEndereco().getEstado();
			Estado estadoFeriado = feriado.getEstado();
			if(estadoCliente.equals(estadoFeriado) && cliente.getIsTrabalhaFeriado()){
				return true;
			}
		}
		Boolean isMunicipal = feriado.getTipoFeriado().equals(TipoFeriadoEnum.MUNICIPAL);
		if(isMunicipal){
			Cidade cidadeCliente = cliente.getCidade();
			Cidade cidadeFeriado = feriado.getCidade();
			if(cidadeCliente.equals(cidadeFeriado) && cliente.getIsTrabalhaFeriado()){
				return true;
			}
		}
		return false;
	}
	
	private void pagar(final CalculoValeRefeicao calculoValeRefeicao, final Date dataValeRefeicao, final Cliente cliente,
			final List<ValeRefeicao> valesRefeicaos,
			final List<PagamentoValeRefeicao> pagamentos) {

		for(ValeRefeicao valeRefeicao : valesRefeicaos){
			PagamentoValeRefeicao pagamentoValeRefeicao = new PagamentoValeRefeicao();
			pagamentoValeRefeicao.setDataValeRefeicao(dataValeRefeicao);
			pagamentoValeRefeicao.setFuncionario(valeRefeicao.getFuncionario());
			pagamentoValeRefeicao.setTipoValeRefeicao(valeRefeicao.getTipoValeRefeicao());
			pagamentoValeRefeicao.setCalculoValeRefeicao(calculoValeRefeicao);
			pagamentos.add(pagamentoValeRefeicao);
		}
	}

	private List<LocalTrabalho> getLocalTrabalhoDoDia(final Date dataInicio,
			final List<LocalTrabalho> locaisTrabalhos) {
		
		int diaSemana = getDiaSemana(dataInicio);
		List<LocalTrabalho> locaisTrabalhoDoDia = new ArrayList<LocalTrabalho>();
		
		for(LocalTrabalho localTrabalho : locaisTrabalhos){
			if(localTrabalho.getDiaSemana().getDiaCalendar() == diaSemana){
				locaisTrabalhoDoDia.add(localTrabalho);
			}
		}
		return locaisTrabalhoDoDia;
	}

	private int getDiaSemana(final Date dataInicio) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dataInicio.getTime());
		int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
		return diaSemana;
	}

	private Boolean isDiaEhSabado(final Date dataInicio) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dataInicio.getTime());
		return DateUtil.isSabado(cal);
	}

	private Boolean isDiaEhDomingo(final Date dataInicio) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dataInicio.getTime());
		return DateUtil.isDomingo(cal);
	}

	private Feriado getFeriado(final Date dataDia, final List<Feriado> feriados) {
		for(Feriado feriado : feriados){
			Calendar calFeriado = Calendar.getInstance();
			calFeriado.setTime(feriado.getData());
			
			Calendar calDataDia = Calendar.getInstance();
			calDataDia.setTime(dataDia);
			
			if(calFeriado.get(Calendar.DAY_OF_MONTH) == calDataDia.get(Calendar.DAY_OF_MONTH)
					&& calFeriado.get(Calendar.MONTH) == calDataDia.get(Calendar.MONTH)) {
				if(calFeriado.get(Calendar.YEAR) == calDataDia.get(Calendar.YEAR)
						|| !feriado.getIsExpira()){
					return feriado;
				}
			}
		}
		return null;
	}

	private List<Feriado> buscarFeriados(final Date dataInicioCalculo,
			final Date dataFinalCalculo) throws DaoException {
		List<Feriado> feriados = feriadoDao.getPorPeriodo(dataInicioCalculo, dataFinalCalculo);
		return feriados;
	}

	private List<Funcionario> buscarFuncionariosTrabalhando() throws DaoException {
		List<Funcionario> funcionarios = funcionarioDao.getAptosValeRefeicao();
		return funcionarios;
	}
	
	public List<PagamentoValeRefeicao> getPagamentosPorCalculo(Long idCalculo)
			throws PotiErpMensagensException, PotiErpException {
		try{
			return pagamentoValeRefeicaoDao.getPorCalculo(idCalculo);
		}catch(Exception ex){
			throw new PotiErpException(ex);
		}
	}
}