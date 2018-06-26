package Control;

import Model.NotaFiscal;

import java.io.*;
import java.util.ArrayList;

public class ControleNotaFiscal {

    private NotaFiscal objNotaFiscal;
    private ArrayList<NotaFiscal> vendas = new ArrayList<>();
    private final String arquivoVendas = "vendas.dat";

    public ControleNotaFiscal() {}

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

}
