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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = Dependente.GET_ALL,
				    query = "FROM Dependente "),
		@NamedQuery(name = Dependente.GET_BY_NOME_E_FUNCIONARIO,
					query = "select d FROM Dependente d " +
							"Inner Join d.funcionario f " +
							"WHERE d.nome = :nome " +
							"and f.codigoRegistro = :codigoRegistro ")
		}
)
@Entity
@Table(name="dependente")
public class Dependente extends BaseEntityPotiErp implements Cloneable{

	private static final long serialVersionUID = 4759038189192285818L;
	
	/**
	 * NQ para buscar todos os Dependentes.
	 */
	public static final String GET_ALL = "Dependente.getAll";
	
	/**
	 * NQ para buscar dependente de acordo com o nome e funcionario.
	 */
	public static final String GET_BY_NOME_E_FUNCIONARIO = "Dependente.getPorNomeEFuncionario";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String localNascimento;
	
	private String cartorio;
	
	private Long numeroRegistro;
	
	private Long numeroFolha;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
	@Temporal(TemporalType.DATE)
	private Date dataBaixa;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name= "idGrauParentesco")
	private GrauParentesco grauParentesco;
	
	private Boolean continuaDependente;
	
	@Transient
	private Long identificador;
	
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(final Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(final Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(final Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public String getLocalNascimento() {
		return localNascimento;
	}

	public void setLocalNascimento(final String localNascimento) {
		this.localNascimento = localNascimento;
	}

	public String getCartorio() {
		return cartorio;
	}

	public void setCartorio(final String cartorio) {
		this.cartorio = cartorio;
	}

	public Long getNumeroRegistro() {
		if(Long.valueOf(0).equals(numeroRegistro)){
			this.numeroRegistro = null;
		}
		return numeroRegistro;
	}

	public void setNumeroRegistro(final Long numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public Long getNumeroFolha() {
		if(Long.valueOf(0).equals(numeroFolha)){
			this.numeroFolha = null;
		}
		return numeroFolha;
	}

	public void setNumeroFolha(final Long numeroFolha) {
		this.numeroFolha = numeroFolha;
	}

	public Date getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(final Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}
	
	public GrauParentesco getGrauParentesco() {
		return grauParentesco;
	}

	public void setGrauParentesco(final GrauParentesco grauParentesco) {
		this.grauParentesco = grauParentesco;
	}

	public Long getIdentificador() {
		if(Long.valueOf(0).equals(identificador)){
			this.identificador = null;
		}
		return identificador;
	}

	public void setIdentificador(final Long identificador) {
		this.identificador = identificador;
	}

	/**
	 * @return the continuaDependente
	 */
	public Boolean getContinuaDependente() {
		return continuaDependente;
	}

	/**
	 * @param continuaDependente the continuaDependente to set
	 */
	public void setContinuaDependente(final Boolean continuaDependente) {
		this.continuaDependente = continuaDependente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cartorio == null) ? 0 : cartorio.hashCode());
		result = prime * result
				+ ((dataBaixa == null) ? 0 : dataBaixa.hashCode());
		result = prime * result
				+ ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result
				+ ((localNascimento == null) ? 0 : localNascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((numeroFolha == null) ? 0 : numeroFolha.hashCode());
		result = prime * result
				+ ((numeroRegistro == null) ? 0 : numeroRegistro.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Dependente))
			return false;
		Dependente other = (Dependente) obj;
		if (cartorio == null) {
			if (other.cartorio != null)
				return false;
		} else if (!cartorio.equals(other.cartorio))
			return false;
		if (dataBaixa == null) {
			if (other.dataBaixa != null)
				return false;
		} else if (!dataBaixa.equals(other.dataBaixa))
			return false;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (localNascimento == null) {
			if (other.localNascimento != null)
				return false;
		} else if (!localNascimento.equals(other.localNascimento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroFolha == null) {
			if (other.numeroFolha != null)
				return false;
		} else if (!numeroFolha.equals(other.numeroFolha))
			return false;
		if (numeroRegistro == null) {
			if (other.numeroRegistro != null)
				return false;
		} else if (!numeroRegistro.equals(other.numeroRegistro))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Dependente [id=" + id + ", nome=" + nome + ", localNascimento="
				+ localNascimento + ", cartorio=" + cartorio
				+ ", numeroRegistro=" + numeroRegistro + ", numeroFolha="
				+ numeroFolha + ", dataNascimento=" + dataNascimento
				+ ", dataInclusao=" + dataInclusao + ", dataBaixa=" + dataBaixa
				+ ", grauParentesco=" + grauParentesco + ", identificador="
				+ identificador + "]";
	}

	@Override
	public Dependente clone() throws CloneNotSupportedException {
		return (Dependente)super.clone();
	}
}