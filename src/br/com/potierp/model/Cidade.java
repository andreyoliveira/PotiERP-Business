package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

/**
 * @author Doug
 */
@NamedQueries(
		{
		@NamedQuery(name=Cidade.GET_ALL,
					query="FROM Cidade c Order By nome "),
		@NamedQuery(name=Cidade.GET_POR_ESTADO,
					query="select cid " +
						  "FROM Cidade cid " +
						  "inner join cid.estado est " +
						  "where est.id = :idEstado "),
		@NamedQuery(name=Cidade.GET_POR_NOME_ESTADO,
				query="select cid FROM Cidade cid " +
					  "inner join cid.estado est " +
					  "where cid.nome = :nome and est.id = :idEstado ")
		}
)
@Entity
@Table(name="cidade")
public class Cidade extends BaseEntityPotiErp implements Cloneable{
	
	private static final long serialVersionUID = 7210586699378043422L;
	
	public static final String GET_ALL = "Cidade.getAll";
	
	public static final String GET_POR_ESTADO = "Cidade.getPorEstado";
	
	public static final String GET_POR_NOME_ESTADO = "Cidade.getPorNomeIdEstado";

	@Id
	@GeneratedValue
	private Long id;
	
	private String codigo;
	
	@NotNull
	private String nome;
	
	private String sigla;
	
	@ManyToOne
	@JoinColumn(name="idEstado")
	private Estado estado;

	@Override
	public Long getId() {
		if(Long.valueOf(0).equals(id)){
			this.id = null;
		}
		return id;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(final String nome) {
		this.nome = nome;
	}

	/**
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla the sigla to set
	 */
	public void setSigla(final String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(final Estado estado) {
		this.estado = estado;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Cidade))
			return false;
		Cidade other = (Cidade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Cidade [id=" + id + ", codigo=" + codigo + ", sigla=" + sigla
				+ ", nome=" + nome + ", estado=" + estado + "]";
	}

	@Override
	public Cidade clone() throws CloneNotSupportedException {
		return (Cidade) super.clone();
	}
}
