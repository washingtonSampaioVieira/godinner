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
@Table(name = "tbl_foto_produto")
public class FotoProduto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_foto_produto")
	Integer id;
	String foto;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_produto")
	Produto produto;
	
	@Column(name="inde_foto")
	int indexFoto;
	
	String legenda;

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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getIndexFoto() {
		return indexFoto;
	}

	public void setIndexFoto(int indexFoto) {
		this.indexFoto = indexFoto;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	@Override
	public String toString() {
		return "FotoProduto [id=" + id + ", foto=" + foto + ", produto=" + produto + ", indexFoto=" + indexFoto
				+ ", legenda=" + legenda + "]";
	}
	
	
}
