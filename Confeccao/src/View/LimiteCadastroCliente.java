package View;

import Control.Controle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LimiteCadastroCliente extends JPanel {

    private JLabel labelTitulo = new JLabel("Cadastro de Clientes");
    private JLabel labelCpf = new JLabel("CPF:      ");
    private JLabel labelNome = new JLabel("Nome:     ");
    private JLabel labelEndereco = new JLabel("Endereço:  ");
    private JLabel labelEmail = new JLabel("E-mail:    ");

    private JTextField fieldCpf = new JTextField(40);
    private JTextField fieldNome = new JTextField(40);
    private JTextField fieldEndereco = new JTextField(40);
    private JTextField fieldEmail = new JTextField(40);

    private JButton buttonSalvar = new JButton("Salvar");
    private JButton buttonCancelar = new JButton("Cancelar");

    public LimiteCadastroCliente(Controle objControle) {
        JPanel p = new JPanel();
        
        p.setLayout(new GridBagLayout());
        p.setBackground(Color.WHITE);
       
        p.setBorder(new EmptyBorder(5, 5, 5, 5));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        p.add(labelTitulo, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        p.add(labelCpf, constraints);

        constraints.gridx = 3;
        constraints.gridy = 1;
        p.add(fieldCpf, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        p.add(labelNome, constraints);

        constraints.gridx = 3;
        constraints.gridy = 2;
        p.add(fieldNome, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        p.add(labelEndereco, constraints);

        constraints.gridx = 3;
        constraints.gridy = 3;
        p.add(fieldEndereco, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        p.add(labelEmail, constraints);

        constraints.gridx = 3;
        constraints.gridx = 4;
        p.add(fieldEmail, constraints);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.setBackground(Color.WHITE);
        this.add(p);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
        p2.add(buttonSalvar);
        p2.add(Box.createRigidArea(new Dimension(10, 0)));
        p2.add(buttonCancelar);
        p2.add(Box.createRigidArea(new Dimension(40, 0)));
        p2.setBorder(new EmptyBorder(50,50,50,50));
        p2.setBackground(Color.WHITE);
        
        this.add(p2);
        
        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fieldCpf.getText() == null || fieldCpf.getText().equalsIgnoreCase("") || fieldNome.getText() == null
                        || fieldNome.getText().equalsIgnoreCase("") || fieldEndereco.getText() == null
                        || fieldEndereco.getText().equalsIgnoreCase("") || fieldEmail.getText() == null
                        || fieldEmail.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!");
                } else {
                  
                        try {
                            objControle.getObjCtrCliente().cadastraCliente(fieldCpf.getText(), fieldNome.getText(), fieldEndereco.getText(), fieldEmail.getText());
                            JOptionPane.showMessageDialog(null, "Cliente Cadastrado!");

                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null, e1.getMessage());
                        }
                    
                }
                fieldCpf.setText("");
                fieldNome.setText("");
                fieldEndereco.setText("");
                fieldEmail.setText("");
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldCpf.setText("");
                fieldNome.setText("");
                fieldEndereco.setText("");
                fieldEmail.setText("");
                objControle.cancela();
            }
        });

    }
}
