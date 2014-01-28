package br.com.potierp.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;


@Entity
@Table(name="historicocomercial")
@NamedQueries({
	@NamedQuery(name=HistoricoComercial.GET_ALL,
			query = "FROM HistoricoComercial HC"),
	@NamedQuery(name=HistoricoComercial.GET_BY_CLIENTE,
			query = "FROM HistoricoComercial hc where hc.cliente.id = :idCliente")
})
/**
 * Representa o Histórico Comercial.
 * @author felipe
 *
 */
public class HistoricoComercial extends BaseEntityPotiErp implements Cloneable{
	
	private static final long serialVersionUID = 1L;
	
	public static final String GET_ALL = "HistoricoComercial.getAll"; 
	
	public static final String GET_BY_CLIENTE = "HistoricoComercial.getByCliente";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	private Long ano;
	
	private Long mes;
	
	private BigDecimal valorNFe;
	
	private BigDecimal retencao;
	
	private BigDecimal retencaoISS;
	
	private BigDecimal abatimentos;
	
	private BigDecimal totalDeducoes;
	
	private BigDecimal totalLiquidoNFe;
	
	@Override
	public Long getId() {
		if(Long.valueOf(0).equals(id)){
			this.id = null;
		}
		return id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}
	
	public Cliente getCliente(){
		return this.cliente;
	}
	
	public void setCliente(final Cliente cliente){
		this.cliente = cliente;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(final Long ano) {
		this.ano = ano;
	}

	public Long getMes() {
		return mes;
	}

	public void setMes(final Long mes) {
		this.mes = mes;
	}

	public BigDecimal getValorNFe() {
		return valorNFe;
	}

	public void setValorNFe(final BigDecimal valorNFe) {
		this.valorNFe = valorNFe;
	}

	public BigDecimal getRetencao() {
		return retencao;
	}

	public void setRetencao(final BigDecimal retencao) {
		this.retencao = retencao;
	}

	public BigDecimal getRetencaoISS() {
		return retencaoISS;
	}

	public void setRetencaoISS(final BigDecimal retencaoISS) {
		this.retencaoISS = retencaoISS;
	}

	public BigDecimal getAbatimentos() {
		return abatimentos;
	}

	public void setAbatimentos(final BigDecimal abatimentos) {
		this.abatimentos = abatimentos;
	}

	public BigDecimal getTotalDeducoes() {
		return totalDeducoes;
	}

	public void setTotalDeducoes(final BigDecimal totalDeducoes) {
		this.totalDeducoes = totalDeducoes;
	}

	public BigDecimal getTotalLiquidoNFe() {
		return totalLiquidoNFe;
	}

	public void setTotalLiquidoNFe(final BigDecimal totalLiquidoNFe) {
		this.totalLiquidoNFe = totalLiquidoNFe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((abatimentos == null) ? 0 : abatimentos.hashCode());
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result
				+ ((retencao == null) ? 0 : retencao.hashCode());
		result = prime * result
				+ ((retencaoISS == null) ? 0 : retencaoISS.hashCode());
		result = prime * result
				+ ((totalDeducoes == null) ? 0 : totalDeducoes.hashCode());
		result = prime * result
				+ ((totalLiquidoNFe == null) ? 0 : totalLiquidoNFe.hashCode());
		result = prime * result
				+ ((valorNFe == null) ? 0 : valorNFe.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HistoricoComercial))
			return false;
		HistoricoComercial other = (HistoricoComercial) obj;
		if (abatimentos == null) {
			if (other.abatimentos != null)
				return false;
		} else if (!abatimentos.equals(other.abatimentos))
			return false;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		if (retencao == null) {
			if (other.retencao != null)
				return false;
		} else if (!retencao.equals(other.retencao))
			return false;
		if (retencaoISS == null) {
			if (other.retencaoISS != null)
				return false;
		} else if (!retencaoISS.equals(other.retencaoISS))
			return false;
		if (totalDeducoes == null) {
			if (other.totalDeducoes != null)
				return false;
		} else if (!totalDeducoes.equals(other.totalDeducoes))
			return false;
		if (totalLiquidoNFe == null) {
			if (other.totalLiquidoNFe != null)
				return false;
		} else if (!totalLiquidoNFe.equals(other.totalLiquidoNFe))
			return false;
		if (valorNFe == null) {
			if (other.valorNFe != null)
				return false;
		} else if (!valorNFe.equals(other.valorNFe))
			return false;
		return true;
	}

		
	@Override
	public String toString() {
		return "HistoricoComercial [id=" + id
				+ ", ano=" + ano + ", mes=" + mes + ", valorNFe=" + valorNFe
				+ ", retencao=" + retencao + ", retencaoISS=" + retencaoISS
				+ ", abatimentos=" + abatimentos + ", totalDeducoes="
				+ totalDeducoes + ", totalLiquidoNFe=" + totalLiquidoNFe + "]";
	}

	@Override
	public HistoricoComercial clone() throws CloneNotSupportedException {
		return (HistoricoComercial) super.clone();
	}
	
}