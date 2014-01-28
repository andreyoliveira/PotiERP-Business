package br.com.potierp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = ValeTransporte.GET_ALL,
				    query = "FROM ValeTransporte vt " +
				    		"order by vt.funcionario.codigoRegistro, rumoTransporte, situacao ")
		}
)

@Entity
@Table(name="valetransporte")
public class ValeTransporte extends BaseEntityPotiErp implements Cloneable{

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -8344283211879271201L;

	/**
	 * NQ para buscar todos os vales transportes.
	 */
	public static final String GET_ALL = "ValeTransporte.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtribuicao;
	
	@ManyToOne
	@JoinColumn(name="idTipoValeTransporte")
	private TipoValeTransporte tipoValeTransporte;
	
	@ManyToOne
	@JoinColumn(name="idFuncionario")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	@Enumerated(EnumType.STRING)
	private RumoTransporteEnum rumoTransporte;
	
	private String observacao;
	
	@Enumerated(EnumType.STRING)
	private SituacaoEnum situacao;
	
	@Transient
	private Long identificador;

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
	
	public Date getDataAtribuicao() {
		return dataAtribuicao;
	}

	public void setDataAtribuicao(final Date dataAtribuicao) {
		this.dataAtribuicao = dataAtribuicao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(final String observacao) {
		this.observacao = observacao;
	}

	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(final SituacaoEnum situacao) {
		this.situacao = situacao;
	}

	public TipoValeTransporte getTipoValeTransporte() {
		return tipoValeTransporte;
	}

	public void setTipoValeTransporte(final TipoValeTransporte tipoValeTransporte) {
		this.tipoValeTransporte = tipoValeTransporte;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public RumoTransporteEnum getRumoTransporte() {
		return rumoTransporte;
	}

	public void setRumoTransporte(final RumoTransporteEnum rumoTransporte) {
		this.rumoTransporte = rumoTransporte;
	}
	
	public Long getIdentificador() {
		if(Long.valueOf(0).equals(identificador)){
			this.identificador = null;
		}
		return identificador;
	}

	public void setIdentificador(final Long identificador) {
		this.identificador = identificador;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataAtribuicao == null) ? 0 : dataAtribuicao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((identificador == null) ? 0 : identificador.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((rumoTransporte == null) ? 0 : rumoTransporte.hashCode());
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ValeTransporte)) {
			return false;
		}
		ValeTransporte other = (ValeTransporte) obj;
		if (dataAtribuicao == null) {
			if (other.dataAtribuicao != null) {
				return false;
			}
		} else if (!dataAtribuicao.equals(other.dataAtribuicao)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (identificador == null) {
			if (other.identificador != null) {
				return false;
			}
		} else if (!identificador.equals(other.identificador)) {
			return false;
		}
		if (observacao == null) {
			if (other.observacao != null) {
				return false;
			}
		} else if (!observacao.equals(other.observacao)) {
			return false;
		}
		if (rumoTransporte != other.rumoTransporte) {
			return false;
		}
		if (situacao != other.situacao) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "ValeTransporte [id=" + id + ", dataAtribuicao="
				+ dataAtribuicao + ", rumoTransporte=" + rumoTransporte
				+ ", observacao=" + observacao + ", situacao=" + situacao
				+ ", identificador=" + identificador + "]";
	}

	@Override
	public ValeTransporte clone() throws CloneNotSupportedException {
		return (ValeTransporte)super.clone();
	}
}