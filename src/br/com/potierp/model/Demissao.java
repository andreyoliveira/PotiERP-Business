package br.com.potierp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = Demissao.GET_ALL,
				    query = "FROM Demissao ")
		}
)

@Entity
@Table(name="demissao")
public class Demissao extends BaseEntityPotiErp{

	private static final long serialVersionUID = 817627603638803820L;
	
	/**
	 * NQ para buscar todas as Demissões.
	 */
	public static final String GET_ALL = "Demissao.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String motivo;
	
	private Date dataDemissao;
	
	@ManyToOne
	@JoinColumn( name = "idTipoDemissao")
	private TipoDemissao tipoDemissao;
	
	@OneToOne
	@JoinColumn( name = "idFuncionario")
	private Funcionario funcionario;

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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(final String motivo) {
		this.motivo = motivo;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(final Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public TipoDemissao getTipoDemissao() {
		return tipoDemissao;
	}

	public void setTipoDemissao(final TipoDemissao tipoDemissao) {
		this.tipoDemissao = tipoDemissao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}