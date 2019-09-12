package godinner.app.model;

import javax.persistence.Entity;

@Entity
public class RestauranteExibicao extends Restaurante {

	public String distancia;
	public String tempoEntrega;
	public Double nota;

	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getTempoEntrega() {
		return tempoEntrega;
	}

	public void setTempoEntrega(String tempoEntrega) {
		this.tempoEntrega = tempoEntrega;
	}

}
