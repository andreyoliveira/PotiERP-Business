package br.com.potierp.relatorio.rh.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.potierp.business.rh.helper.AquisicaoValeRefeicaoHelper;
import br.com.potierp.business.rh.service.CalcularValeRefeicao;
import br.com.potierp.infra.exception.PotiErpException;
import br.com.potierp.infra.interceptor.DAOInterceptor;
import br.com.potierp.infra.msg.MensagensExceptionEnum;
import br.com.potierp.model.CalculoValeRefeicao;
import br.com.potierp.util.report.JasperReportUtilHelper;
import br.com.potierp.util.report.PotiJasperUtil;

/**
 * @author Doug
 *
 */
@Stateless(mappedName = "MapaValeRefeicaoService", name="MapaValeRefeicaoService")
@Local(MapaValeRefeicaoService.class)
@Interceptors(DAOInterceptor.class)
public class MapaValeRefeicaoServiceBean implements MapaValeRefeicaoService {

	/* (non-Javadoc)
	 * @see br.com.potierp.relatorio.rh.service.MapaValeTransporteService#getRelatorio(java.util.List)
	 */
	@Override
	public byte[] getRelatorio(
			List<CalculoValeRefeicao> listCalculoValeRefeicao)
			throws PotiErpException {
		try {
			List<JasperReportUtilHelper> listJasperReportUtilHelpers = getListJasperReportUtilHelper(listCalculoValeRefeicao);
			return PotiJasperUtil.gerarMultiPdfComDoisRelatorios(listJasperReportUtilHelpers);
		} catch (Exception e) {
			throw new PotiErpException(MensagensExceptionEnum.ERRO_AO_GERAR_RELATORIO, new Object[]{"Mapa de vale transporte"}, e);
		}
	}
	
	private List<JasperReportUtilHelper> getListJasperReportUtilHelper(
			final List<CalculoValeRefeicao> listCalculoValeRefeicao) throws Exception{
		
		List<JasperReportUtilHelper> listJasperReportUtilHelpers = new ArrayList<JasperReportUtilHelper>();
		for(CalculoValeRefeicao calculo : listCalculoValeRefeicao){
			CalcularValeRefeicao calcularValeRefeicao = new CalcularValeRefeicao(calculo);
			calcularValeRefeicao.calcular();
			
			List<AquisicaoValeRefeicaoHelper> aquisicoes = calculo.getAquisicoesValeRefeicaoHelper();
			BigDecimal valorTotalAquisicao = BigDecimal.ZERO;
			Integer quantidadeTotalAquisicao = 0;
			for (AquisicaoValeRefeicaoHelper aquisicao : aquisicoes) {
				valorTotalAquisicao = valorTotalAquisicao.add(aquisicao.getValorTotal());
				quantidadeTotalAquisicao = quantidadeTotalAquisicao + aquisicao.getQuantidade();
			}
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("dataInicio", calculo.getDataInicio());
			parametros.put("dataFim", calculo.getDataFim());
			parametros.put("totalGeral", calculo.getValorTotal());
			parametros.put("quantidadeGeral", calculo.getQuantidadeTotal());
			parametros.put("aquisicoes", aquisicoes);
			parametros.put("valorTotalAquisicao", valorTotalAquisicao);
			parametros.put("quantidadeTotalAquisicao", quantidadeTotalAquisicao);
			parametros.put("quantidadeFuncionarios", calculo.getFuncionariosValeRefeicaoHelper().size());
			
			JasperReportUtilHelper jasperReportUtilHelper = new JasperReportUtilHelper();
			jasperReportUtilHelper.setDataSource(calculo.getFuncionariosValeRefeicaoHelper());
			jasperReportUtilHelper.setOrigem("MapaValeRefeicao.jasper");
			jasperReportUtilHelper.setParametros(parametros);
			listJasperReportUtilHelpers.add(jasperReportUtilHelper);
			
			jasperReportUtilHelper = new JasperReportUtilHelper();
			jasperReportUtilHelper.setDataSource(calculo.getFuncionariosValeRefeicaoHelper());
			jasperReportUtilHelper.setOrigem("MapaAquisicaoValeRefeicao.jasper");
			jasperReportUtilHelper.setParametros(parametros);
			listJasperReportUtilHelpers.add(jasperReportUtilHelper);
		}
		return listJasperReportUtilHelpers;
	}
}
