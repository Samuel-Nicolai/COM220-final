package View;

import Control.Controle;
import Model.Cliente;
import Model.Mercadoria;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LimiteCadastroVenda extends JPanel {

    private Cliente objCliente;
    private Mercadoria objMercadoria;
    private ArrayList<Mercadoria> compras = new ArrayList<>();

    private int quantidadeProd = 0;

    private JLabel labelTitulo = new JLabel("Realizar Venda");
    private JLabel labelData = new JLabel("Data (dia/mes/ano): ");
    private JLabel labelCliente = new JLabel("Código do Cliente: ");
    private JLabel labelProduto = new JLabel("Código do Produto / Quantidade: ");
    private JTextArea labelAlerta = new JTextArea(20, 20);
    private JLabel labelTotal = new JLabel("Total = ");

    private JTextField fieldDia = new JTextField(2);
    private JTextField fieldMes = new JTextField(2);
    private JTextField fieldAno = new JTextField(4);
    private JTextField fieldCliente = new JTextField(30);
    private JTextField fieldProduto = new JTextField(10);
    private JTextField fieldQuantidade = new JTextField(10);
    private JTextArea conferencia = new JTextArea(40, 40);
    private String texto = "";

    private JButton buttonGerarNota = new JButton("Gerar Nota Fiscal");
    private JButton buttonCancelar = new JButton("Cancelar");
    private JButton buttonAddCliente = new JButton("Add");
    private JButton buttonAddProduto = new JButton("Add");

    public LimiteCadastroVenda(Controle objControle) {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.setSize(800, 600);
        this.setBorder(new EmptyBorder(5,5,5,5));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,5,5,5);
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(labelTitulo, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(labelData, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(fieldDia, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        this.add(fieldMes, constraints);

        constraints.gridx = 3;
        constraints.gridy = 1;
        this.add(fieldAno, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 0.003;
        this.add(labelCliente, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(fieldCliente, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        this.add(buttonAddCliente, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(labelProduto, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        this.add(fieldProduto, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        this.add(fieldQuantidade, constraints);

        constraints.gridx = 3;
        constraints.gridy = 3;
        this.add(buttonAddProduto, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        this.add(buttonCancelar, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        this.add(buttonGerarNota, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        constraints.weighty = 0.5;
        this.add(conferencia, constraints);

        constraints.gridx = 2;
        constraints.gridy = 6;
        this.add(labelTotal, constraints);

        constraints.gridx = 2;
        constraints.gridy = 7;
        this.add(labelAlerta, constraints);


        buttonAddCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = "";
                if (fieldCliente.getText() == null || fieldCliente.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Preencha o campo com o CPF do cliente!");
                } else {
                    if(objControle.getObjCtrCliente().existeCliente(fieldCliente.getText())) {
                        texto += "Cliente: \n";
                        texto += objControle.getObjCtrCliente().consultaCliente(fieldCliente.getText()) + "\n";
                        conferencia.setText(texto);
                    } else {
                        str = fieldCliente.getText() + " não está cadastrado.\n" +
                                "Para proceder com o cadastro vá em: \nMenu > Cliente > Cadastrar.";
                        labelAlerta.setText(str);
                        fieldCliente.setText("");
                    }
                }
            }
        });

        buttonAddProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fieldProduto.getText() == null || fieldProduto.getText().equalsIgnoreCase("") ||
                        fieldQuantidade.getText() == null || fieldQuantidade.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Os Campos Codigo do produto e quantidade devem ser preenchidos");
                    if (objControle.getObjCtrMercadoria().existeMercadoria(Integer.parseInt(fieldProduto.getText())) > 0) {
                        if (objControle.getObjCtrMercadoria().consultaQuantidade(Integer.parseInt(fieldProduto.getText())) >
                                Integer.parseInt(fieldQuantidade.getText())) {
                            texto += "Produto: " + fieldProduto.getText() + "Quantidade: " + fieldQuantidade.getText() + "\n";
                            compras.add(objControle.getObjCtrMercadoria().getObjMercadoria(Integer.parseInt(fieldProduto.getText())));
                            conferencia.setText(texto);
                            fieldCliente.setText("");
                            fieldQuantidade.setText("");
                        } else {
                            labelAlerta.setText("Quantidade insuficiente.\n" +
                                    "Restam " + objControle.getObjCtrMercadoria().consultaQuantidade(Integer.parseInt(fieldProduto.getText())) + " unidades");
                        }
                    } else {
                        labelAlerta.setText( "O produto: " + fieldProduto.getText() + " não existe!");
                    }
                    fieldCliente.setText("");
                    fieldQuantidade.setText("");
                }
            }
        });
    }

}
