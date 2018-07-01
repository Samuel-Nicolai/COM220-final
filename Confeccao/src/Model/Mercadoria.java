package Model;

import java.io.Serializable;

public class Mercadoria implements Serializable, Comparable<Mercadoria> {

    private int codigo;
    private String descricao;
    private float valorCompra;
    private float valorVenda;
    private int quantidade;

    public Mercadoria() {}

    public Mercadoria(int codigo, String descricao, float valorCompra, float valorVenda, int quantidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValorCompra() {
        return valorCompra;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorCompra(float valorCompra) {
        this.valorCompra = valorCompra;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int compareTo(Mercadoria o) {
        if (this.quantidade < o.quantidade) {
            return 1;
        }
        if (this.quantidade > o.quantidade) {
            return -1;
        }
        return 0;
    }
}
