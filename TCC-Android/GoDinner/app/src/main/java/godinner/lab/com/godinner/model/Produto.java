package godinner.lab.com.godinner.model;

import java.io.Serializable;
import java.util.List;

public class Produto implements Serializable{

    private Integer id;
    private String nome;
    private Double preco;
    private String descricao;
    private Double desconto;
    private Integer vendidos;
    private List<FotoProduto> fotos;

    public List<FotoProduto> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoProduto> fotos) {
        this.fotos = fotos;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getVendidos() {
        return vendidos;
    }

    public void setVendidos(Integer vendidos) {
        this.vendidos = vendidos;
    }
}
