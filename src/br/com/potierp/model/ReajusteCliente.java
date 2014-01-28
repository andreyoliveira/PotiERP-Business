package br.com.potierp.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name=ReajusteCliente.GET_ALL,
					query="FROM ReajusteCliente rc ")
		}
)
@Entity
@Table(name="reajusteCliente")
public class ReajusteCliente extends BaseEntityPotiErp implements Cloneable{
	
	private static final long serialVersionUID = 7210586699378043422L;
	
	public static final String GET_ALL = "ReajusteCliente.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	private BigDecimal valor;
	
	private BigDecimal valorReajustado;
	
	@Temporal(TemporalType.DATE)
	private Date dataReajuste;
	
	@Override
	public Long getId() {
		if(Long.valueOf(0).equals(id)){
			this.id = null;
		}
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}
	
	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
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
	 * @return the valorReajustado
	 */
	public BigDecimal getValorReajustado() {
		return valorReajustado;
	}

	/**
	 * @param valorReajustado the valorReajustado to set
	 */
	public void setValorReajustado(final BigDecimal valorReajustado) {
		this.valorReajustado = valorReajustado;
	}

	/**
	 * @return the dataReajuste
	 */
	public Date getDataReajuste() {
		return dataReajuste;
	}

	/**
	 * @param dataReajuste the dataReajuste to set
	 */
	public void setDataReajuste(final Date dataReajuste) {
		this.dataReajuste = dataReajuste;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataReajuste == null) ? 0 : dataReajuste.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result + ((valorReajustado == null) ? 0 : valorReajustado.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ReajusteCliente)) {
			return false;
		}
		ReajusteCliente other = (ReajusteCliente) obj;
		if (dataReajuste == null) {
			if (other.dataReajuste != null) {
				return false;
			}
		} else if (!dataReajuste.equals(other.dataReajuste)) {
			return false;
		}
		if (valor == null) {
			if (other.valor != null) {
				return false;
			}
		} else if (!valor.equals(other.valor)) {
			return false;
		}
		if (valorReajustado == null) {
			if (other.valorReajustado != null) {
				return false;
			}
		} else if (!valorReajustado.equals(other.valorReajustado)) {
			return false;
		}
		return true;
	}

	@Override
	public ReajusteCliente clone() throws CloneNotSupportedException {
		return (ReajusteCliente) super.clone();
	}
}