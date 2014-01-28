package br.com.potierp.business.rh.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.potierp.business.rh.helper.FuncionarioValeTransporteHelper;
import br.com.potierp.business.rh.helper.TipoValeTransporteHelper;
import br.com.potierp.model.CalculoValeTransporte;
import br.com.potierp.model.Cliente;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.PagamentoValeTransporte;
import br.com.potierp.model.RumoTransporteEnum;
import br.com.potierp.model.TipoValeTransporte;

public class CalcularValeTransporte {
	
	private CalculoValeTransporte calculoValeTransporte;
	
	private BigDecimal valor = BigDecimal.ZERO;
	
	private Integer quantidade = 0;
	
	private List<FuncionarioValeTransporteHelper> funcionariosValeTransporteHelper = new ArrayList<FuncionarioValeTransporteHelper>();
	
	private List<TipoValeTransporteHelper> tiposTransporteHelper = new ArrayList<TipoValeTransporteHelper>();
	
	public CalcularValeTransporte(final CalculoValeTransporte calculoValeTransporte) {
		this.calculoValeTransporte = calculoValeTransporte;
	}
	
	public void calcular() throws Exception{
		
		List<PagamentoValeTransporte> pagamentos 
			= new ArrayList<PagamentoValeTransporte>(calculoValeTransporte.getPagamentosValeTransporte());
		Collections.sort(pagamentos);
		PagamentoValeTransporte pagamentoValeTransporteAnterior = null;

		for(PagamentoValeTransporte pagamentoValeTransporte : pagamentos){

			if(pagamentoValeTransporteAnterior != null){
				Funcionario funcionario = pagamentoValeTransporte.getFuncionario();
				Funcionario funcionarioAnterior = pagamentoValeTransporteAnterior.getFuncionario();
				if(funcionario.getPessoa().compareTo(funcionarioAnterior.getPessoa()) == 0){

					if(pagamentoValeTransporte.compareTo(pagamentoValeTransporteAnterior) != 0){
						addTipoValeTransporteHelper(pagamentoValeTransporteAnterior);
					}
				}else{
					addTipoValeTransporteHelper(pagamentoValeTransporteAnterior);
					addFuncionarioValeTransporteHelper(pagamentoValeTransporteAnterior);
				}
			}
			TipoValeTransporte tipoValeTransporte = pagamentoValeTransporte.getTipoValeTransporte();
			valor = valor.add(tipoValeTransporte.getValor());
			quantidade++;
			pagamentoValeTransporteAnterior = pagamentoValeTransporte.clone();
		}
		if(pagamentoValeTransporteAnterior != null){
			addTipoValeTransporteHelper(pagamentoValeTransporteAnterior);
			addFuncionarioValeTransporteHelper(pagamentoValeTransporteAnterior);
			calculoValeTransporte.setFuncionariosValeTransporteHelper(funcionariosValeTransporteHelper);
		}
	}

	private void addFuncionarioValeTransporteHelper(final PagamentoValeTransporte pagamentoValeTransporteAnterior) {
		FuncionarioValeTransporteHelper funcionarioValeTransporteHelper = new FuncionarioValeTransporteHelper();
		funcionarioValeTransporteHelper.setFuncionario(pagamentoValeTransporteAnterior.getFuncionario());
		funcionarioValeTransporteHelper.setTiposValeTransporte(tiposTransporteHelper);
		funcionarioValeTransporteHelper.somarQuantidades();
		funcionarioValeTransporteHelper.somarValores();
		funcionariosValeTransporteHelper.add(funcionarioValeTransporteHelper);
		tiposTransporteHelper = new ArrayList<TipoValeTransporteHelper>();
	}

	private void addTipoValeTransporteHelper(final PagamentoValeTransporte pagamentoValeTransporteAnterior) {
		TipoValeTransporte tipoValeTransporteAnterior = pagamentoValeTransporteAnterior.getTipoValeTransporte();
		RumoTransporteEnum rumo = pagamentoValeTransporteAnterior.getRumoTransporte();
		Cliente cliente = pagamentoValeTransporteAnterior.getCliente();
		
		TipoValeTransporteHelper tipoValeTransporteHelper = new TipoValeTransporteHelper();
		tipoValeTransporteHelper.setTipoValeTransporte(tipoValeTransporteAnterior);
		tipoValeTransporteHelper.setRumo(rumo);
		tipoValeTransporteHelper.setCliente(cliente);
		tipoValeTransporteHelper.setValor(valor);
		tipoValeTransporteHelper.setQuantidade(quantidade);
		tiposTransporteHelper.add(tipoValeTransporteHelper);
		valor = BigDecimal.ZERO;
		quantidade = 0;
	}
}