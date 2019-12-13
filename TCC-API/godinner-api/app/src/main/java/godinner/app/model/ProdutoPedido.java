package godinner.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_produto_pedido")
public class ProdutoPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto_pedido")
	Integer id;

	@JsonIgnoreProperties({ "descricao", "vendidos", "status", "foto", "restaurante" })
	@ManyToOne
	@JoinColumn(name = "id_produto")
	@NotNull
	Produto produto;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	Pedido pedido;

	@NotNull
	@Column(name = "quantidade")
	Integer quantidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "ProdutoPedido [id=" + id + ", produto=" + produto + ", pedido=" + pedido + ", quantidade=" + quantidade
				+ ", getId()=" + getId() + ", getProduto()=" + getProduto() + ", getPedido()=" + getPedido()
				+ ", getQuantidade()=" + getQuantidade() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
