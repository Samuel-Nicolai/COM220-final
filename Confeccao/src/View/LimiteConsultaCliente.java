package View;

import Control.Controle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class LimiteConsultaCliente extends JPanel {

    private JPanel painel = new JPanel(new BorderLayout());
    private JTextField t1 = new JTextField();
    private JTextArea area = new JTextArea(32, 70);
    private Controle ctr;
    private JButton b1 = new JButton("Consultar");
    private JButton b2 = new JButton("Cancelar");

    public LimiteConsultaCliente(Controle objControle) {
        this.ctr = objControle;

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(Box.createRigidArea(new Dimension(10, 0)));
        p.add(t1);
        p.add(Box.createRigidArea(new Dimension(10, 0)));
        p.add(b1);
        p.add(Box.createRigidArea(new Dimension(10, 0)));
        p.add(b2);
        p.add(Box.createRigidArea(new Dimension(10, 0)));
        p.setBackground(Color.WHITE);

        t1.setPreferredSize(new Dimension(30, 27));
        t1.setMaximumSize(new Dimension(Integer.MAX_VALUE, t1.getPreferredSize().height));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(p);

        area.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Informações:"));
        area.setEditable(false);
        this.add(area);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(t1.getText());
                area.setText(ctr.getObjCtrCliente().consultaCliente(t1.getText()));
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctr.cancela();
            }
        });

        this.setBackground(Color.WHITE);
        this.setSize(800, 600);
    }

}
