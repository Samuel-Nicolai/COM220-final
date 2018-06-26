package Control;

import Model.Mercadoria;

import java.io.*;
import java.util.ArrayList;

public class ControleMercadoria {

    private Mercadoria objMercadoria;
    private ArrayList<Mercadoria> estoque = new ArrayList<>();
    private final String arquivoEstoque = "estoque.dat";

    public ControleMercadoria() {}

    protected void desserializaMercadoria() throws Exception {
        File objFile = new File(arquivoEstoque);
        if (objFile.exists()) {
            FileInputStream objFileInput = new FileInputStream(arquivoEstoque);
            ObjectInputStream objInput = new ObjectInputStream(objFileInput);
            this.estoque = (ArrayList<Mercadoria>) objInput.readObject();
            objInput.close();
        }
    }

    private void serializaMercadoria() throws Exception {
        FileOutputStream objFileOutput = new FileOutputStream(arquivoEstoque);
        ObjectOutputStream objOutput = new ObjectOutputStream(objFileOutput);
        objOutput.writeObject(estoque);
        objOutput.flush();
        objOutput.close();
    }

    public void finaliza() throws Exception {
        this.serializaMercadoria();
    }

    public int existeMercadoria(int codigo) {
        int existe = 0;
        for (Mercadoria m: estoque) {
            if (m.getCodigo() == codigo) {
                existe = estoque.indexOf(m);
            }
        }
        return existe;
    }

    public void cadastraMercadoria(int codigo, String descricao, float valorCompra, float valorVenda, int quantidade) {
        int index = this.existeMercadoria(codigo);
        if (index != 0) {
            estoque.get(index).setQuantidade(estoque.get(index).getQuantidade() + quantidade);
        } else {
            this.objMercadoria = new Mercadoria(codigo, descricao, valorCompra, valorVenda, quantidade);
            this.estoque.add(objMercadoria);
        }
    }

    public String consultaMercadoria(int codigo) {
        String retorno = "O Produto " + codigo + " não existe!";
        int index = this.existeMercadoria(codigo);
        if (index != 0) {
            retorno = "Produto: " + codigo + "\n" + "Descrição: " + estoque.get(index).getDescricao() + "\n" +
                        "Preço: " + estoque.get(index).getValorVenda() + "\nQuantidade: " + estoque.get(index).getQuantidade();
        }
        return retorno;
    }

    public int consultaQuantidade(int codigo) {
        int quant = 0;
        for (Mercadoria m: estoque) {
            if (m.getCodigo() == codigo) {
                quant = m.getQuantidade();
            }
        }
        return quant;
    }

    public Mercadoria getObjMercadoria(int codigo) {
        for (Mercadoria m: estoque) {
            if (m.getCodigo() == codigo) {
                objMercadoria = m;
            }
        }
        return objMercadoria;
    }
}
