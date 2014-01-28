package br.com.potierp.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = Empresa.GET_ALL,
				    query = "FROM Empresa "),
	    @NamedQuery(name = Empresa.GET_BY_CNPJ,
			    query = "FROM Empresa e " +
			    		"WHERE e.cnpj = :cnpj ")
		}
)
@Entity
@Table(name="empresa")
public class Empresa extends BaseEntityPotiErp implements Cloneable{

	private static final long serialVersionUID = -2361974148969024253L;
	
	/**
	 * NQ para buscar todas as Empresas.
	 */
	public static final String GET_ALL = "Empresa.getAll";
	
	/**
	 * NQ para buscar Empresas de acordo com um CNPj.
	 */
	public static final String GET_BY_CNPJ = "Empresa.getByCnpj";

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String razaoSocial;
	
	@NotNull
	private String nomeFantasia;
	
	@NotNull
	private String cnpj;
	
	private String codigoMunicipal;
	
	@NotNull
	private String tipoAtividade;
	
	@NotNull
	@Embedded
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name = "idMatriz")
	private Empresa matriz;
	
	@OneToMany
	@JoinTable(name ="empresamatriz_empresafilial",
			joinColumns = @JoinColumn(name="idMatriz"),
			inverseJoinColumns = @JoinColumn(name="idFilial"))
	private List<Empresa> filiais;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idTelefone1")
	private Telefone telefone1;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="idTelefone2")
	private Telefone telefone2;

	private String email;

	private String responsavel;

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

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(final String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(final String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(final String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCodigoMunicipal() {
		return codigoMunicipal;
	}

	public void setCodigoMunicipal(final String codigoMunicipal) {
		this.codigoMunicipal = codigoMunicipal;
	}

	public String getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(final String tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(final Endereco endereco) {
		this.endereco = endereco;
	}

	public Empresa getMatriz() {
		return matriz;
	}

	public void setMatriz(final Empresa matriz) {
		this.matriz = matriz;
	}

	public List<Empresa> getFiliais() {
		return filiais;
	}

	public void setFiliais(final List<Empresa> filiais) {
		this.filiais = filiais;
	}

	public Telefone getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(final Telefone telefone1) {
		this.telefone1 = telefone1;
	}

	public Telefone getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(final Telefone telefone2) {
		this.telefone2 = telefone2;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(final String responsavel) {
		this.responsavel = responsavel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result
				+ ((tipoAtividade == null) ? 0 : tipoAtividade.hashCode());
		result = prime * result
				+ ((codigoMunicipal == null) ? 0 : codigoMunicipal.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result
				+ ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		result = prime * result
				+ ((responsavel == null) ? 0 : responsavel.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Empresa))
			return false;
		Empresa other = (Empresa) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (tipoAtividade == null) {
			if (other.tipoAtividade != null)
				return false;
		} else if (!tipoAtividade.equals(other.tipoAtividade))
			return false;
		if (codigoMunicipal == null) {
			if (other.codigoMunicipal != null)
				return false;
		} else if (!codigoMunicipal.equals(other.codigoMunicipal))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Empresa [id=" + id + ", razaoSocial=" + razaoSocial
				+ ", nomeFantasia=" + nomeFantasia + ", cnpj=" + cnpj
				+ ", codigoMunicipal=" + codigoMunicipal + ", tipoAtividade="
				+ tipoAtividade + ", endereco=" + endereco + ", matriz="
				+ matriz + ", filiais=" + filiais + ", telefone1=" + telefone1
				+ ", telefone2=" + telefone2 + ", email=" + email
				+ ", responsavel=" + responsavel + "]";
	}

	@Override
	public Empresa clone() throws CloneNotSupportedException {
		Empresa empresa = (Empresa)super.clone();
		empresa.setEndereco(endereco.clone());
		empresa.setMatriz(matriz.clone());
		empresa.setTelefone1(telefone1.clone());
		empresa.setTelefone2(telefone2.clone());
		Collections.copy(empresa.getFiliais(), filiais);
		return empresa;
	}
}