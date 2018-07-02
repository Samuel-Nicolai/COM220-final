package Model;

import java.io.Serializable;

public class Cliente implements Serializable {

    private String cpf;
    private String nome;
    private String endereco;
    private String email;

    public Cliente() {}

    public Cliente(String cpf, String nome, String endereco, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
