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
import javax.validation.constraints.Size;


@Entity
@Table(name = "tbl_cidade")
public class Cidade {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cidade")
	Integer id;
	
	@Column(name = "cidade")
	String cidade;
	
	@ManyToOne
	@JoinColumn(name = "id_estado")
    Estado estado;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cidade [id=" + id + ", cidade=" + cidade + ", estado=" + estado + "]";
	}
	
	
	
}
