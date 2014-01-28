package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.Length;

import br.com.potierp.core.Identifiable;
import br.com.potierp.infra.bd.BaseEntityPotiErp;

@Entity
@NamedQueries({
	@NamedQuery(name=Funcionalidade.GET_ALL, query="FROM Funcionalidade")
})
@Table(name="funcionalidade")
public class Funcionalidade extends BaseEntityPotiErp implements Comparable<Funcionalidade>, Identifiable{
	
	private static final long serialVersionUID = 1L;

	public static final String GET_ALL = "Funcionalidade.GET_ALL";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long codigo;
	
	@Length(max = 100)
	private String descricao;
	
	@Override
	public Long getId() {
		if (Long.valueOf(0).equals(id)) {
			this.id = null;
		}
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getCodigo() {
		if (Long.valueOf(0).equals(codigo)) {
			this.codigo = null;
		}
		return codigo;
	}

	public void setCodigo(final Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public int compareTo(final Funcionalidade o) {
		return this.codigo.compareTo(o.getCodigo());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Funcionalidade other = (Funcionalidade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}