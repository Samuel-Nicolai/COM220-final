package Control;

import Model.Mercadoria;

import java.io.*;
import java.util.ArrayList;

public class ControleMercadoria {

    private Mercadoria objMercadoria = new Mercadoria();
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
        int existe = -1;
        for (Mercadoria m: estoque) {
            if (m.getCodigo() == codigo) {
                existe = estoque.indexOf(m);
            }
        }
        return existe;
    }

    public void cadastraMercadoria(int codigo, String descricao, float valorCompra, float valorVenda, int quantidade) {
        int index = this.existeMercadoria(codigo);
        if (index != -1) {
            estoque.get(index).setQuantidade(estoque.get(index).getQuantidade() + quantidade);
        } else {
            this.objMercadoria = new Mercadoria(codigo, descricao, valorCompra, valorVenda, quantidade);
            this.estoque.add(objMercadoria);
        }
    }

    public String consultaMercadoria(int codigo) {
        String retorno = "O Produto " + codigo + " não existe!";
        int index = this.existeMercadoria(codigo);
        if (index != -1) {
            retorno = "Produto: " + codigo + "\n" + "Descrição: " + estoque.get(index).getDescricao() + "\n" +
                        "Preço: " + estoque.get(index).getValorVenda() + "\nQuantidade: " + estoque.get(index).getQuantidade();
        }
        return retorno;
    }

    public String consultaMercadoriaCompra(int codigo) {
        String retorno = ",";
        int index = this.existeMercadoria(codigo);
        if (index != -1) {
            retorno = codigo + "," + estoque.get(index).getValorVenda();
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

    public void diminuiEstoque(int codigo, int quantidade) {
        for (Mercadoria m: estoque) {
            if (m.getCodigo() == codigo) {
                m.setQuantidade(m.getQuantidade() - quantidade);
            }
        }
    }

    public Mercadoria getMercadoria(int codigo) {
        Mercadoria retorno = new Mercadoria();
        for (Mercadoria m: estoque) {
            if (m.getCodigo() == codigo) {
                retorno.setCodigo(m.getCodigo());
                retorno.setDescricao(m.getDescricao());
                retorno.setValorCompra(m.getValorCompra());
                retorno.setValorVenda(m.getValorVenda());
                retorno.setQuantidade(m.getQuantidade());
            }
        }
        return retorno;
    }
    
     public Mercadoria getObjMercadoria(int codigo)
    {
        for (Mercadoria m : estoque)
        {
            if (m.getCodigo() == codigo)
            {
                objMercadoria = m;
            }
        }
        return objMercadoria;
    }
     public Mercadoria consultaMercadorias(int pCodigo)
    {
        for (Mercadoria mec : estoque)
        {
            if (mec.getCodigo() == pCodigo)
            {
                return mec;
            }
        }
        return null;
    }

    public String listaMercadoria()
    {
        String retorno = "";
        retorno = "<html>"
                + "<table border = 2 bgcolor='graylight'>"
                + "<tr>"
                + "<th>Codigo</th>"
                + "<th>Descrição</th>"
                + "<th>Quantidade</th"
                + "<th>Preco de venda R$"
                + "</tr>";
        for (Mercadoria mec : estoque)
        {

            retorno += "<tr>"
                    + "<td>" + mec.getCodigo() + "</td>"
                    + "<td> " + mec.getDescricao() + "</td>"
                    + "<td>" + mec.getQuantidade() + "</td>"
                    + "<td>" + mec.getValorVenda() + "</td>"
                    + "</tr>";

        }
        retorno += "</table></html>";
        return retorno;
    }

    public void removeMercadoria(Mercadoria pMercadoria)
    {
        estoque.remove(pMercadoria);
    }
    //>>>EDIÇÃO<<< Da nova quantidade da mercadoria
    //Caso nao encontre a mercadoria devolve null

    public String editaMercadoria(int pCodigo, int pQtd)
    {
        String retorno = "O Produto " + pCodigo + " não existe!";
        for (Mercadoria mec : estoque)
        {
            if (mec.getCodigo() == pCodigo)
            {
                mec.setQuantidade(pQtd);
                retorno = "<html>"
                        + "<table border = 1 text-align : center>"
                        + "<tr>"
                        + "<th>Codigo</th>"
                        + "<th>Descrição</th>"
                        + "<th>Quantidade</th"
                        + "<th>Preço de venda R$"
                        + "</tr>";
                retorno += "<tr>"
                        + "<td>" + mec.getCodigo() + "</td>"
                        + "<td> " + mec.getDescricao() + "</td>"
                        + "<td>" + mec.getQuantidade() + "</td>"
                        + "<td>" + mec.getValorVenda() + "</td>"
                        + "</tr>";

                retorno += "</table></html>";
            }

        }
        return retorno;

    }
    
    
    
}
