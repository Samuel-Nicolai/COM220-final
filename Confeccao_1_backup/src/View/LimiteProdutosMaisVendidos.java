package View;

import Control.Controle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LimiteProdutosMaisVendidos extends JPanel {

    private JTextArea area = new JTextArea(60, 60);
    private JScrollPane sp = new JScrollPane(area);
    private JButton ranking = new JButton("Mostrar Ranking");

    public LimiteProdutosMaisVendidos(Controle objControle) {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.setSize(800, 600);
        this.setBorder(new EmptyBorder(5,5,5,5));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,5,5,5);
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(ranking, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 0.9;
        constraints.weighty = 0.9;
        this.add(area, constraints);


        ranking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strArea = objControle.getObjCtrNotaFiscal().topDezProdutosVendidos();
                area.setText(strArea);
            }
        });
    }

}
