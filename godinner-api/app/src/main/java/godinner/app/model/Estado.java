package godinner.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_estado")
public class Estado {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estado")
	private Integer id;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "uf")
	private String uf;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", estado=" + estado + ", uf=" + uf + "]";
	}

}
