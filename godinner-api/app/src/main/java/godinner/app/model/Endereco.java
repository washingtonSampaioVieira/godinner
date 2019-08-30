package godinner.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_endereco")
public class Endereco {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	Integer id;
	
	@Size(max = 15 , min = 8)
	@Column(name = "cep")
    @NotNull
	String cep;
	
	@Size(max = 6 , min = 1)
	@Column(name = "numero")
    @NotNull
	String numero;
	
	@Size(max = 70 , min = 3)
	@Column(name = "logradouro")
    @NotNull
	String logradouro;
	
	@Size(max = 30 , min = 3)
	@Column(name = "bairro")
    @NotNull
	String bairro;
	
	@Size(max = 15 )
	@Column(name = "complemento")
    @NotNull
	String complemento;
	
	@Size(max = 50)
	@Column(name = "referencia")
	String referencia;
	
	@OneToOne
	@JoinColumn(name = "id_cidade")
    @NotNull
    Cidade cidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", cep=" + cep + ", numero=" + numero + ", logradouro=" + logradouro + ", bairro="
				+ bairro + ", complemento=" + complemento + ", referencia=" + referencia + ", cidade=" + cidade + "]";
	}
	
	
	
}
