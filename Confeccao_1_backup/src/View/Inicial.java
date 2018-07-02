package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Inicial extends JPanel {

    private JLabel nome = new JLabel("Confecção");
    private ImageIcon icone = new ImageIcon("icon.png");
    private JLabel labelImagem = new JLabel(icone);

    public Inicial() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.setSize(800, 600);
        this.setBorder(new EmptyBorder(5,5,5,5));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,5,5,5);
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.nome.setFont(new Font(this.nome.getFont().getFontName(), this.nome.getFont().getStyle(), 30));
        this.add(nome, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(labelImagem, constraints);
    }
}
