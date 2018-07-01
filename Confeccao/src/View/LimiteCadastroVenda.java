package View;

import Control.Controle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class LimiteCadastroVenda extends JPanel {

    private ArrayList<String> compras = new ArrayList<>();

    private int quantidadeProd = 0;
    private float valorTotal = 0;

    private JLabel labelTitulo = new JLabel("Realizar Venda");
    private JLabel labelData = new JLabel("Data (dia/mes/ano): ");
    private JLabel labelCliente = new JLabel("Código do Cliente: ");
    private JLabel labelProduto = new JLabel("Código do Produto / Quantidade: ");
    private JLabel labelTotal = new JLabel("Total = " + valorTotal);

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

        buttonAddCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fieldCliente.getText() == null || fieldCliente.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "O Campo de código do cliente deve ser preenchido");
                } else {
                    if (objControle.getObjCtrCliente().existeCliente(fieldCliente.getText())) {
                        texto += objControle.getObjCtrCliente().consultaCliente(fieldCliente.getText());
                        conferencia.setText(texto);
                    } else {
                        JOptionPane.showMessageDialog(null, "O cliente "
                            + fieldCliente.getText() + " não existe.\nPara cadastrá-lo vá em Menu > cliente > Cadastrar");
                    }
                }
            }
        });

        buttonAddProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fieldProduto.getText() == null || fieldProduto.getText().equalsIgnoreCase("")
                        || fieldQuantidade.getText() == null || fieldQuantidade.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Os campos código do produto e quantidade devem ser preenchidos");
                } else {
                    int cod = Integer.parseInt(fieldProduto.getText());
                    int quant = Integer.parseInt(fieldQuantidade.getText());
                    if (objControle.getObjCtrMercadoria().existeMercadoria(cod) == -1){
                        JOptionPane.showMessageDialog(null, "Produto " + cod + " não está cadastrado");
                        fieldProduto.setText("");
                    } else {
                        if (objControle.getObjCtrMercadoria().consultaQuantidade(cod) < quant || fieldQuantidade.getText().equalsIgnoreCase("0")) {
                            JOptionPane.showMessageDialog(null, "Não existe quantidade suficiente em estoque.\n" +
                                    "Estoque " + objControle.getObjCtrMercadoria().consultaQuantidade(cod) +
                                    "\nOu quantidade deve ser maior do que zero.");
                        } else {
                            if (quantidadeProd > 10) {
                                JOptionPane.showMessageDialog(null, "Pode-se inserir no máximo 10 produtos por nota!");
                            } else {
                                compras.add(fieldProduto.getText() + "," + fieldQuantidade.getText());
                                String array = objControle.getObjCtrMercadoria().consultaMercadoriaCompra(Integer.parseInt(fieldProduto.getText()));
                                float preco = Float.parseFloat(array.split(",")[1]);
                                texto += "\nCodigo: " + array.split(",")[0] + "\tQuant.: " + fieldQuantidade.getText() +
                                            "\tPreço: " + preco;
                                conferencia.setText(texto);
                                quantidadeProd++;
                                valorTotal += Integer.parseInt(fieldQuantidade.getText()) * preco;
                                labelTotal.setText("Total = " + valorTotal);
                                fieldProduto.setText("");
                                fieldQuantidade.setText("");
                            }
                        }
                    }
                }
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantidadeProd = 0;
                valorTotal = 0;
                texto = "";
                fieldDia.setText("");
                fieldMes.setText("");
                fieldAno.setText("");
                fieldCliente.setText("");
                fieldProduto.setText("");
                fieldQuantidade.setText("");
                conferencia.setText("");
                labelTotal.setText("Total = 0");
                compras.removeAll(compras);
                compras.clear();
            }
        });

        buttonGerarNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fieldDia.getText() == null || fieldDia.getText().equalsIgnoreCase("")
                        || fieldMes.getText() == null || fieldMes.getText().equalsIgnoreCase("")
                        || fieldAno.getText() == null || fieldAno.getText().equalsIgnoreCase("")
                        || fieldCliente.getText() == null || fieldCliente.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Os campos de data e cliente devem estar preenchidos!");
                } else {
                    if (compras.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Você deve adicionar ao menos um produto válido!");
                    } else {
                        int dia = Integer.parseInt(fieldDia.getText());
                        int mes = Integer.parseInt((fieldMes.getText())) - 1;
                        int ano = Integer.parseInt(fieldAno.getText()) - 1900;
                        Date data = new Date(ano, mes, dia);
                        String cliente = fieldCliente.getText();
                        String nota = objControle.getObjCtrNotaFiscal().geraNota(data, cliente, compras);
                        quantidadeProd = 0;
                        valorTotal = 0;
                        texto = "";
                        fieldDia.setText("");
                        fieldMes.setText("");
                        fieldAno.setText("");
                        fieldCliente.setText("");
                        fieldProduto.setText("");
                        fieldQuantidade.setText("");
                        conferencia.setText("");
                        labelTotal.setText("Total = 0");
                        compras.removeAll(compras);
                        compras.clear();
                        mostraNota(nota);
                    }
                }
            }
        });
    }

    public void mostraNota(String nota) {
        JFrame notaFiscal = new JFrame("Nota fiscal");
        JTextArea area = new JTextArea(nota);
        notaFiscal.add(area);
        notaFiscal.setSize(500,500);
        notaFiscal.setLocationRelativeTo(null);
        notaFiscal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        notaFiscal.setVisible(true);
    }
}
