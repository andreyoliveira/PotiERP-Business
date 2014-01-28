package br.com.potierp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

/**
 * @author Andrey Oliveira
 */
@NamedQueries(
		{
		@NamedQuery(name=HistoricoDemissao.GET_ALL,
					query=" FROM HistoricoDemissao "),
		@NamedQuery(name=HistoricoDemissao.GET_POR_FUNCIONARIO,
					query = "SELECT h " +
							"FROM HistoricoDemissao h " +
							"WHERE h.funcionario.id = :idFuncionario ")
		}
)
@Entity
@Table(name="historicodemissao")
public class HistoricoDemissao extends BaseEntityPotiErp implements Cloneable {
	
	private static final long serialVersionUID = -2358039132403375101L;

	public static final String GET_ALL = "HistoricoDemissao.getAll";
	
	public static final String GET_POR_FUNCIONARIO = "HistoricoDemissao.getPorFuncionario";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario;
	
	private Date dataDemissao;
	
	@Enumerated(EnumType.STRING)
	private MotivoDemissaoEnum motivo;
	
	@Enumerated(EnumType.STRING)
	private TipoAvisoPrevioEnum tipoAvisoPrevio;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(final Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public MotivoDemissaoEnum getMotivo() {
		return motivo;
	}

	public void setMotivo(final MotivoDemissaoEnum motivo) {
		this.motivo = motivo;
	}

	public TipoAvisoPrevioEnum getTipoAvisoPrevio() {
		return tipoAvisoPrevio;
	}

	public void setTipoAvisoPrevio(final TipoAvisoPrevioEnum tipoAvisoPrevio) {
		this.tipoAvisoPrevio = tipoAvisoPrevio;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataDemissao == null) ? 0 : dataDemissao.hashCode());
		result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
		result = prime * result
				+ ((tipoAvisoPrevio == null) ? 0 : tipoAvisoPrevio.hashCode());
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
		HistoricoDemissao other = (HistoricoDemissao) obj;
		if (dataDemissao == null) {
			if (other.dataDemissao != null)
				return false;
		} else if (!dataDemissao.equals(other.dataDemissao))
			return false;
		if (motivo != other.motivo)
			return false;
		if (tipoAvisoPrevio != other.tipoAvisoPrevio)
			return false;
		return true;
	}

	@Override
	public HistoricoDemissao clone() throws CloneNotSupportedException {
		return (HistoricoDemissao)super.clone();
	}
}