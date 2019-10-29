package godinner.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import godinner.app.repository.StatusPedidoRepository;

@Entity
@Table(name = "tbl_status_pedido")
public class StatusPedido {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_status_pedido")
	Integer id;

	@Column(name = "descricao")
	String descricao;

	public StatusPedido(int idStatus) {
		this.id = idStatus;
	}
	public StatusPedido() {};

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "StatusPedido [id=" + id + ", descricao=" + descricao + ", getId()=" + getId() + ", getDescricao()="
				+ getDescricao() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
