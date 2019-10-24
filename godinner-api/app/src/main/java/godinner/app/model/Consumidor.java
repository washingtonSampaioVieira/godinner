package godinner.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_consumidor")
public class Consumidor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_consumidor")
	private Integer id;

	@Column(name = "nome")
	@NotNull
	@Size(min = 3, max = 45)
	private String nome;

	@NotNull
	@Size(min = 10, max = 100)
	private String email;

	@NotNull
	@Size(min = 6, max = 255)
	private String senha;

	@NotNull
	@Size(min = 13, max = 15)
	private String cpf;

	@Size(max = 15)
	private String telefone;

	@Column(name = "foto_perfil")
	@Size(max = 255)
	private String fotoPerfil;

	@JoinColumn(name = "id_endereco")
	@ManyToOne
	@NotNull
	private Endereco endereco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Consumidor [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", telefone=" + telefone + ", fotoPerfil=" + fotoPerfil + ", endereco=" + endereco + "]";
	}
}