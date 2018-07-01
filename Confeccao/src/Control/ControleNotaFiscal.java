package Control;

import Model.Mercadoria;
import Model.NotaFiscal;

import java.io.*;
import java.util.*;
import java.util.Collections.*;
import java.util.function.Predicate;

public class ControleNotaFiscal {

    private NotaFiscal objNotaFiscal = new NotaFiscal();
    private Controle objControle;
    private ArrayList<NotaFiscal> vendas = new ArrayList<>();
    private final String arquivoVendas = "vendas.dat";

    public ControleNotaFiscal(Controle objControle) {
        this.objControle = objControle;
    }

    protected void desserializaNotaFiscal() throws Exception {
        File objFile = new File(arquivoVendas);
        if (objFile.exists()) {
            FileInputStream objFileInput = new FileInputStream(arquivoVendas);
            ObjectInputStream inputStream = new ObjectInputStream(objFileInput);
            this.vendas = (ArrayList<NotaFiscal>) inputStream.readObject();
            inputStream.close();
        }
    }

    private void serializaNotaFiscal() throws Exception {
        FileOutputStream outputStream = new FileOutputStream(arquivoVendas);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(vendas);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public void finaliza() throws Exception{
        this.serializaNotaFiscal();
    }

    public String geraNota(Date data, String codCliente, ArrayList<String> compras) {
        String sucesso = "";
        this.objNotaFiscal = new NotaFiscal(data, objControle.getObjCtrCliente().getObjCliente(codCliente));
        for (String str: compras) {
            int codProduto = Integer.parseInt(str.split(",")[0]);
            int quantProduto = Integer.parseInt(str.split(",")[1]);
            this.objNotaFiscal.setIten(objControle.getObjCtrMercadoria().getMercadoria(codProduto), quantProduto);
            objControle.getObjCtrMercadoria().diminuiEstoque(codProduto, quantProduto);
        }
        vendas.add(this.objNotaFiscal);
        return sucesso = this.objNotaFiscal.stringNota();
    }

    public String faturamentoProduto(int codigo) {
        float faturamento = 0;
        String retorno = "O faturamento do produto " + codigo + " foi de R$ ";
        for (NotaFiscal nota: vendas) {
            for (Mercadoria merc: nota.getItens()) {
                if (codigo == merc.getCodigo()) {
                    faturamento += merc.getValorVenda() * merc.getQuantidade();
                }
            }
        }
        retorno += faturamento + ".\n";
        return retorno;
    }

    public String faturamentoCliente(String cpf) {
        float faturamento = 0;
        String retorno = "O faturamento sobre o cliente " + cpf + " foi de R$ ";
        for (NotaFiscal nota: vendas) {
            if (nota.getCliente().getCpf().equalsIgnoreCase(cpf)) {
                for (Mercadoria merc: nota.getItens()) {
                    faturamento += merc.getValorVenda() * merc.getQuantidade();
                }
            }
        }
        retorno += faturamento + ".\n";
        return retorno;
    }

    public String faturamentoPeriodo(Date inicio, Date fim) {
        float faturamento = 0;
        String retorno = "O faturamento no período de \n" + inicio.toString() + " à " + fim.toString() + "\n foi de R$ ";
        for (NotaFiscal venda: vendas) {
            if ((inicio.compareTo(venda.getData()) <= 0) && (fim.compareTo(venda.getData()) >= 0)) {
                for (Mercadoria produto: venda.getItens()) {
                    faturamento += produto.getValorVenda() * produto.getQuantidade();
                }
            }
        }
        retorno += faturamento + ".";
        return retorno;
    }

    public String lucroPeriodo(Date inicio, Date fim) {
        float lucro = 0;
        String retorno = "O lucro no período de \n" + inicio.toString() + " à " + fim.toString() + "\n foi de R$ ";
        for (NotaFiscal venda: vendas) {
            if ((inicio.compareTo(venda.getData()) <= 0) && (fim.compareTo(venda.getData()) >= 0)) {
                for (Mercadoria produto: venda.getItens()) {
                    lucro += (produto.getValorVenda() - produto.getValorCompra()) * produto.getQuantidade();
                }
            }
        }
        retorno += lucro + ".";
        return retorno;
    }

    public String vendasClientePeriodo(String cpf, Date inicio, Date fim) {
        float valor = 0;
        String retorno = "Compras realizadas pelo cliente " + cpf + " de \n" + inicio.toString() + " à " + fim.toString();
        for (NotaFiscal venda: vendas) {
            if ((inicio.compareTo(venda.getData()) <= 0) && (fim.compareTo(venda.getData()) >= 0) && venda.getCliente().getCpf().equalsIgnoreCase(cpf)) {
                retorno += "\n Nota: " + venda.getId() + " valor: R$ ";
                for (Mercadoria produto: venda.getItens()) {
                    valor += (produto.getValorVenda()) * produto.getQuantidade();
                }
                retorno += valor + ".";
                valor = 0;
            }
        }
        return retorno;
    }

    public String topDezProdutosVendidos() {
        String retorno = "Ranking dos produtos mais vendidos: ";
        retorno += "\nProduto\tDescrição\t\tPreço\tQuantidade";

        ArrayList<Mercadoria> todosProdVendidos = new ArrayList<>();
        ArrayList<Mercadoria> ranking = new ArrayList<>();

        for (NotaFiscal nota: vendas) {
            for (Mercadoria m: nota.getItens()) {
                    Mercadoria aux = new Mercadoria(m.getCodigo(), m.getDescricao(), m.getValorCompra(), m.getValorVenda(), m.getQuantidade());
                    todosProdVendidos.add(aux);
            }
        }

        Set<Integer> codigos = new TreeSet<Integer>();
        for (Mercadoria m: todosProdVendidos) {
            codigos.add(new Integer(m.getCodigo()));
        }

        for (Integer cod: codigos) {
            int quantidade = this.quantidadeTotal(cod, todosProdVendidos);
            Mercadoria aux = objControle.getObjCtrMercadoria().getMercadoria(cod);
            aux.setQuantidade(quantidade);
            ranking.add(aux);
        }

        Collections.sort(ranking);

        for (Mercadoria ran: ranking) {
            retorno += "\n" + ran.getCodigo() + "\t" + ran.getDescricao() + "\t\t" + ran.getValorVenda() + "\t" + ran.getQuantidade();
        }
        return retorno;
    }

    private int quantidadeTotal(int cod, ArrayList<Mercadoria> todosProdVendidos) {
        int quantidade = 0;
        for (Mercadoria m: todosProdVendidos) {
            if (m.getCodigo() == cod) {
                quantidade += m.getQuantidade();
            }
        }
        return quantidade;
    }

}
