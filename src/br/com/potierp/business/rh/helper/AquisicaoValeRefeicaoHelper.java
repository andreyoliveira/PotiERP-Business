package br.com.potierp.business.rh.helper;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.potierp.model.TipoValeRefeicao;

/**
 * @author Doug
 *
 */
public class AquisicaoValeRefeicaoHelper implements Serializable {

	private static final long serialVersionUID = -8338339041041225130L;

	private Integer quantidade = 0;
	
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	private TipoValeRefeicao tipoValeRefeicao;

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
	 * @return the valorTotal
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(final BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * @return the tipoValeRefeicao
	 */
	public TipoValeRefeicao getTipoValeRefeicao() {
		return tipoValeRefeicao;
	}

	/**
	 * @param tipoValeRefeicao the tipoValeRefeicao to set
	 */
	public void setTipoValeRefeicao(final TipoValeRefeicao tipoValeRefeicao) {
		this.tipoValeRefeicao = tipoValeRefeicao;
	}
}
