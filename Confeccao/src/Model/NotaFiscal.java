package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class NotaFiscal implements Serializable {

    private int id;
    private Date data;
    private Cliente cliente;
    private ArrayList<Mercadoria> itens = new ArrayList<>();

    public NotaFiscal() {}

    public NotaFiscal(Date data, Cliente cliente) {
        this.id = (int) System.currentTimeMillis();
        this.data = data;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Mercadoria> getItens() {
        return itens;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setIten(Mercadoria item, int quantidade) {
        item.setQuantidade(quantidade);
        this.itens.add(item);
    }

    public String stringNota() {
        String retorno = "";
        retorno += "Confecção CNPJ: 999888777 \tNota:\t" + this.getId();
        retorno += "\nCliente:\tCPF: " + this.getCliente().getCpf() + "\tNome: " + this.getCliente().getNome();
        retorno += "\nProduto\tDescrição\t\tPreço\tQuantidade";
        for (Mercadoria m: itens) {
            retorno += "\n" + m.getCodigo() + "\t" + m.getDescricao() + "\t\t" + m.getValorVenda() + "\t" + m.getQuantidade();
        }
        retorno += "\nTotal: " + this.calculaValorNota();
        return retorno;
    }

    public float calculaValorNota() {
        float valor = 0;
        for (Mercadoria m: itens) {
            valor += m.getValorVenda() * m.getQuantidade();
        }
        return valor;
    }
}
