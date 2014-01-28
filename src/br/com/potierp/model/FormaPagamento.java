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
		@NamedQuery(name = FormaPagamento.GET_ALL,
				    query = "FROM FormaPagamento "),
	    @NamedQuery(name = FormaPagamento.GET_BY_NOME,
			    query = "FROM FormaPagamento fp " +
			    		"WHERE fp.nome = :formaPagamento ")
		}
)

@Entity
@Table(name="formapagamento")
public class FormaPagamento extends BaseEntityPotiErp implements Cloneable{

	private static final long serialVersionUID = 2189563058987509652L;
	
	/**
	 * NQ para buscar todos as formas de pagamentos
	 */
	public static final String GET_ALL = "FormaPagamento.getAll";
	
	/**
	 * NQ para buscar formas de pagamento de acordo com o nome. 
	 */
	public static final String GET_BY_NOME = "FormaPagamento.getByNome";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
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
		if (!(obj instanceof FormaPagamento))
			return false;
		FormaPagamento other = (FormaPagamento) obj;
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
		return "FormaPagamento [id=" + id + ", nome=" + nome + ", descricao="
				+ descricao + "]";
	}

	@Override
	public FormaPagamento clone() throws CloneNotSupportedException {
		return (FormaPagamento)super.clone();
	}
}