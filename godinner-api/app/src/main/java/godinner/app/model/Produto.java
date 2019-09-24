package godinner.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.bytebuddy.implementation.bind.annotation.Default;
import net.bytebuddy.implementation.bind.annotation.Empty;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@Entity
@Table(name = "tbl_produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	Integer id;

	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "nome")
	String nome;

	@NotNull
	@Column(name = "preco")
	Integer preco;

	@Column(name = "descricao")
	@Size(max = 40)
	String descricao;

	@Column(name = "desconto")
	Integer desconto;

	@Column(name = "vendidos")
	Integer vendidos;

	@NotNull
	@ManyToOne

	@JoinColumn(name = "id_restaurante")
	@JsonIgnoreProperties({ "endereco", "telefone", "razaoSocial", "email", "foto", "cnpj", "senha" })
	Restaurante restaurante;

	@JsonIgnore
	@Column(name = "status")
	String status;

	@JsonIgnoreProperties({ "produto" })
	@OneToMany(mappedBy = "produto")
	List<FotoProduto> foto;

	public List<FotoProduto> getFoto() {
		return foto;
	}

	public void setFoto(List<FotoProduto> foto) {
		this.foto = foto;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

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

	public Integer getPreco() {
		return preco;
	}

	public void setPreco(Integer preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getVendidos() {
		return vendidos;
	}

	public void setVendidos(Integer vendidos) {
		this.vendidos = vendidos;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDesconto() {
		return desconto;
	}

	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", descricao=" + descricao + ", vendidos="
				+ vendidos + ", restaurante=" + restaurante.getId();
	}

}
