package br.com.potierp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
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

import org.hibernate.validator.NotNull;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

@NamedQueries(
		{
		@NamedQuery(name = Fornecedor.GET_ALL,
				    query = "FROM Fornecedor "),
	    @NamedQuery(name = Fornecedor.GET_BY_CNPJ,
			    	query = "FROM Fornecedor c " +
	    					"WHERE c.cpfCnpj = :cpfCnpj "),
		@NamedQuery(name = Fornecedor.GET_BY_CODIGO, 
					query = "FROM Fornecedor f " +
							"WHERE f.codigo = :codigo ")
		}
)

@Entity
@Table(name="fornecedor")
public class Fornecedor extends BaseEntityPotiErp implements Cloneable{
	
	private static final long serialVersionUID = -1505831407555172536L;
	
	/**
	 * NQ para buscar todos os Fornecedores.
	 */
	public static final String GET_ALL = "Fornecedor.getAll";
	
	/**
	 * NQ para buscar Fornecedor de acordo com um CNPj.
	 */
	public static final String GET_BY_CNPJ = "Fornecedor.getByCnpj";
	
	/**
	 * NQ para buscar Fornecedor de acordo com o Código.
	 */
	public static final String GET_BY_CODIGO = "Fornecedor.getByCodigo";

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String codigo;
	
	@NotNull
	private String razaoSocial;
	
	@NotNull
	private String nomeFantasia;
	
	@NotNull
	private String tipoDocumento;
	
	@NotNull
	private String cpfCnpj;
	
	@NotNull
	private String tipoAtividade;
	
	@NotNull
	@Embedded
	private Endereco endereco;
	
	private Date dataInicioContrato;
	
	private Date dataFinalContrato;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idContato1")
	private Pessoa contato1;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idContato2")
	private Pessoa contato2;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idTelefone1")
	private Telefone telefone1;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idTelefone2")
	private Telefone telefone2;
	
	private String observacao;
	
	private Integer diaPagamento;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;
	
	@Enumerated(EnumType.STRING)
	private SituacaoEnum situacao;
	
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(final String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(final Endereco endereco) {
		this.endereco = endereco;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(final Empresa empresa) {
		this.empresa = empresa;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	public String getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(final String tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public Date getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(final Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public Date getDataFinalContrato() {
		return dataFinalContrato;
	}

	public void setDataFinalContrato(final Date dataFinalContrato) {
		this.dataFinalContrato = dataFinalContrato;
	}

	public Pessoa getContato1() {
		return contato1;
	}

	public void setContato1(final Pessoa contato1) {
		this.contato1 = contato1;
	}

	public Pessoa getContato2() {
		return contato2;
	}

	public void setContato2(final Pessoa contato2) {
		this.contato2 = contato2;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(final String observacao) {
		this.observacao = observacao;
	}

	public Integer getDiaPagamento() {
		if(Integer.valueOf(0).equals(diaPagamento)){
			this.diaPagamento = null;
		}
		return diaPagamento;
	}

	public void setDiaPagamento(final Integer diaPagamento) {
		this.diaPagamento = diaPagamento;
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
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(final String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(final SituacaoEnum situacao) {
		this.situacao = situacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((cpfCnpj == null) ? 0 : cpfCnpj.hashCode());
		result = prime * result
				+ ((diaPagamento == null) ? 0 : diaPagamento.hashCode());
		result = prime * result
				+ ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result
				+ ((tipoAtividade == null) ? 0 : tipoAtividade.hashCode());
		result = prime * result
				+ ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Fornecedor))
			return false;
		Fornecedor other = (Fornecedor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (cpfCnpj == null) {
			if (other.cpfCnpj != null)
				return false;
		} else if (!cpfCnpj.equals(other.cpfCnpj))
			return false;
		if (diaPagamento == null) {
			if (other.diaPagamento != null)
				return false;
		} else if (!diaPagamento.equals(other.diaPagamento))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		if (situacao != other.situacao)
			return false;
		if (tipoAtividade == null) {
			if (other.tipoAtividade != null)
				return false;
		} else if (!tipoAtividade.equals(other.tipoAtividade))
			return false;
		if (tipoDocumento == null) {
			if (other.tipoDocumento != null)
				return false;
		} else if (!tipoDocumento.equals(other.tipoDocumento))
			return false;
		return true;
	}
	
	@Override
	public Fornecedor clone() throws CloneNotSupportedException {
		return (Fornecedor)super.clone();
	}
}