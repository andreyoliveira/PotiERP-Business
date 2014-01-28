package br.com.potierp.business.rh.helper;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.potierp.model.TipoValeTransporte;

/**
 * @author Doug
 *
 */
public class AquisicaoValeTransporteHelper implements Serializable {

	private static final long serialVersionUID = -8338339041041225130L;

	private Integer quantidade = 0;
	
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	private TipoValeTransporte tipoValeTransporte;

	/**
	 * @return the quantidade
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Integer quantidade) {
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
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * @return the tipoValeTransporte
	 */
	public TipoValeTransporte getTipoValeTransporte() {
		return tipoValeTransporte;
	}

	/**
	 * @param tipoValeTransporte the tipoValeTransporte to set
	 */
	public void setTipoValeTransporte(TipoValeTransporte tipoValeTransporte) {
		this.tipoValeTransporte = tipoValeTransporte;
	}
}
