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

import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tbl_pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	Integer id;

	@JsonIgnoreProperties({ "endereco", "telefone", "cnpj", "razaoSocial", "senha", "email", "foto" })
	@ManyToOne
	@JoinColumn(name = "id_restaurante")
	Restaurante restaurante;

	@NotNull
	@Column(name = "valor_entrega")
	Double valorEntrega;

	@NotNull
	@Column(name = "valor_total")
	Double valorTotal;

	@Column(name = "data_do_pedido")
	String dataDoPedido;

	@Column(name = "comissao_paga")
	Boolean comissaoPaga;
	
	
	@JsonProperty("produtos")
	@OneToMany(mappedBy = "pedido")
	private List<ProdutoPedido> produtos;

	private MessageType type;

	public enum MessageType {
		CHAT, LEAVE, JOIN
	}

	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Pedido [id=" + id + ", restaurante=" + restaurante + ", valorEntrega=" + valorEntrega + ", valorTotal="
				+ valorTotal + ", dataDoPedido=" + dataDoPedido + ", comissaoPaga=" + comissaoPaga + ", produtos="
				+ produtos + "]";
	}

	public List<ProdutoPedido> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoPedido> produtos) {
		this.produtos = produtos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Double getValorEntrega() {
		return valorEntrega;
	}

	public void setValorEntrega(Double valorEntrega) {
		this.valorEntrega = valorEntrega;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDataDoPedido() {
		return dataDoPedido;
	}

	public void setDataDoPedido(String dataDoPedido) {
		this.dataDoPedido = dataDoPedido;
	}

	public Boolean getComissaoPaga() {
		return comissaoPaga;
	}

	public void setComissaoPaga(Boolean comissaoPaga) {
		this.comissaoPaga = comissaoPaga;
	}

}
