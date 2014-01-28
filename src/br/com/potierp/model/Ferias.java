package br.com.potierp.model;

import java.util.Date;

import javax.persistence.Entity;
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
			@NamedQuery(name = Ferias.GET_ALL,
						query = " FROM Ferias "),
			@NamedQuery(name = Ferias.GET_POR_FUNCIONARIO,
						query = "SELECT f " +
								"FROM Ferias f " +
								"WHERE f.funcionario.id = :idFuncionario ")
		}
)
@Entity
@Table(name="ferias")
public class Ferias extends BaseEntityPotiErp implements Cloneable {
	
	private static final long serialVersionUID = -6863188353299959738L;

	public static final String GET_ALL = "Ferias.getAll";
	
	public static final String GET_POR_FUNCIONARIO = "Ferias.getPorFuncionario";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario;
	
	private Date periodoInicialAquisitivo;
	
	private Date periodoFinalAquisitivo;
	
	private Boolean feriasColetivas;
	
	private Date periodoInicialGozo;
	
	private Date periodoFinalGozo;
	
	private Date retornoTrabalho;

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

	public Date getPeriodoInicialAquisitivo() {
		return periodoInicialAquisitivo;
	}

	public void setPeriodoInicialAquisitivo(final Date periodoInicialAquisitivo) {
		this.periodoInicialAquisitivo = periodoInicialAquisitivo;
	}

	public Date getPeriodoFinalAquisitivo() {
		return periodoFinalAquisitivo;
	}

	public void setPeriodoFinalAquisitivo(final Date periodoFinalAquisitivo) {
		this.periodoFinalAquisitivo = periodoFinalAquisitivo;
	}

	public Boolean getFeriasColetivas() {
		return feriasColetivas;
	}

	public void setFeriasColetivas(final Boolean feriasColetivas) {
		this.feriasColetivas = feriasColetivas;
	}

	public Date getPeriodoInicialGozo() {
		return periodoInicialGozo;
	}

	public void setPeriodoInicialGozo(final Date periodoInicialGozo) {
		this.periodoInicialGozo = periodoInicialGozo;
	}

	public Date getPeriodoFinalGozo() {
		return periodoFinalGozo;
	}

	public void setPeriodoFinalGozo(final Date periodoFinalGozo) {
		this.periodoFinalGozo = periodoFinalGozo;
	}

	public Date getRetornoTrabalho() {
		return retornoTrabalho;
	}

	public void setRetornoTrabalho(final Date retornoTrabalho) {
		this.retornoTrabalho = retornoTrabalho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((feriasColetivas == null) ? 0 : feriasColetivas.hashCode());
		result = prime
				* result
				+ ((periodoFinalAquisitivo == null) ? 0
						: periodoFinalAquisitivo.hashCode());
		result = prime
				* result
				+ ((periodoFinalGozo == null) ? 0 : periodoFinalGozo.hashCode());
		result = prime
				* result
				+ ((periodoInicialAquisitivo == null) ? 0
						: periodoInicialAquisitivo.hashCode());
		result = prime
				* result
				+ ((periodoInicialGozo == null) ? 0 : periodoInicialGozo
						.hashCode());
		result = prime * result
				+ ((retornoTrabalho == null) ? 0 : retornoTrabalho.hashCode());
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
		Ferias other = (Ferias) obj;
		if (feriasColetivas == null) {
			if (other.feriasColetivas != null)
				return false;
		} else if (!feriasColetivas.equals(other.feriasColetivas))
			return false;
		if (periodoFinalAquisitivo == null) {
			if (other.periodoFinalAquisitivo != null)
				return false;
		} else if (!periodoFinalAquisitivo.equals(other.periodoFinalAquisitivo))
			return false;
		if (periodoFinalGozo == null) {
			if (other.periodoFinalGozo != null)
				return false;
		} else if (!periodoFinalGozo.equals(other.periodoFinalGozo))
			return false;
		if (periodoInicialAquisitivo == null) {
			if (other.periodoInicialAquisitivo != null)
				return false;
		} else if (!periodoInicialAquisitivo
				.equals(other.periodoInicialAquisitivo))
			return false;
		if (periodoInicialGozo == null) {
			if (other.periodoInicialGozo != null)
				return false;
		} else if (!periodoInicialGozo.equals(other.periodoInicialGozo))
			return false;
		if (retornoTrabalho == null) {
			if (other.retornoTrabalho != null)
				return false;
		} else if (!retornoTrabalho.equals(other.retornoTrabalho))
			return false;
		return true;
	}

	@Override
	public Ferias clone() throws CloneNotSupportedException {
		return (Ferias)super.clone();
	}
	
	
}