package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;
import br.com.potierp.util.StringUtils;

@NamedQueries(
		{
		@NamedQuery(name = Cargo.GET_ALL,
				    query = "FROM Cargo "),
	    @NamedQuery(name = Cargo.GET_BY_NOME,
			    query = "FROM Cargo c " +
			    		"WHERE c.cargo = :cargo ")
		}
)

@Entity
@Table(name="cargo")
public class Cargo extends BaseEntityPotiErp implements Comparable<Cargo>, Cloneable{

	private static final long serialVersionUID = -2138437333146882790L;
	
	/**
	 * NQ para buscar todos os cargos.
	 */
	public static final String GET_ALL = "Cargo.getAll";
	
	/**
	 * NQ para buscar cargos de acordo com o nome. 
	 */
	public static final String GET_BY_NOME = "Cargo.getByNome";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long cbo;
	
	private String cargo;
	
	private String descricao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(final String cargo) {
		this.cargo = cargo;
	}
	
	public Long getCbo() {
		if(Long.valueOf(0).equals(cbo)){
			this.cbo = null;
		}
		return cbo;
	}

	public void setCbo(final Long cbo) {
		this.cbo = cbo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
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
		Cargo other = (Cargo) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Cargo [id=" + id + ", cbo=" + cbo + ", cargo=" + cargo
				+ ", descricao=" + descricao + "]";
	}

	@Override
	public int compareTo(final Cargo outro) {
		return StringUtils.compareIgnoreAccentsAndCase(this.cargo, outro.cargo);
	}
	
	@Override
	public Cargo clone() throws CloneNotSupportedException {
		return (Cargo)super.clone();
	}
}