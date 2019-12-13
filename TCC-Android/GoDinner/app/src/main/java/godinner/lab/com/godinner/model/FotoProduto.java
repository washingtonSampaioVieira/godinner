package godinner.lab.com.godinner.model;

import java.io.Serializable;

public class FotoProduto implements Serializable {

    private int id;
    private String foto;
    private String legenda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }
}
