package br.com.potierp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = SituacaoFuncionario.GET_ALL,
				    query = "FROM SituacaoFuncionario "),
	    @NamedQuery(name = SituacaoFuncionario.GET_BY_CODIGO,
			    query = "FROM SituacaoFuncionario v " +
			    		"WHERE v.codigo = :codigo ")
		}
)

@Entity
@Table(name="situacaofuncionario")
public class SituacaoFuncionario extends BaseEntityPotiErp implements Cloneable{

	private static final long serialVersionUID = -2138437333146882790L;
	
	/**
	 * NQ para buscar todas as situações do funcionario.
	 */
	public static final String GET_ALL = "SituacaoFuncionario.getAll";
	
	/**
	 * NQ para buscar Situações do Funcionario de acordo com o codigo. 
	 */
	public static final String GET_BY_CODIGO = "SituacaoFuncionario.getByCodigo";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long codigo;
	
	private String nome;
	
	private String descricao;
	
	@Enumerated
	@Column(name="codigoCor")
	private CorEnum corEnum;

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
	
	public CorEnum getCorEnum() {
		return corEnum;
	}

	public void setCorEnum(final CorEnum corEnum) {
		this.corEnum = corEnum;
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
		if (!(obj instanceof SituacaoFuncionario))
			return false;
		SituacaoFuncionario other = (SituacaoFuncionario) obj;
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
		return "SituacaoFuncionario [id=" + id + ", codigo=" + codigo
				+ ", nome=" + nome + ", descricao=" + descricao + ", corEnum="
				+ corEnum + "]";
	}

	@Override
	public SituacaoFuncionario clone() throws CloneNotSupportedException {
		return (SituacaoFuncionario)super.clone();
	}
}