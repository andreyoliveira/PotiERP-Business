package br.com.potierp.model;

import java.math.BigDecimal;

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
		@NamedQuery(name = Beneficio.GET_ALL,
				    query = "FROM Beneficio "),
	    @NamedQuery(name = Beneficio.GET_BY_NOME,
			    query = "FROM Beneficio b " +
			    		"WHERE b.nome = :nome ")
		}
)

@Entity
@Table(name="beneficio")
public class Beneficio extends BaseEntityPotiErp implements Comparable<Beneficio>, Cloneable{

	private static final long serialVersionUID = 1301228059425605809L;
	
	/**
	 * NQ para buscar todos os Beneficio.
	 */
	public static final String GET_ALL = "Beneficio.getAll";
	
	/**
	 * NQ para buscar Beneficios de acordo com o nome. 
	 */
	public static final String GET_BY_NOME = "Beneficio.getByNome";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private BigDecimal valor;
	
	private String descricao;

	@Override
	public Long getId() {
		if(Long.valueOf(0).equals(id)){
			this.id = null;
		}
		return this.id;
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
	
	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(final BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Beneficio))
			return false;
		Beneficio other = (Beneficio) obj;
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
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public int compareTo(final Beneficio outro) {
		return StringUtils.compareIgnoreAccentsAndCase(this.nome, outro.nome);
	}
	
	@Override
	public Beneficio clone() throws CloneNotSupportedException {
		return (Beneficio)super.clone();
	}
}