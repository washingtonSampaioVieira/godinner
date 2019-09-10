package godinner.app.model;

import javax.persistence.Entity;

import org.hibernate.annotations.NotFound;

@Entity
public class ProdutoExibicao extends Produto {
	
	private String foto;
	
	
	public String distacia;
	
	public String tempoEntrega;
	
	
	
	public String getDistacia() {
		return distacia;
	}

	public void setDistacia(String distacia) {
		this.distacia = distacia;
	}

	public void setTempoEntrega(String tempoEntrega) {
		this.tempoEntrega = tempoEntrega;
	}
	public String getTempoEntrega() {
		return tempoEntrega;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}	
}
