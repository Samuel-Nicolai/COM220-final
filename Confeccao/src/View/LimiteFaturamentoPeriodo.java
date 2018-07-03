package View;

import Control.Controle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.regex.*;

public class LimiteFaturamentoPeriodo extends JPanel {

    private Pattern pattern = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");

    private JLabel labelTitulo = new JLabel("Consultar faturamento por período");
    private JLabel labelDataInicio = new JLabel("Início (dd/mm/aaaa): ");
    private JLabel labelDataFim = new JLabel("Fim (dd/mm/aaaa): ");
    private JTextField fieldDataInicio = new JTextField(30);
    private JTextField fieldDataFim = new JTextField(30);
    private JButton buttonConsultar = new JButton("Consultar");
    private JButton buttonCancelar = new JButton("Cancelar");
    private JTextArea area = new JTextArea(30, 30);
    

    public LimiteFaturamentoPeriodo(Controle objControle) {
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
        this.add(labelDataInicio, constraints);

        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        this.add(fieldDataInicio, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(labelDataFim, constraints);

        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        this.add(fieldDataFim, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(buttonCancelar, constraints);

        constraints.gridx = 3;
        constraints.gridy = 3;
        this.add(buttonConsultar, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        this.add(area, constraints);

        buttonConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fieldDataInicio.getText() == null || fieldDataInicio.getText().equalsIgnoreCase("")
                        || fieldDataFim.getText() == null || fieldDataFim.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Os campos de data inicial e final devem ser preenchidos!");
                } else {
                    Matcher padraoInicio = pattern.matcher(fieldDataInicio.getText());
                    Matcher padraoFim = pattern.matcher(fieldDataFim.getText());
                    if (padraoInicio.matches() == false || padraoFim.matches() == false) {
                        JOptionPane.showMessageDialog(null, "O formato da data deve ser dd/mm/aaaa");
                    } else {
                        String inicio = fieldDataInicio.getText();
                        String fim = fieldDataFim.getText();
                        int diaInicio = Integer.parseInt(inicio.split("/")[0]);
                        int mesInicio = Integer.parseInt(inicio.split("/")[1]);
                        int anoInicio = Integer.parseInt(inicio.split("/")[2]);
                        Date dataInicio = new Date(anoInicio-1900, mesInicio-1, diaInicio);
                        int diaFim = Integer.parseInt(fim.split("/")[0]);
                        int mesFim = Integer.parseInt(fim.split("/")[1]);
                        int anoFim = Integer.parseInt(fim.split("/")[2]);
                        Date dataFim = new Date(anoFim-1900, mesFim-1, diaFim);
                        area.setText(objControle.getObjCtrNotaFiscal().faturamentoPeriodo(dataInicio, dataFim));
                    }
                }
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldDataInicio.setText("");
                fieldDataFim.setText("");
                area.setText("");
            }
        });
    }
}
