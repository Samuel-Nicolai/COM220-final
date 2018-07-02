package Control;

import Model.Cliente;
import Model.Mercadoria;

import java.io.*;
import java.util.ArrayList;

public class ControleCliente {

    private Cliente objCliente;
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private final String arquivoClientes = "clientes.dat";
    
    //Contrutor Vazio
    public ControleCliente() {
    }
    
    //Métodos de persistencia
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
    
    //Metodo para fechar programa
    public void finaliza() throws Exception {
        this.serializaCliente();
    }
    
    //Retorna a lista de clientes em formato ArrayList
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    
    //Instancia um novo cliente e guarda no ArrayList de clientes
    public void cadastraCliente(String cpf, String nome, String endereco, String email) throws Exception {
        this.objCliente = new Cliente(cpf, nome, endereco, email);
        
        //Exceção de cpf incorreto
        char[] ch = cpf.toCharArray();
        boolean d = true;
        for (int i = 0; i < ch.length; i++) {
            if (!Character.isDigit(ch[i])) {
                d = false;
                throw new Exception("Digite apenas numeros para cpf");
            }
        }
        
        //Exceção de nome incorreto
        char[] ch3 = nome.toCharArray();
        boolean d3 = true;
        for (int i = 0; i < ch3.length; i++) {
            if (Character.isDigit(ch3[i])) {
                d3 = false;
                throw new Exception("Digite apenas letras para o nome");
            }
        }
        
        //Verifica se email possui um @
        int ce;
        ce = email.indexOf("@");
        if (ce < 0) {
            throw new Exception("Digite um email válido (com @)");
        }
        
        //Verifica se o cliente ja existe
        if (existeCliente(cpf)) {
            throw new Exception("O cliente com cpf " + cpf + " já está cadastrado.");
        } else {
            clientes.add(this.objCliente);
        }
    }
    
    //Retorna tabela html com informações do cliente
    public String consultaCliente(String cpf) {
        String retorno = "";
        boolean b = false;
        for (Cliente c : clientes) {
            if (c.getCpf().equalsIgnoreCase(cpf)) {

                retorno = "<html>"
                        + "<table border = 2 bgcolor='graylight'>"
                        + "<tr>"
                        + "<th>Nome</th>"
                        + "<th>CPF</th>"
                        + "<th>Endereço</th"
                        + "<th>Email"
                        + "</tr>";
                

                    retorno += "<tr>"
                            + "<td>" + c.getNome()+ "</td>"
                            + "<td> " + c.getCpf() + "</td>"
                            + "<td>" + c.getEndereco() + "</td>"
                            + "<td>" + c.getEmail() + "</td>"
                            + "</tr>";

                }
                retorno += "</table></html>";
                b =true;

            }
        
        //Caso cliente não exista
        if (b == false) {
            retorno += "Cliente com cpf " + cpf + " não encontrado.";
        }

        return retorno;
    }
    
    //Remove um cliente da ArrayList clientes
    public void removeCliente(String cpf) throws Exception {
        Cliente cliente = null;
        
        //Verifica se O cpf é válido
        char[] ch = cpf.toCharArray();
        boolean d = true;
        for (int i = 0; i < ch.length; i++) {
            if (!Character.isDigit(ch[i])) {
                d = false;
                throw new Exception("Digite apenas numeros");
            }
        }
        
        //Varre o ArrayList em busca de clientes
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                cliente = c;
            }
        }
        
        //Se cliente não existe lança exceção, caso exista remove do ArrayList
        if (cliente == null) {
            throw new Exception("Este cliente não esta cadastrado");
        } else {
            clientes.remove(cliente);
        }
    }
    
    //Retorna tabela html com todos os clientes cadastrados
    public String listaClientes() {
        String retorno = "";
        retorno = "<html>"
                + "<table border = 2 bgcolor='graylight'>"
                + "<tr>"
                + "<th>CPF</th>"
                + "<th>Nome</th>"
                + "</tr>";
        for (Cliente c : clientes) {

            retorno += "<tr>"
                    + "<td>" + c.getCpf() + "</td>"
                    + "<td> " + c.getNome() + "</td>"
                    + "</tr>";

        }
        retorno += "</table></html>";
        return retorno;
    }
    
    //Verifica se um cliente existe. Usado por outros metodos.
    public boolean existeCliente(String cpf) {
        boolean existe = false;
        for (Cliente c : clientes) {
            if (c.getCpf().equalsIgnoreCase(cpf)) {
                existe = true;
            }
        }
        return existe;
    }
    
    //Metodo para recuperação de um unico cliente, usado para fazer vendas por exemplo
    public Cliente getObjCliente(String cpf) {
        for (Cliente c : clientes) {
            if (cpf.equalsIgnoreCase(c.getCpf())) {
                objCliente = c;
            }
        }
        return objCliente;
    }
}
