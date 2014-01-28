package br.com.potierp.model;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.NotNull;

import br.com.potierp.infra.bd.BaseEntityPotiErp;
import br.com.potierp.util.StringUtils;

/**
 * @author Renan
 * 22/03/2011
 * 		<p>
 * 		$LastChangedBy: $
 * 		<p>
 * 		$LastChangedDate: $
 */
@Entity
@Table(name="pessoa")
public class Pessoa extends BaseEntityPotiErp implements Comparable<Pessoa>, Cloneable {

	private static final long serialVersionUID = 6785201177762470979L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String nome;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Enumerated
	private SexoEnum sexo;
	
	private String email;
	
	@Embedded
	private Endereco endereco;

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

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(final SexoEnum sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(final Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(final Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pessoa))
			return false;
		Pessoa other = (Pessoa) obj;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sexo != other.sexo)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", dataNascimento="
				+ dataNascimento + ", sexo=" + sexo + ", email=" + email
				+ ", endereco=" + endereco + "]";
	}

	@Override
	public int compareTo(final Pessoa pessoa) {
		return StringUtils.compareIgnoreAccentsAndCase(this.nome, pessoa.nome);
	}
	
	/* (non-Javadoc)
	 * @see br.com.potierp.infra.bd.BaseEntityPotiErp#clone()
	 */
	@Override
	public Pessoa clone() throws CloneNotSupportedException {
		return (Pessoa)super.clone();
	}
}