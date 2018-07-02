/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Control.*;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author junior
 */
public class LimiteEditarMercadoria extends JPanel
{
     private JLabel labelTitulo = new JLabel("Editar de Mercadoria");
    private JLabel labelCodigo = new JLabel("Codigo:      ");
    private JLabel quantidadLabel = new JLabel("Quantidade:      ");
    private JTextField fieldCodigo = new JTextField(30);
    private JTextField quantidadeField = new JTextField(30);
    private JButton buttonEditar = new JButton("Editar");
    private JLabel area = new JLabel("");
    
    
    public LimiteEditarMercadoria(Controle objControle)
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
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(quantidadLabel,constraints);
        
        constraints.gridx = 3;
        constraints.gridy = 2;
        this.add(quantidadeField,constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        this.add(buttonEditar, constraints);

        constraints.gridx = 1;
        constraints.gridy = 10;
        constraints.gridwidth = 5;
        constraints.gridheight = 5;
        this.add(area, constraints);

        buttonEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cod = Integer.parseInt(fieldCodigo.getText());
                int qtd = Integer.parseInt(quantidadeField.getText());
               area.setText(objControle.getObjCtrMercadoria().editaMercadoria(cod, qtd));
            }
        });
        
        
        
        
        
    }
    
}
