package View;

import Control.Controle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LimiteCadastroMercadoria extends JPanel
{

    //Criando os labels 
    private JLabel labelTitulo = new JLabel("Cadastro de Mercadorias");
    private JLabel labelCodigo = new JLabel("Codigo:             ");
    private JLabel labelDescricao = new JLabel("Descricao:          ");
    private JLabel labelValorCompra = new JLabel("Custo:              ");
    private JLabel labelValorVenda = new JLabel("Valor de Venda:     ");
    private JLabel labelQuantidade = new JLabel("Quantidade:         ");
// criando os Textfields
    private JTextField fieldCodigo = new JTextField(40);
    private JTextField fieldDescricao = new JTextField(40);
    private JTextField fieldValorCompra = new JTextField(40);
    private JTextField fieldValorVenda = new JTextField(40);
    private JTextField fieldQuantidade = new JTextField(40);
// criando os botoes
    private JButton buttonSalvar = new JButton("Salvar");
    private JButton buttonCancelar = new JButton("Cancelar");
// construtor da classe
    public LimiteCadastroMercadoria(Controle objControle)
    {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.setSize(800, 600);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
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
        this.add(labelDescricao, constraints);

        constraints.gridx = 3;
        constraints.gridy = 2;
        this.add(fieldDescricao, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(labelValorCompra, constraints);

        constraints.gridx = 3;
        constraints.gridy = 3;
        this.add(fieldValorCompra, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        this.add(labelValorVenda, constraints);

        constraints.gridx = 3;
        constraints.gridx = 4;
        this.add(fieldValorVenda, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        this.add(labelQuantidade, constraints);

        constraints.gridx = 3;
        constraints.gridy = 5;
        this.add(fieldQuantidade, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        this.add(buttonCancelar, constraints);

        constraints.gridx = 3;
        constraints.gridy = 6;
        this.add(buttonSalvar, constraints);
// botao de salvar a mercadoria
        buttonSalvar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (fieldCodigo.getText() == null || fieldCodigo.getText().equalsIgnoreCase("") || fieldDescricao.getText() == null
                        || fieldDescricao.getText().equalsIgnoreCase("") || fieldValorCompra.getText() == null
                        || fieldValorCompra.getText().equalsIgnoreCase("") || fieldValorVenda.getText() == null
                        || fieldValorVenda.getText().equalsIgnoreCase("") || fieldQuantidade.getText() == null
                        || fieldQuantidade.getText().equalsIgnoreCase(""))
                {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!");
                } else
                {
                    try
                    {
                        int cod = Integer.parseInt(fieldCodigo.getText());
                        float compra = Float.parseFloat(fieldValorCompra.getText());
                        float venda = Float.parseFloat(fieldValorVenda.getText());
                        int quant = Integer.parseInt(fieldQuantidade.getText());
                        objControle.getObjCtrMercadoria().cadastraMercadoria(cod, fieldDescricao.getText(), compra, venda, quant);
                    } catch (Exception e1)
                    {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }
                }
                fieldCodigo.setText("");
                fieldDescricao.setText("");
                fieldValorCompra.setText("");
                fieldValorVenda.setText("");
                fieldQuantidade.setText("");
            }
        });

        buttonCancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                fieldCodigo.setText("");
                fieldDescricao.setText("");
                fieldValorCompra.setText("");
                fieldValorVenda.setText("");
                fieldQuantidade.setText("");
            }
        });

    }
}
