package godinner.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ProdutoExibicao{
	
	Integer id;
	String nome;
	Integer preco;
	String descricao;
	String desconto;
	
	Integer vendidos;
	@JsonIgnore
	Restaurante restaurante;
	String status;
	String foto;
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPreco() {
		return preco;
	}

	public void setPreco(Integer preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDesconto() {
		return desconto;
	}

	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}

	public Integer getVendidos() {
		return vendidos;
	}

	public void setVendidos(Integer vendidos) {
		this.vendidos = vendidos;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
			
			
}
