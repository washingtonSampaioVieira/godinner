package godinner.app.model;

import javax.persistence.Entity;

import org.hibernate.annotations.NotFound;

@Entity
public class ProdutoExibicao extends Produto {
	
	private String foto;
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}	
}
