package br.com.potierp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.potierp.infra.bd.BaseEntityPotiErp;

/**
 * @author renan
 */
@NamedQueries(
		{
		@NamedQuery(name = EnderecoRepositorio.GET_ALL,
				    query = "FROM EnderecoRepositorio ")
		}
)

@Entity
@Table(name="enderecorepositorio")
public class EnderecoRepositorio extends BaseEntityPotiErp{

	private static final long serialVersionUID = -9209138782328382208L;
	
	public static final String GET_ALL = "EnderecoRepositorio.getAll";
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String cep;
	
	private String endereco;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;

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

	public String getCep() {
		return cep;
	}

	public void setCep(final String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(final String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(final String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(final String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(final String bairro) {
		this.bairro = bairro;
	}
}