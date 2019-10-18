package godinner.app.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class TemplateRestaurante {
	
	private Integer id;
	private String foto;
	private String sobre;
	private String slogan;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "id_restaurante")
	private Restaurante restaurante;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	@Override
	public String toString() {
		return "Template [id=" + id + ", foto=" + foto + ", sobre=" + sobre + ", slogan=" + slogan + ", restaurante="
				+ restaurante + "]";
	}

}
