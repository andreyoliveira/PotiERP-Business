package br.com.potierp.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = JornadaTrabalho.GET_ALL,
				    query = "FROM JornadaTrabalho "),
	    @NamedQuery(name = JornadaTrabalho.GET_BY_NOME,
			    query = "FROM JornadaTrabalho jt " +
			    		"WHERE jt.nome = :nome "),
		@NamedQuery(name = JornadaTrabalho.GET_POR_ID_COM_INTERVALO, 
					query = "FROM JornadaTrabalho jt " +
							" left join fetch jt.intervalosJornada ij " +
							"WHERE jt.id = :id ")
		}
		
)

@Entity
@Table(name="jornadatrabalho")
public class JornadaTrabalho extends BaseEntityPotiErp implements Cloneable{

	private static final long serialVersionUID = -4685020372867025017L;
	
	/**
	 * NQ para buscar todos as Jornada de Trabalho.
	 */
	public static final String GET_ALL = "JornadaTrabalho.getAll";
	
	/**
	 * NQ para buscar Jornadas de Trabalho de acordo com o nome.
	 */
	public static final String GET_BY_NOME = "JornadaTrabalho.getByNome";
	
	public static final String GET_POR_ID_COM_INTERVALO = "JornadaTrabalho.getPorIdComIntervalo";

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String descricao;

	private Boolean utilizada;
	
	private boolean diaSeguinte;
	
	private String horarioInicial;
	
	private String horarioFinal;
	
	@OneToMany(mappedBy = "jornadaTrabalho", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Cascade(value={org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private Collection<IntervaloJornada> intervalosJornada;

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

	public Boolean isUtilizada() {
		return utilizada;
	}

	public void setUtilizada(final Boolean utilizada) {
		this.utilizada = utilizada;
	}
	
	/**
	 * @return the horarioInicial
	 */
	public String getHorarioInicial() {
		return horarioInicial;
	}

	/**
	 * @param horarioInicial the horarioInicial to set
	 */
	public void setHorarioInicial(final String horarioInicial) {
		this.horarioInicial = horarioInicial;
	}

	/**
	 * @return the horarioFinal
	 */
	public String getHorarioFinal() {
		return horarioFinal;
	}

	/**
	 * @param horarioFinal the horarioFinal to set
	 */
	public void setHorarioFinal(final String horarioFinal) {
		this.horarioFinal = horarioFinal;
	}

	/**
	 * @return the intervalosJornada
	 */
	public Collection<IntervaloJornada> getIntervalosJornada() {
		return this.intervalosJornada;
	}

	/**
	 * @param intervalosJornada the intervalosJornada to set
	 */
	public void setIntervalosJornada(final Collection<IntervaloJornada> intervalosJornada) {
		this.intervalosJornada = intervalosJornada;
	}

	/**
	 * @return the diaSeguinte
	 */
	public boolean isDiaSeguinte() {
		return diaSeguinte;
	}

	/**
	 * @param diaSeguinte the diaSeguinte to set
	 */
	public void setDiaSeguinte(final boolean diaSeguinte) {
		this.diaSeguinte = diaSeguinte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((utilizada == null) ? 0 : utilizada.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof JornadaTrabalho))
			return false;
		JornadaTrabalho other = (JornadaTrabalho) obj;
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
		if (utilizada == null) {
			if (other.utilizada != null)
				return false;
		} else if (!utilizada.equals(other.utilizada))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "JornadaTrabalho [id=" + id + ", nome=" + nome + ", descricao="
				+ descricao + ", utilizada=" + utilizada + "]";
	}

	@Override
	public JornadaTrabalho clone() throws CloneNotSupportedException {
		return (JornadaTrabalho)super.clone();
	}
}