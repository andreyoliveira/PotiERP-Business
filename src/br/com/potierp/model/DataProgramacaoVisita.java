package br.com.potierp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.potierp.infra.bd.BaseEntityPotiErp;
import br.com.potierp.util.DateUtil;

@NamedQueries( 
		{
			@NamedQuery(name = DataProgramacaoVisita.GET_ALL,
						query = "select dataProg from DataProgramacaoVisita dataProg ")						
		}
	)
@Entity
@Table(name="programacaovisitasdatas")
public class DataProgramacaoVisita extends BaseEntityPotiErp implements Cloneable, Comparable<DataProgramacaoVisita> {
	
	private static final long serialVersionUID = -5219908545394618515L;
	
	public static final String GET_ALL = "DataProgramacaoVisita.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="programacaovisitas_id")
	private ProgramacaoVisita programacaoVisita;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_visita")
	private Date dataProgramada;
	
	private Boolean visitado;
	
	private Boolean isVisitado;
	
	private Boolean isAguardando;

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
	
	public ProgramacaoVisita getProgramacaoVisita() {
		return programacaoVisita;
	}

	public void setProgramacaoVisita(final ProgramacaoVisita programacaoVisita) {
		this.programacaoVisita = programacaoVisita;
	}

	public Date getDataProgramada() {
		if(dataProgramada != null)
			return new Date(dataProgramada.getTime());
		return dataProgramada;
	}
	
	public String getDataFormatada() {
		return DateUtil.convertDateParaPtBrComDiaDaSemana(this.dataProgramada);
	}

	public void setDataProgramada(final Date dataProgramada) {
		this.dataProgramada = dataProgramada;
	}

	public Boolean getVisitado() {
		return visitado;
	}

	public void setVisitado(final Boolean visitado) {
		this.visitado = visitado;
	}
	
	/**
	 * @return the isVisitado
	 */
	public Boolean getIsVisitado() {
		return isVisitado;
	}

	/**
	 * @param isVisitado the isVisitado to set
	 */
	public void setIsVisitado(final Boolean isVisitado) {
		this.isVisitado = isVisitado;
	}

	/**
	 * @return the isAguardando
	 */
	public Boolean getIsAguardando() {
		return isAguardando;
	}

	/**
	 * @param isAguardando the isAguardando to set
	 */
	public void setIsAguardando(final Boolean isAguardando) {
		this.isAguardando = isAguardando;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataProgramada == null) ? 0 : dataProgramada.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isAguardando == null) ? 0 : isAguardando.hashCode());
		result = prime * result
				+ ((isVisitado == null) ? 0 : isVisitado.hashCode());
		result = prime * result
				+ ((visitado == null) ? 0 : visitado.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DataProgramacaoVisita)) {
			return false;
		}
		DataProgramacaoVisita other = (DataProgramacaoVisita) obj;
		if (dataProgramada == null) {
			if (other.dataProgramada != null) {
				return false;
			}
		} else if (!dataProgramada.equals(other.dataProgramada)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (isAguardando == null) {
			if (other.isAguardando != null) {
				return false;
			}
		} else if (!isAguardando.equals(other.isAguardando)) {
			return false;
		}
		if (isVisitado == null) {
			if (other.isVisitado != null) {
				return false;
			}
		} else if (!isVisitado.equals(other.isVisitado)) {
			return false;
		}
		if (visitado == null) {
			if (other.visitado != null) {
				return false;
			}
		} else if (!visitado.equals(other.visitado)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(final DataProgramacaoVisita dpv) {
		return this.dataProgramada.compareTo(dpv.dataProgramada);
	}

	@Override
	public DataProgramacaoVisita clone() throws CloneNotSupportedException {
		return (DataProgramacaoVisita)super.clone();
	}
}