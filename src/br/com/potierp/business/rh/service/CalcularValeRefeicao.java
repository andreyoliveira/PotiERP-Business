package br.com.potierp.business.rh.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.potierp.business.rh.helper.FuncionarioValeRefeicaoHelper;
import br.com.potierp.business.rh.helper.TipoValeRefeicaoHelper;
import br.com.potierp.model.CalculoValeRefeicao;
import br.com.potierp.model.Funcionario;
import br.com.potierp.model.PagamentoValeRefeicao;
import br.com.potierp.model.TipoValeRefeicao;

public class CalcularValeRefeicao {
	
	private CalculoValeRefeicao calculoValeRefeicao;
	
	private BigDecimal valor = BigDecimal.ZERO;
	
	private Integer quantidade = 0;
	
	private List<FuncionarioValeRefeicaoHelper> funcionariosValeRefeicaoHelper = new ArrayList<FuncionarioValeRefeicaoHelper>();
	
	private List<TipoValeRefeicaoHelper> tiposRefeicaoHelper = new ArrayList<TipoValeRefeicaoHelper>();
	
	public CalcularValeRefeicao(final CalculoValeRefeicao calculoValeRefeicao) {
		this.calculoValeRefeicao = calculoValeRefeicao;
	}
	
	public void calcular() throws Exception{
		
		List<PagamentoValeRefeicao> pagamentos 
			= new ArrayList<PagamentoValeRefeicao>(calculoValeRefeicao.getPagamentosValeRefeicao());
		
		if(pagamentos.isEmpty()){
			calculoValeRefeicao.setFuncionariosValeRefeicaoHelper(new ArrayList<FuncionarioValeRefeicaoHelper>());
			return;
		}
		
		Collections.sort(pagamentos);
		PagamentoValeRefeicao pagamentoValeRefeicaoAnterior = null;

		for(PagamentoValeRefeicao pagamentoValeRefeicao : pagamentos){

			if(pagamentoValeRefeicaoAnterior != null){
				Funcionario funcionario = pagamentoValeRefeicao.getFuncionario();
				Funcionario funcionarioAnterior = pagamentoValeRefeicaoAnterior.getFuncionario();
				if(funcionario.getPessoa().compareTo(funcionarioAnterior.getPessoa()) == 0){

					if(pagamentoValeRefeicao.compareTo(pagamentoValeRefeicaoAnterior) != 0){
						addTipoValeRefeicaoHelper(pagamentoValeRefeicaoAnterior);
					}
				}else{
					addTipoValeRefeicaoHelper(pagamentoValeRefeicaoAnterior);
					addFuncionarioValeRefeicaoHelper(pagamentoValeRefeicaoAnterior);
				}
			}
			TipoValeRefeicao tipoValeRefeicao = pagamentoValeRefeicao.getTipoValeRefeicao();
			valor = valor.add(tipoValeRefeicao.getValor());
			quantidade++;
			pagamentoValeRefeicaoAnterior = pagamentoValeRefeicao.clone();
		}
		if(pagamentoValeRefeicaoAnterior != null){
			addTipoValeRefeicaoHelper(pagamentoValeRefeicaoAnterior);
			addFuncionarioValeRefeicaoHelper(pagamentoValeRefeicaoAnterior);
			calculoValeRefeicao.setFuncionariosValeRefeicaoHelper(funcionariosValeRefeicaoHelper);
		}
	}

	private void addFuncionarioValeRefeicaoHelper(final PagamentoValeRefeicao pagamentoValeRefeicaoAnterior) {
		FuncionarioValeRefeicaoHelper funcionarioValeRefeicaoHelper = new FuncionarioValeRefeicaoHelper();
		funcionarioValeRefeicaoHelper.setFuncionario(pagamentoValeRefeicaoAnterior.getFuncionario());
		funcionarioValeRefeicaoHelper.setTiposValeRefeicao(tiposRefeicaoHelper);
		funcionarioValeRefeicaoHelper.somarQuantidades();
		funcionarioValeRefeicaoHelper.somarValores();
		funcionariosValeRefeicaoHelper.add(funcionarioValeRefeicaoHelper);
		tiposRefeicaoHelper = new ArrayList<TipoValeRefeicaoHelper>();
	}

	private void addTipoValeRefeicaoHelper(final PagamentoValeRefeicao pagamentoValeRefeicaoAnterior) {
		TipoValeRefeicao tipoValeRefeicaoAnterior = pagamentoValeRefeicaoAnterior.getTipoValeRefeicao();
		
		TipoValeRefeicaoHelper tipoValeRefeicaoHelper = new TipoValeRefeicaoHelper();
		tipoValeRefeicaoHelper.setTipoValeRefeicao(tipoValeRefeicaoAnterior);
		tipoValeRefeicaoHelper.setValor(valor);
		tipoValeRefeicaoHelper.setQuantidade(quantidade);
		tiposRefeicaoHelper.add(tipoValeRefeicaoHelper);
		valor = BigDecimal.ZERO;
		quantidade = 0;
	}
}