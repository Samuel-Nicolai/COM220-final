package Control;

import Model.Cliente;

import java.io.*;
import java.util.ArrayList;

public class ControleCliente {

    private Cliente objCliente = new Cliente();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private final String arquivoClientes = "clientes.dat";

    public ControleCliente(){}

    protected void desserializaCliente() throws Exception {
        File objFile = new File(arquivoClientes);
        if (objFile.exists()) {
            FileInputStream objFileInput = new FileInputStream(arquivoClientes);
            ObjectInputStream objInput = new ObjectInputStream(objFileInput);
            this.clientes = (ArrayList<Cliente>) objInput.readObject();
            objInput.close();
        }
    }

    private void serializaCliente() throws Exception {
        FileOutputStream objFileOutput = new FileOutputStream(arquivoClientes);
        ObjectOutputStream objOutput = new ObjectOutputStream(objFileOutput);
        objOutput.writeObject(clientes);
        objOutput.flush();
        objOutput.close();
    }

    public void finaliza() throws Exception {
        this.serializaCliente();
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void cadastraCliente(String cpf, String nome, String endereco, String email) throws Exception {
        this.objCliente = new Cliente(cpf, nome, endereco, email);
        if (clientes.contains(objCliente)){
            throw new Exception("O cliente " + cpf + " já está cadastrado.");
        }
        else {
            clientes.add(this.objCliente);
        }
    }

    public String consultaCliente(String cpf) {
        String retorno = "";
        for (Cliente c: clientes) {
            if (c.getCpf().equalsIgnoreCase(cpf)) {
                retorno += "Nome: " + c.getNome();
            }
        }
        if (retorno.equalsIgnoreCase("")){
            retorno += "Cliente " + cpf + " não encontrado.";
        }
        return retorno;
    }

    public boolean existeCliente(String cpf) {
        boolean existe = false;
        for (Cliente c: clientes) {
            if (c.getCpf().equalsIgnoreCase(cpf)){
                existe = true;
            }
        }
        return existe;
    }

    public Cliente getObjCliente(String cpf) {
        for (Cliente c: clientes) {
            if (cpf.equalsIgnoreCase(c.getCpf())) {
                this.objCliente = c;
            }
        }
        return this.objCliente;
    }
}
