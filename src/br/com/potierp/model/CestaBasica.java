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

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = CestaBasica.GET_ALL,
				    query = "FROM CestaBasica cb " +
							"order by cb.funcionario.codigoRegistro ")
		}
)

@Entity
@Table(name="cestabasica")
public class CestaBasica extends BaseEntityPotiErp implements Cloneable{

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = -1074958735969078256L;

	/**
	 * NQ para buscar todos as cestas básicas.
	 */
	public static final String GET_ALL = "CestaBasica.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtribuicao;
	
	@ManyToOne
	@JoinColumn(name="idTipoCestaBasica")
	private TipoCestaBasica tipoCestaBasica;
	
	@ManyToOne
	@JoinColumn(name="idFuncionario")
	private Funcionario funcionario;
	
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

	public TipoCestaBasica getTipoCestaBasica() {
		return tipoCestaBasica;
	}

	public void setTipoCestaBasica(final TipoCestaBasica tipoCestaBasica) {
		this.tipoCestaBasica = tipoCestaBasica;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
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
		if (!(obj instanceof CestaBasica))
			return false;
		CestaBasica other = (CestaBasica) obj;
		if (dataAtribuicao == null) {
			if (other.dataAtribuicao != null)
				return false;
		} else if (!dataAtribuicao.equals(other.dataAtribuicao))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "CestaBasica [id=" + id + ", dataAtribuicao=" + dataAtribuicao
				+ ", tipoCestaBasica=" + tipoCestaBasica + "]";
	}

	@Override
	public CestaBasica clone() throws CloneNotSupportedException {
		return (CestaBasica)super.clone();
	}
}