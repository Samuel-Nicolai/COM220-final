/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.Controle;
import Model.Mercadoria;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author junior
 */
public class LimiteRemoverMercadoria extends JPanel
{
     Mercadoria auxiliar;
     private JLabel labelTitulo = new JLabel("Remover mercadoria");
    private JLabel labelCodigo = new JLabel("Insira o código da mercadoria que deseja remover");;
    private JTextField fieldCodigo = new JTextField(10);
    private JPanel painel, p1, p2;
    private JButton      removeJB = new JButton("Remover");
   

public LimiteRemoverMercadoria(Controle objControle)
{
   
    this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.setSize(800, 600);
        this.setBorder(new EmptyBorder(5,5,5,5));
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,5,5,5);
        constraints.fill = GridBagConstraints.BOTH;
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        this.add(labelTitulo, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(labelCodigo, constraints);

         constraints.gridx = 3;
        constraints.gridy = 1;
        this.add(fieldCodigo, constraints);
        
        constraints.gridx = 2;
        constraints.gridy = 3;
        this.add(removeJB, constraints);
        
        
    removeJB.addActionListener(new ActionListener()
    {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String cod;
        int codigo;
        cod = fieldCodigo.getText();
        if ( cod.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Você deve informar um codigo valido");
        }
        else
            try
            {
                codigo = Integer.parseInt(cod);
                int result = JOptionPane.showConfirmDialog(null,"Deseja Remover ? ","Excluir",JOptionPane.YES_NO_CANCEL_OPTION);
                if(result == JOptionPane.YES_OPTION)
                {
                   auxiliar =  objControle.getObjCtrMercadoria().consultaMercadorias(codigo);
                    objControle.getObjCtrMercadoria().removeMercadoria(auxiliar);
                }
                
            } catch (Exception exec)
            {
                JOptionPane.showMessageDialog(null,exec.getMessage());
            }
            }
        });
        
    }
}




