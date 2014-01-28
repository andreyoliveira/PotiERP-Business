package br.com.potierp.business.operacional.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.dao.DataProgramacaoVisitaDao;
import br.com.potierp.dao.ProgramacaoVisitaDao;
import br.com.potierp.infra.annotation.DAO;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.DataProgramacaoVisita;
import br.com.potierp.model.ProgramacaoVisita;
import br.com.potierp.model.Responsavel;
import br.com.potierp.util.report.JasperReportUtilHelper;
import br.com.potierp.util.report.PotiJasperUtil;

/**
 * @author Ralph
 */
@Stateless(mappedName="ProgramacaoVisitaService", name="ProgramacaoVisitaService")
@Local(ProgramacaoVisitaService.class)
@Interceptors(DAOInterceptor.class)
public class ProgramacaoVisitaServiceBean implements ProgramacaoVisitaService {
	
	@DAO
	private ProgramacaoVisitaDao programacaoVisitaDao;
	
	@DAO
	private DataProgramacaoVisitaDao dataProgramacaoVisitaDao;

	@Override
	public List<ProgramacaoVisita> consultarTotasProgramacoesVisita() throws PotiErpException {
		try {
			Collection<ProgramacaoVisita> programacoesVisitas = programacaoVisitaDao.getAll();
			return new ArrayList<ProgramacaoVisita>(programacoesVisitas);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"ProgramacaoVisita"}, e);
		}
	}

	@Override
	public List<ProgramacaoVisita> consultarPorResponsavel(final Long idResponsavel) throws PotiErpException {
		try {
			Collection<ProgramacaoVisita> programacoesVisitas = programacaoVisitaDao.consultarPorResponsavel(idResponsavel);
			return new ArrayList<ProgramacaoVisita>(programacoesVisitas);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"ProgramacaoVisita"}, e);
		}
	}

	@Override
	public ProgramacaoVisita consultarPorResponsavelECliente(final Long idResponsavel, final Long idCliente) throws PotiErpException {
		try {
			return programacaoVisitaDao.consultarPorResponsavelECliente(idResponsavel, idCliente);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_ENTIDADE, new Object[]{"ProgramacaoVisita"}, e);
		}
	}
	
	@Override
	public List<ProgramacaoVisita> getBy(final Date dataProgramada) throws PotiErpException {
		try {
			Collection<ProgramacaoVisita> programacoesVisitas = programacaoVisitaDao.getBy(dataProgramada);
			return new ArrayList<ProgramacaoVisita>(programacoesVisitas);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_BUSCAR_LISTA_ENTIDADES, new Object[]{"ProgramacaoVisita"}, e);
		}
	}

	@Override
	public void salvar(final ProgramacaoVisita programacaoVisita) throws PotiErpException {
		try {
			programacaoVisitaDao.salvar(programacaoVisita);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"ProgramacaoVisita"}, e);
		}
	}

	@Override
	public void excluir(final ProgramacaoVisita programacaoVisita) throws PotiErpException {
		try {
			programacaoVisitaDao.excluir(programacaoVisita);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_ENTIDADE, new Object[]{"ProgramacaoVisita"}, e);
		}
	}

	@Override
	public void excluirLista(final List<ProgramacaoVisita> listaProgramacaoVisitas) throws PotiErpException {
		if(listaProgramacaoVisitas==null)
			throw new PotiErpException(MensagensExceptionEnum.ERRO_REMOVER_LISTA.getMsg() + " Lista esta nula");
			
		for(ProgramacaoVisita programacaoVisita : listaProgramacaoVisitas) {
			excluir(programacaoVisita);
		}
	}
	
	@Override
	public void salvar(final DataProgramacaoVisita dataProgramacaoVisita) throws PotiErpException {
		try {
			dataProgramacaoVisitaDao.salvar(dataProgramacaoVisita);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_ALTERAR_ENTIDADE, new Object[]{"DataProgramacaoVisita"}, e);
		}
	}

	@Override
	public byte[] getRelatorioProgramacaoVisita(final List<ProgramacaoVisita> listaProgramacaoVisita, final Responsavel responsavel,
			final int mesSelecionado) throws PotiErpException {
		try {
			List<JasperReportUtilHelper> listJasperReportUtilHelpers = getListJasperReportUtilHelperProgramacaoVisita(listaProgramacaoVisita, responsavel, mesSelecionado);
			return PotiJasperUtil.gerarMultiPdf(listJasperReportUtilHelpers);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_AO_GERAR_RELATORIO, new Object[] { "Programacao de Visitas" }, e);
		}
	}
	
	private List<JasperReportUtilHelper> getListJasperReportUtilHelperProgramacaoVisita(final List<ProgramacaoVisita> listaProgramacaoVisita, final Responsavel responsavel,
			final int mesSelecionado) {
		List<JasperReportUtilHelper> listJasperReportUtilHelpers = new ArrayList<JasperReportUtilHelper>();
		preparaDadosDeProgramcaoVisita(listaProgramacaoVisita, mesSelecionado);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("responsavel", responsavel.getFuncionario().getPessoa().getNome());
		parametros.put("DiasSemana", getListaDiasDoMes(mesSelecionado));
		parametros.put("mes", nomeDoMes(mesSelecionado));
		parametros.put("ano", "2012");
		
		JasperReportUtilHelper jasperReportUtilHelper = new JasperReportUtilHelper();
		jasperReportUtilHelper.setDataSource(listaProgramacaoVisita);
		jasperReportUtilHelper.setOrigem("relatorioProgramacaoVisita.jasper");
		jasperReportUtilHelper.setParametros(parametros);
		listJasperReportUtilHelpers.add(jasperReportUtilHelper);
		return listJasperReportUtilHelpers;
	}
	
	private String nomeDoMes(final int mesSelecionado) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.MONTH, mesSelecionado);
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
		
		return sdf.format(calendar.getTime());
	}
	
	private List<String> getListaDiasDoMes(final int mesSelecionado) {
		List<String> listaDiasComSemana = new ArrayList<String>(30);
		
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.MONTH, mesSelecionado);
		int ano = calendar.get(Calendar.YEAR);
		int ultimoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		
		for(int dia=1; dia<=ultimoDia; dia++) {
			calendar = new GregorianCalendar(ano, mesSelecionado, dia);
			
			listaDiasComSemana.add((new Integer(dia)).toString() + ";" + sdf.format(calendar.getTime()));
		}
		return listaDiasComSemana;
	}
	
	private void preparaDadosDeProgramcaoVisita(final List<ProgramacaoVisita> listaProgramacaoVisita, final int mesSelecionado) {
		for(ProgramacaoVisita programacaoVisita : listaProgramacaoVisita) {
			List<DataProgramacaoVisita> novaListaDataProgramacaoVisita = new ArrayList<DataProgramacaoVisita>();
			
			Calendar calendar = new GregorianCalendar();
			calendar.set(Calendar.MONTH, mesSelecionado);
			int ano = calendar.get(Calendar.YEAR);
			int ultimoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			for(int dia=1; dia<=ultimoDia; dia++) {
				boolean possuiDataProgramada = false;
				calendar = new GregorianCalendar(ano, mesSelecionado, dia);
				
				
				for(DataProgramacaoVisita dataProg : programacaoVisita.getDatasProgramacaoVisitas()) {
					if(dataProg.getDataProgramada().equals(calendar.getTime())) {
						novaListaDataProgramacaoVisita.add(dataProg);
						possuiDataProgramada = true;
						break;
					}
				}
				
				if(!possuiDataProgramada) {
					novaListaDataProgramacaoVisita.add(geraDataProgramacaoVisitaVazio(programacaoVisita, calendar.getTime()));
				}
			}
			programacaoVisita.setDatasProgramacaoVisitas(novaListaDataProgramacaoVisita);
		}
	}
	
	private DataProgramacaoVisita geraDataProgramacaoVisitaVazio(final ProgramacaoVisita programacaoVisita, final Date diaDoMes) {
		DataProgramacaoVisita dataVazia = new DataProgramacaoVisita();
		dataVazia.setProgramacaoVisita(programacaoVisita);
		dataVazia.setDataProgramada(diaDoMes);
		dataVazia.setVisitado(false);
		dataVazia.setIsVisitado(false);
		dataVazia.setIsAguardando(true);
		return dataVazia;
	}
	
	@Override
	public byte[] getRelatorioAcompanhamentoVisita(final List<ProgramacaoVisita> listaProgramacaoVisita, final Responsavel responsavel,
			final int mesSelecionado) throws PotiErpException {
		try {
			List<JasperReportUtilHelper> listJasperReportUtilHelpers = getListJasperReportUtilHelperAcompanhamentoVisita(listaProgramacaoVisita, responsavel, mesSelecionado);
			return PotiJasperUtil.gerarMultiPdf(listJasperReportUtilHelpers);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_AO_GERAR_RELATORIO, new Object[] { "Acompanhamento de Visitas" }, e);
		}
	}
	
	private List<JasperReportUtilHelper> getListJasperReportUtilHelperAcompanhamentoVisita(final List<ProgramacaoVisita> listaProgramacaoVisita, final Responsavel responsavel,
			final int mesSelecionado) {
		List<JasperReportUtilHelper> listJasperReportUtilHelpers = new ArrayList<JasperReportUtilHelper>();
		preparaDadosDeAcompanhamentoVisita(listaProgramacaoVisita, mesSelecionado);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("responsavel", responsavel.getFuncionario().getPessoa().getNome());
		parametros.put("DiasSemana", getListaDiasDoMes(mesSelecionado));
		parametros.put("mes", nomeDoMes(mesSelecionado));
		parametros.put("ano", "2012");
		
		JasperReportUtilHelper jasperReportUtilHelper = new JasperReportUtilHelper();
		jasperReportUtilHelper.setDataSource(listaProgramacaoVisita);
		jasperReportUtilHelper.setOrigem("relatorioAcompanhamentoVisita.jasper");
		jasperReportUtilHelper.setParametros(parametros);
		listJasperReportUtilHelpers.add(jasperReportUtilHelper);
		return listJasperReportUtilHelpers;
	}
	
	private void preparaDadosDeAcompanhamentoVisita(final List<ProgramacaoVisita> listaProgramacaoVisita, final int mesSelecionado) {
		for(ProgramacaoVisita programacaoVisita : listaProgramacaoVisita) {
			List<DataProgramacaoVisita> novaListaDataProgramacaoVisita = new ArrayList<DataProgramacaoVisita>();
			
			Calendar calendar = new GregorianCalendar();
			calendar.set(Calendar.MONTH, mesSelecionado);
			int ano = calendar.get(Calendar.YEAR);
			int ultimoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			for(int dia=1; dia<=ultimoDia; dia++) {
				boolean possuiDataProgramada = false;
				calendar = new GregorianCalendar(ano, mesSelecionado, dia);
				
				
				for(DataProgramacaoVisita dataProg : programacaoVisita.getDatasProgramacaoVisitas()) {
					if(dataProg.getDataProgramada().equals(calendar.getTime())) {
						novaListaDataProgramacaoVisita.add(dataProg);
						possuiDataProgramada = true;
						break;
					}
				}
				
				if(!possuiDataProgramada) {
					novaListaDataProgramacaoVisita.add(geraDataProgramacaoVisitaVazio(programacaoVisita, calendar.getTime()));
				}
			}
			programacaoVisita.setDatasProgramacaoVisitas(novaListaDataProgramacaoVisita);
		}
	}
}
