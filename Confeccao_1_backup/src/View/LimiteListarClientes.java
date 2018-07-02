package View;

import Control.Controle;
import Control.ControleMercadoria;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LimiteListarClientes extends JPanel {


    JPanel p1, painel, centro;
    JTextArea exibicao;
    JLabel listaJL, lb;
    Controle ctr;

    public LimiteListarClientes(Controle objControle) {
        ctr = objControle;
        //criando o Jlabel para titulo
        listaJL = new JLabel("Lista de clientes");

        // Criando um label para receber a tabela com os dados
        lb = new JLabel();
        
        //Aqui são definidos um JPanel em flowlayout, outro em boxlayout e outro em border layout
        p1 = new JPanel(new FlowLayout());
        centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        painel = new JPanel(new BorderLayout());
        //centro definine borda lateral 
        centro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //A lista vinda de ControleCliente é adiciona no JLabel lb que é adicionado no JPanel centro
        centro.add(lb);
        //em p1 é adicionado o Titulo
        p1.add(listaJL);
        
        //definição de visual de cor para tudo
        p1.setBackground(Color.WHITE);
        painel.setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);
        lb.setBackground(Color.WHITE);
        centro.setBackground(Color.WHITE);
        
        //painel é o JPanel principal que contem os outros JPanels e a posição deles
        //Adiciona p1 com tituulo
        painel.add(p1, BorderLayout.NORTH);
        //adiciona centro com a tabela de lista de clientes
        painel.add(centro, BorderLayout.CENTER);
        
        //A classe principal recebe o painel principal. A classe principal tabem poderia receber diretamente os outros paineis
        this.add(painel);
        this.setSize(800, 600);
        this.setVisible(true);
        
       
    }
        public void mostrarClientes(){
            /*esse metodo serve para quando selecionado o menu de listar 
            clientes o actionListerner do menu possa chama-lo da classe limite principal*/
            lb.setText(ctr.getObjCtrCliente().listaClientes());
        }

}
