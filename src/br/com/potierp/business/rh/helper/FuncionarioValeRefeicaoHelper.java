package br.com.potierp.business.rh.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import br.com.potierp.model.Funcionario;

public class FuncionarioValeRefeicaoHelper implements Serializable {

	private static final long serialVersionUID = 1263102457365113924L;
	
	private Funcionario funcionario;
	
	private BigDecimal valor;
	
	private Integer quantidade;
	
	private List<TipoValeRefeicaoHelper> tiposValeRefeicao;
	
	public void somarQuantidades(){
		quantidade = 0;
		for(TipoValeRefeicaoHelper tipoValeRefeicaoHelper : tiposValeRefeicao){
			quantidade += tipoValeRefeicaoHelper.getQuantidade();
		}
	}
	
	public void somarValores(){
		valor = BigDecimal.ZERO;
		for(TipoValeRefeicaoHelper tipoValeRefeicaoHelper : tiposValeRefeicao){
			valor = valor.add(tipoValeRefeicaoHelper.getValor());
		}
	}

	/**
	 * @return the funcionario
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(final BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return the quantidade
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(final Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return the tiposValeRefeicao
	 */
	public List<TipoValeRefeicaoHelper> getTiposValeRefeicao() {
		return tiposValeRefeicao;
	}

	/**
	 * @param tiposValeRefeicao the tiposValeRefeicao to set
	 */
	public void setTiposValeRefeicao(
			final List<TipoValeRefeicaoHelper> tiposValeRefeicao) {
		this.tiposValeRefeicao = tiposValeRefeicao;
	}
}