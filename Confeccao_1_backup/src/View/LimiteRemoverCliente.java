/*
 * To change p1 license header, choose License Headers in Project Properties.
 * To change p1 template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.Controle;
import Model.Cliente;
import Model.Mercadoria;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author junior
 */
public class LimiteRemoverCliente extends JPanel {

    Mercadoria auxiliar;
    private JLabel labelTitulo = new JLabel("Remover Cliente");
    private JLabel labelCodigo = new JLabel("Insira o CPF do Cliente");
    private JTextField fieldCodigo = new JTextField(10);
    private JPanel painel, p1, p2;
    private JButton removeJB = new JButton("Remover");
    private JButton cancela = new JButton("Cancelar");
    private Controle ctr;

    public LimiteRemoverCliente(Controle objControle) {
        this.ctr = objControle;
        //um JPanel p1 em gridbaglayout é definido para guardar o formulário de entrada de dados
        JPanel p1 = new JPanel();
        
        //Configuração de visual
        p1.setLayout(new GridBagLayout());
        p1.setBackground(Color.WHITE);
        p1.setSize(200, 200);
        p1.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        //Definição da grade
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        
        //Adicionando os objetos a grade
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        p1.add(labelTitulo, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        p1.add(labelCodigo, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        p1.add(fieldCodigo, constraints);
        
        //O Jpanel principal extendido pela classe é definido em box Layout para separar os botês da grade
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //Separação da borda do topo
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        //cor
        this.setBackground(Color.WHITE);
        //O painel em gridbaglayout é adicionado no jpanel principal no eixo Y
        this.add(p1);
        
        
        //Definiçãode um segundo JPanel p2 para receber os botões
        JPanel p2 = new JPanel();
        //Os botões são organizados no eixo X por BoxLayout
        p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
        p2.add(removeJB);
        p2.add(Box.createRigidArea(new Dimension(10, 0)));//espaço entre botões
        p2.add(cancela);
        p2.setBorder(new EmptyBorder(10, 10, 300, 10));//Espaço entre laterais
        p2.setBackground(Color.WHITE);
        //O painel 2 entra no painel principal da classe
        this.add(p2);
        
        //Funções dos botões
        cancela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objControle.cancela();
            }
        });

        removeJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cod;

                cod = fieldCodigo.getText();
                boolean contem = false;
                ArrayList<Cliente> al;
                al = ctr.getObjCtrCliente().getClientes();
                
                //Verifica se a entrada esta vazia
                if (cod.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Você deve informar um cpf");
                } else {
                    //Mensagem de confirmação 
                    int result = JOptionPane.showConfirmDialog(null, "Deseja Remover ? ", "Excluir", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            //Chama metodo da classe ControleCliente responsavel por remover um cliente
                            ctr.getObjCtrCliente().removeCliente(cod);
                            JOptionPane.showMessageDialog(null, "Cliente Removido");
                        } catch (Exception erro) {
                            //Recebe e informa os error tratados na classe ControleCliente no metodo removeCliente
                            JOptionPane.showMessageDialog(null, erro.getMessage());

                        }
                    } else {
                        //Informação redundante para confirmar que não houve a exclusão do cliente
                        JOptionPane.showMessageDialog(null, "Operação Cancelada");
                    }
                }
            }
        });

    }
}
