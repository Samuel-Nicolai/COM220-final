package View;

import Control.Controle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LimiteConsultaCliente extends JPanel {

    private JLabel labelTitulo = new JLabel("Consulta de clientes");
    private JLabel labelCpf = new JLabel("CPF:      ");
    private JTextField fieldCpf = new JTextField(30);
    private JButton buttonConsultar = new JButton("Consultar");
    private JTextArea area = new JTextArea(30, 30);

    public LimiteConsultaCliente(Controle objControle) {
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
        this.add(labelCpf, constraints);

        constraints.gridx = 3;
        constraints.gridy = 1;
        this.add(fieldCpf, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(buttonConsultar, constraints);

        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 10;
        this.add(area, constraints);

        buttonConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setText(objControle.getObjCtrCliente().consultaCliente(fieldCpf.getText()));
            }
        });

    }
}
