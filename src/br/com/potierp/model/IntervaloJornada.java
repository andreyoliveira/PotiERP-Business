package br.com.potierp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@Entity
@Table(name="intervalojornada")
public class IntervaloJornada extends BaseEntityPotiErp implements Cloneable {

	private static final long serialVersionUID = 6978091099799501867L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long tempo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipointervalo")
	private TipoRefeicaoEnum tipoRefeicao;
	
	@ManyToOne
	@JoinColumn(name = "idJornadaTrabalho")
	private JornadaTrabalho jornadaTrabalho;

	@Override
	public Long getId() {
		if(Long.valueOf(0).equals(id)){
			this.id = null;
		}
		return this.id;
	}

	/**
	 * @return the tempo
	 */
	public Long getTempo() {
		if(Long.valueOf(0).equals(tempo)){
			this.tempo = null;
		}
		return tempo;
	}

	/**
	 * @param tempo the tempo to set
	 */
	public void setTempo(final Long tempo) {
		this.tempo = tempo;
	}

	/**
	 * @return the tipoRefeicao
	 */
	public TipoRefeicaoEnum getTipoRefeicao() {
		return tipoRefeicao;
	}

	/**
	 * @param tipoRefeicao the tipoRefeicao to set
	 */
	public void setTipoRefeicao(final TipoRefeicaoEnum tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the jornadaTrabalho
	 */
	public JornadaTrabalho getJornadaTrabalho() {
		return jornadaTrabalho;
	}

	/**
	 * @param jornadaTrabalho the jornadaTrabalho to set
	 */
	public void setJornadaTrabalho(final JornadaTrabalho jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tempo == null) ? 0 : tempo.hashCode());
		result = prime * result
				+ ((tipoRefeicao == null) ? 0 : tipoRefeicao.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntervaloJornada other = (IntervaloJornada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tempo == null) {
			if (other.tempo != null)
				return false;
		} else if (!tempo.equals(other.tempo))
			return false;
		if (tipoRefeicao != other.tipoRefeicao)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IntervaloJornada [id=" + id + ", tempo=" + tempo
				+ ", tipoRefeicao=" + tipoRefeicao + "]";
	}
	
	/* (non-Javadoc)
	 * @see br.com.potierp.infra.bd.BaseEntityPotiErp#clone()
	 */
	@Override
	public IntervaloJornada clone() throws CloneNotSupportedException {
		return (IntervaloJornada) super.clone();
	}
}
