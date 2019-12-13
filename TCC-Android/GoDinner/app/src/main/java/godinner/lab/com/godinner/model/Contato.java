package godinner.lab.com.godinner.model;

import java.io.Serializable;

public class Contato implements Serializable {

    private String nome;
    private String telefone;
    private String cpf;
    private Boolean notificacoes;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(Boolean notificacoes) {
        this.notificacoes = notificacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
