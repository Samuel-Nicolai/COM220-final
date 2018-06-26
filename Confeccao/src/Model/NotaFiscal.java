package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class NotaFiscal implements Serializable {

    private int id;
    private Date data;
    private Cliente cliente;
    private ArrayList<Mercadoria> itens = new ArrayList<>();

    public NotaFiscal(int codigo, Date data, Cliente cliente, ArrayList<Mercadoria> itens) {
        this.id = (int) System.currentTimeMillis();
        this.data = data;
        this.cliente = cliente;
        this.itens = itens;
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

    public void setItens(ArrayList<Mercadoria> itens) {
        this.itens = itens;
    }
}
