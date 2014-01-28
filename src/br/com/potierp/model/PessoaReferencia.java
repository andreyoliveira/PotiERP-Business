package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@Entity
@Table(name="pessoareferencia")
public class PessoaReferencia extends BaseEntityPotiErp{

	private static final long serialVersionUID = -3575583800191112080L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idGrauParentesco")
	private GrauParentesco grauParentesco;
	
	@OneToOne
	@JoinColumn(name = "idPessoa")
	private Pessoa pessoa;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(final Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public GrauParentesco getGrauParentesco() {
		return grauParentesco;
	}

	public void setGrauParentesco(final GrauParentesco grauParentesco) {
		this.grauParentesco = grauParentesco;
	}
}