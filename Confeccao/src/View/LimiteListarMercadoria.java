/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.Controle;
import Control.ControleMercadoria;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author junior
 */
public class LimiteListarMercadoria extends JPanel
{
    
    JButton listarMercJB;
    JPanel p1, painel, centro;
    JTextArea exibicao;
    ControleMercadoria objControle;
    JLabel listaJL;

    public LimiteListarMercadoria(Controle objControle)
    {
        //criando o Jlabel
        listaJL = new JLabel("Listar estoque com todas as mercadorias");

        // criando o Botao e listener 
        listarMercJB = new JButton("Gerar lista de mercadorias");
        
        // Criando um label para receber a tabela com os dados
        JLabel lb = new JLabel();

        p1 = new JPanel(new FlowLayout());
        centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        painel = new JPanel(new BorderLayout());

        centro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centro.add(lb);
        
        p1.add(listaJL);
        p1.add(listarMercJB);

        painel.add(p1,BorderLayout.NORTH);
        painel.add(centro,BorderLayout.CENTER);
       
        this.add(painel);
        this.setSize(800,600);
        this.setVisible(true);
        
 
listarMercJB.addActionListener(new ActionListener()
{
            @Override
            public void actionPerformed(ActionEvent e)
            {
                lb.setText(objControle.getObjCtrMercadoria().listaMercadoria());
            }
        });
}
    
}
