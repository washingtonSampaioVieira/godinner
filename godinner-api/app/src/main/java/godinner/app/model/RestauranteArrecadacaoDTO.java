package godinner.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_arrecadacao_restaurante")
public class RestauranteArrecadacaoDTO {
	
	private float resultado;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_restaurante")
	private Integer id;
	
	
	public float getResultado() {
		return resultado;
	}


	public void setResultado(float resultado) {
		this.resultado = resultado;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}




	@Override
	public String toString() {
		return "RestauranteArrecadacaoDTO [resultado=" + resultado + ", id=" + id + "]";
	}

	
	
	
	
}
