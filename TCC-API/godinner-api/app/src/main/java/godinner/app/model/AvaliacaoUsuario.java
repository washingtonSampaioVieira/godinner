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

@Entity
@Table(name = "tbl_avaliacao_usuario")
public class AvaliacaoUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_avaliacao_usuario")
	private Integer id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_restaurante")
	private Restaurante restaurante;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_consumidor")
	private Consumidor consumidor;
	private String comentario;

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

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "AvaliacaoUsuario [id=" + id + ", restaurante=" + restaurante + ", consumidor=" + consumidor
				+ ", comentario=" + comentario + "]";
	}
}
