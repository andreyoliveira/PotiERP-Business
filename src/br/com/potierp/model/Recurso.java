package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = Recurso.GET_ALL,
					query = "FROM Recurso "),
		@NamedQuery(name = Recurso.GET_BY_DESCRICAO,
					query = "FROM Recurso r " +
							"WHERE r.descricao = :descricao "),
		@NamedQuery(name = Recurso.GET_BY_CODIGO,
					query = "FROM Recurso r " +
							"WHERE r.codigo = :codigo ")
		}
)
@Entity
@Table(name="recurso")
public class Recurso extends BaseEntityPotiErp implements Cloneable {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -636094742429405313L;
	
	/**
	 * NQ para buscar todos os recursos.
	 */
	public static final String GET_ALL = "Recurso.getAll";
	
	/**
	 * NQ para buscar os recursos por nome.
	 */
	public static final String GET_BY_DESCRICAO = "Recurso.getByDescricao";
	
	/**
	 * NQ para buscar os recursos por c�digo.
	 */
	public static final String GET_BY_CODIGO = "Recurso.getByCodigo";

	@Id
	@GeneratedValue
	private Long id;
	
	private String codigo;
	
	private String descricao;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recurso other = (Recurso) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Setor [id=" + id + ", codigo=" + codigo + ", descricao="
				+ descricao + "]";
	}

	@Override
	public Recurso clone() throws CloneNotSupportedException {
		return (Recurso)super.clone();
	}

}
