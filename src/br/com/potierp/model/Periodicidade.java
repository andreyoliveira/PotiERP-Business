package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name=Periodicidade.GET_ALL,
					query="FROM Periodicidade p ")
		}
)
@Entity
@Table(name="periodicidade")
public class Periodicidade extends BaseEntityPotiErp implements Cloneable{
	
	private static final long serialVersionUID = 7210586699378043422L;
	
	public static final String GET_ALL = "Periodicidade.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String nome;
	
	@Override
	public Long getId() {
		if(Long.valueOf(0).equals(id)){
			this.id = null;
		}
		return id;
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
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		if (!(obj instanceof Periodicidade)) {
			return false;
		}
		Periodicidade other = (Periodicidade) obj;
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

	@Override
	public Periodicidade clone() throws CloneNotSupportedException {
		return (Periodicidade) super.clone();
	}
}
