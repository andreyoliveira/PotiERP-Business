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
		@NamedQuery(name = VinculoEmpregaticio.GET_ALL,
				    query = "FROM VinculoEmpregaticio "),
	    @NamedQuery(name = VinculoEmpregaticio.GET_BY_CODIGO,
			    query = "FROM VinculoEmpregaticio v " +
			    		"WHERE v.codigo = :codigo ")
		}
)

@Entity
@Table(name="vinculoempregaticio")
public class VinculoEmpregaticio extends BaseEntityPotiErp implements Cloneable{

	private static final long serialVersionUID = -2138437333146882790L;
	
	/**
	 * NQ para buscar todos os vinculos empregaticios.
	 */
	public static final String GET_ALL = "VinculoEmpregaticio.getAll";
	
	/**
	 * NQ para buscar Vinculos Empregaticios de acordo com o codigo. 
	 */
	public static final String GET_BY_CODIGO = "VinculoEmpregaticio.getByCodigo";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long codigo;
	
	private String nome;
	
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

	public Long getCodigo() {
		if(Long.valueOf(0).equals(codigo)){
			this.codigo = null;
		}
		return codigo;
	}

	public void setCodigo(final Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
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
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof VinculoEmpregaticio))
			return false;
		VinculoEmpregaticio other = (VinculoEmpregaticio) obj;
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "VinculoEmpregaticio [id=" + id + ", codigo=" + codigo
				+ ", nome=" + nome + ", descricao=" + descricao + "]";
	}

	@Override
	public VinculoEmpregaticio clone() throws CloneNotSupportedException {
		return (VinculoEmpregaticio)super.clone();
	}
}