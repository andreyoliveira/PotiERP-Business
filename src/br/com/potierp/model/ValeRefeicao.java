package br.com.potierp.model;

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
import javax.persistence.Transient;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = ValeRefeicao.GET_ALL,
				    query = "FROM ValeRefeicao vr " +
							"order by vr.funcionario.codigoRegistro ")
		}
)

@Entity
@Table(name="valerefeicao")
public class ValeRefeicao extends BaseEntityPotiErp implements Cloneable{

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = -1074958735969078256L;

	/**
	 * NQ para buscar todos os vales Refeições.
	 */
	public static final String GET_ALL = "ValeRefeicao.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtribuicao;
	
	@ManyToOne
	@JoinColumn(name="idTipoValeRefeicao")
	private TipoValeRefeicao tipoValeRefeicao;
	
	@ManyToOne
	@JoinColumn(name="idFuncionario")
	private Funcionario funcionario;
	
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

	public TipoValeRefeicao getTipoValeRefeicao() {
		return tipoValeRefeicao;
	}

	public void setTipoValeRefeicao(final TipoValeRefeicao tipoValeRefeicao) {
		this.tipoValeRefeicao = tipoValeRefeicao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataAtribuicao == null) ? 0 : dataAtribuicao.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ValeRefeicao))
			return false;
		ValeRefeicao other = (ValeRefeicao) obj;
		if (dataAtribuicao == null) {
			if (other.dataAtribuicao != null)
				return false;
		} else if (!dataAtribuicao.equals(other.dataAtribuicao))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ValeRefeicao [id=" + id + ", dataAtribuicao=" + dataAtribuicao
				+ ", tipoValeRefeicao=" + tipoValeRefeicao + ", identificador="
				+ identificador + "]";
	}

	@Override
	public ValeRefeicao clone() throws CloneNotSupportedException {
		return (ValeRefeicao)super.clone();
	}
}