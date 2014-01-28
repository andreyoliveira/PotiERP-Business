package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@Entity
@Table(name="pais")
@NamedQueries({
	@NamedQuery(name = Pais.GET_ALL,
			query = "FROM Pais"),
	@NamedQuery(name = Pais.GET_POR_SIGLA,
			query = "FROM Pais where sigla = :sigla")
})
public class Pais extends BaseEntityPotiErp implements Cloneable, Comparable<Pais>{
	
	private static final long serialVersionUID = 1L;
	
	public static final String GET_ALL = "Pais.getAll";
	
	public static final String GET_POR_SIGLA = "Pais.getPorSigla";
	
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Length(min=2, max=4)
	private String codigo;
	
	@NotNull
	@Length(min=1, max=45)
	private String nome;
	
	@NotNull
	@Length(min=2, max=5)
	private String sigla;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(final String sigla) {
		this.sigla = sigla;
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
		if (!(obj instanceof Pais))
			return false;
		Pais other = (Pais) obj;
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
		return "Pais [id=" + id + ", codigo=" + codigo + ", nome=" + nome
				+ ", sigla=" + sigla + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public int compareTo(final Pais o) {
		return this.getNome().compareTo(o.getNome());
	}
	
}