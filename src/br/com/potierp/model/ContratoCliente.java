package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name=ContratoCliente.GET_ALL,
					query="FROM ContratoCliente cc ")
		}
)
@Entity
@Table(name="contratoCliente")
public class ContratoCliente extends BaseEntityPotiErp implements Cloneable{
	
	private static final long serialVersionUID = 7210586699378043422L;
	
	public static final String GET_ALL = "ContratoCliente.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="idTipoServico")
	private TipoServico tipoServico;
	
	@ManyToOne
	@JoinColumn(name="idPeriodicidade")
	private Periodicidade periodicidade;
	
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
	 * @return the tipoServico
	 */
	public TipoServico getTipoServico() {
		return tipoServico;
	}

	/**
	 * @param tipoServico the tipoServico to set
	 */
	public void setTipoServico(final TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	/**
	 * @return the periodicidade
	 */
	public Periodicidade getPeriodicidade() {
		return periodicidade;
	}

	/**
	 * @param periodicidade the periodicidade to set
	 */
	public void setPeriodicidade(final Periodicidade periodicidade) {
		this.periodicidade = periodicidade;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof ContratoCliente)) {
			return false;
		}
		ContratoCliente other = (ContratoCliente) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public ContratoCliente clone() throws CloneNotSupportedException {
		return (ContratoCliente) super.clone();
	}
}
