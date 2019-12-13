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

import ch.qos.logback.core.status.Status;

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
	Integer comissaoPaga;

	@JsonProperty("produtos")
	@OneToMany(mappedBy = "pedido")
	private List<ProdutoPedido> produtos;
	
	
	@JsonIgnoreProperties({ "email", "senha", "cpf", "telefone"})
	@ManyToOne
	@JoinColumn(name = "id_consumidor")
	private Consumidor consumidor;

	@ManyToOne
	@JoinColumn(name = "id_status_pedido")
	private StatusPedido statusPedido;
	
	@Column(name = "descricao")
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
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

	public Integer getComissaoPaga() {
		return comissaoPaga;
	}

	public void setComissaoPaga(Integer i) {
		this.comissaoPaga = i;
	}
	public Consumidor getConsumidor() {
		return consumidor;
	}
	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}
}
