package godinner.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import godinner.app.model.Endereco;


@Entity
@Table(name = "tbl_restaurante")
public class Restaurante {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_restaurante")
    Integer id;
	
    
    @Size(max = 100 , min = 8)
	@Column(name = "email", unique = true)
    @NotNull
    String email;
    
    @Size(max = 255 , min = 8)
	@Column(name = "senha")
    @NotNull
    String senha;
    
    @Size(max = 50 , min = 2)
	@Column(name = "razao_social")
    @NotNull
    String razaoSocial;
    
    
    @Size(max = 18 , min = 13)
	@Column(name = "cnpj", unique = true)
    @NotNull
    String cnpj;
	
    
	@Column(name = "telefone")
    String telefone;
    
	@OneToOne
	@JoinColumn(name = "id_endereco")
    Endereco endereco;
	
	String foto;
	
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
//	@Column(name = "status")
//    String status;
//	
//	@Column(name = "criacao")
//    String criacao;
//	
//	@Column(name = "ultima_atualizacao")
//    String ultimaAtualizacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	@Override
	public String toString() {
		return "Restaurante [id=" + id + ", email=" + email + ", senha=" + senha + ", razaoSocial=" + razaoSocial
				+ ", cnpj=" + cnpj + ", telefone=" + telefone + ", endereco=" + endereco + ", foto=" + foto + "]";
	}

	

	

	
	
	
	
}
