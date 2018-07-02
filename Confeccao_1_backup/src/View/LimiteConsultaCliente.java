package View;

import Control.Controle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class LimiteConsultaCliente extends JPanel {

    private JTextField t1 = new JTextField();
    private JTextArea area = new JTextArea(32, 70);
    private Controle ctr;
    private JButton b1 = new JButton("Consultar");
    private JButton b2 = new JButton("Cancelar");
    private JPanel p1, painel, centro;
    private JTextArea exibicao;
    private JLabel titulo, lbDados;

    public LimiteConsultaCliente(Controle objControle) {
        this.ctr = objControle;

        //criando o Jlabel para informação de entrada
        titulo = new JLabel("Entre com CPF do cliente");
        JPanel title = new JPanel();
        //JPanel title contem titulo e entrada de dados por isso foi definido como BoxLayout
        title.setLayout(new BoxLayout(title, BoxLayout.Y_AXIS));
        title.add(Box.createRigidArea(new Dimension(0, 10)));
        title.add(titulo);
        title.add(Box.createRigidArea(new Dimension(0, 10)));
        title.setBackground(Color.WHITE);
        
        //Esse jpanel p guarda os botões e a caixa de texto pra entrada do cpf
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(Box.createRigidArea(new Dimension(10, 0)));
        p.add(t1);
        p.add(Box.createRigidArea(new Dimension(10, 0)));
        p.add(b1);
        p.add(Box.createRigidArea(new Dimension(10, 0)));
        p.add(b2);
        p.add(Box.createRigidArea(new Dimension(10, 0)));
        p.setBackground(Color.WHITE);
        
        //O Jpanel p, que esta organizado em X, é adicionado ao JPanel title que esta organizado em Y
        title.add(p);
        title.add(Box.createRigidArea(new Dimension(0, 10)));
        
        //Definição de tamanho de caixa de texto para não se alterar verticalmente com modificação do tamanho da tela
        t1.setPreferredSize(new Dimension(30, 27));
        t1.setMaximumSize(new Dimension(Integer.MAX_VALUE, t1.getPreferredSize().height));

        // Criando um label para receber a tabela com os dados de ControleCliente
        lbDados = new JLabel();
        //O Jpanel p1 ira guardar o jlabel com a tabela
        p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.add(lbDados);
        p1.setBackground(Color.WHITE);
        
        //Organização da JPanel da classe, aqui foi organizado em BoxLayout para colocar no eixo Y os demais JPanels
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.add(title);
        this.add(p1);
        this.setSize(800, 600);
        this.setVisible(true);
        
        //Funções dos botões
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Modifica o texto do JLabel com a tabela retornada pela classe ControleCliente
                lbDados.setText(ctr.getObjCtrCliente().consultaCliente(t1.getText()));
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctr.cancela();
            }
        });

        this.setBackground(Color.WHITE);
        this.setSize(800, 600);
    }

}
