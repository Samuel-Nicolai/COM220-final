package View;

import Control.Controle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Limite extends JFrame {

    // Todos os componentes utilizados no frame principal
    private JMenuBar menu;
    private JMenu menuCliente;
    private JMenu menuMercadoria;
    private JMenu menuVenda;
    private JMenuItem itemClienteCadastro;
    private JMenuItem itemClienteConsulta;
    private JMenuItem itemMercadoriaCadastro;
    private JMenuItem itemMercadoriaConsulta;
    private JMenuItem itemVendaCadastrar;
    private JMenuItem itemFaturamentoProduto;
    private JMenuItem itemFaturamentoCliente;
    private JMenuItem itemFaturamentoPeriodo;
    private JMenuItem itemLucroPeriodo;
    private JMenuItem itemVendaClientePeriodo;
    private JMenuItem itemRankingProdutos;
    private JButton finalizar;

    // Todos os panels utilizados
    private JPanel cardPanel;
    private Inicial panelInicial;
    private LimiteCadastroCliente formCadCliente;
    private LimiteConsultaCliente formConCliente;
    private LimiteCadastroMercadoria formCadMercadoria;
    private LimiteConsultaMercadoria formConMercadoria;
    private LimiteCadastroVenda formCadVenda;
    private LimiteFaturamentoProduto faturamentoProduto;
    private LimiteFaturamentoCliente faturamentoCliente;
    private LimiteFaturamentoPeriodo faturamentoPeriodo;
    private LimiteLucroPeriodo lucroPeriodo;
    private LimiteVendaClientePeriodo vendaClientePeriodo;
    private LimiteProdutosMaisVendidos rankingProdutosMaisVendidos;

    public Limite(Controle objControle) {   // Construtor recebe um controle principal
        super("Confecção");
        this.setSize(800, 600);

        // Montando o esquema de paineis com CardLayout
        this.cardPanel = new JPanel(new CardLayout());
        this.getContentPane().add(cardPanel, BorderLayout.CENTER);

        this.panelInicial = new Inicial();
        this.cardPanel.add(panelInicial, "inicial");
        this.formCadCliente = new LimiteCadastroCliente(objControle);
        this.cardPanel.add(formCadCliente, "formCadCliente");
        this.formConCliente = new LimiteConsultaCliente(objControle);
        this.cardPanel.add(formConCliente, "formConCliente");
        this.formCadMercadoria = new LimiteCadastroMercadoria(objControle);
        this.cardPanel.add(formCadMercadoria, "formCadMercadoria");
        this.formConMercadoria = new LimiteConsultaMercadoria(objControle);
        this.cardPanel.add(formConMercadoria, "formConMercadoria");
        this.formCadVenda = new LimiteCadastroVenda(objControle);
        this.cardPanel.add(formCadVenda, "formCadVenda");
        this.faturamentoProduto = new LimiteFaturamentoProduto(objControle);
        this.cardPanel.add(faturamentoProduto, "faturamentoProduto");
        this.faturamentoCliente = new LimiteFaturamentoCliente(objControle);
        this.cardPanel.add(faturamentoCliente, "faturamentoCliente");
        this.faturamentoPeriodo = new LimiteFaturamentoPeriodo(objControle);
        this.cardPanel.add(faturamentoPeriodo, "faturamentoPeriodo");
        this.lucroPeriodo = new LimiteLucroPeriodo(objControle);
        this.cardPanel.add(lucroPeriodo, "lucroPeriodo");
        this.vendaClientePeriodo = new LimiteVendaClientePeriodo(objControle);
        this.cardPanel.add(vendaClientePeriodo, "vendaClientePeriodo");
        this.rankingProdutosMaisVendidos = new LimiteProdutosMaisVendidos(objControle);
        this.cardPanel.add(rankingProdutosMaisVendidos, "rankingProdutosMaisVendidos");

        // Montando o JMenuBar
        this.menu = new JMenuBar();
        this.menu.setLayout(new GridBagLayout());
        GridBagConstraints direita = new GridBagConstraints();
        direita.anchor = GridBagConstraints.EAST;
        direita.weightx = 1.0;
        GridBagConstraints esquerda = new GridBagConstraints();
        esquerda.anchor = GridBagConstraints.WEST;

        this.menuCliente = new JMenu("Cliente");
        this.itemClienteCadastro = new JMenuItem("Cadastrar");
        this.itemClienteConsulta = new JMenuItem("Consultar");
        this.menuCliente.add(itemClienteCadastro);
        this.menuCliente.add(itemClienteConsulta);
        this.menu.add(menuCliente, esquerda);

        this.menuMercadoria = new JMenu("Mercadoria");
        this.itemMercadoriaCadastro = new JMenuItem("Cadastrar");
        this.itemMercadoriaConsulta = new JMenuItem("Consultar");
        this.menuMercadoria.add(itemMercadoriaCadastro);
        this.menuMercadoria.add(itemMercadoriaConsulta);
        this.menu.add(menuMercadoria, esquerda);

        this.menuVenda = new JMenu("Venda");
        this.itemVendaCadastrar = new JMenuItem("Realizar");
        this.itemFaturamentoProduto = new JMenuItem("Consultar fat. do Produto");
        this.itemFaturamentoCliente = new JMenuItem("Consultar fat. sobre Cliente");
        this.itemFaturamentoPeriodo = new JMenuItem("Consultar fat. por Período");
        this.itemLucroPeriodo = new JMenuItem("Consultar lucro por Período");
        this.itemVendaClientePeriodo = new JMenuItem("Consultar vendas p/ cliente periodo");
        this.itemRankingProdutos = new JMenuItem("Consultar Ranking de produtos");
        this.menuVenda.add(itemVendaCadastrar);
        this.menuVenda.add(itemFaturamentoProduto);
        this.menuVenda.add(itemFaturamentoCliente);
        this.menuVenda.add(itemFaturamentoPeriodo);
        this.menuVenda.add(itemLucroPeriodo);
        this.menuVenda.add(itemVendaClientePeriodo);
        this.menuVenda.add(itemRankingProdutos);
        this.menu.add(menuVenda, esquerda);

        this.finalizar = new JButton("Finalizar");
        this.menu.add(finalizar, direita);
        this.setJMenuBar(this.menu);

        // Ações do Menu
        this.finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objControle.finaliza();
            }
        });

        this.itemClienteCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout formCadCliente = (CardLayout) (cardPanel.getLayout());
                formCadCliente.show(cardPanel, "formCadCliente");
            }
        });

        this.itemClienteConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout formConCliente = (CardLayout) (cardPanel.getLayout());
                formConCliente.show(cardPanel, "formConCliente");
            }
        });

        this.itemMercadoriaCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout formCadMercadoria = (CardLayout) (cardPanel.getLayout());
                formCadMercadoria.show(cardPanel, "formCadMercadoria");
            }
        });

        this.itemMercadoriaConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout formConMercadoria = (CardLayout) (cardPanel.getLayout());
                formConMercadoria.show(cardPanel, "formConMercadoria");
            }
        });

        this.itemVendaCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout formCadVenda = (CardLayout) (cardPanel.getLayout());
                formCadVenda.show(cardPanel, "formCadVenda");
            }
        });

        this.itemFaturamentoProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout faturamentoProduto = (CardLayout) (cardPanel.getLayout());
                faturamentoProduto.show(cardPanel, "faturamentoProduto");
            }
        });

        this.itemFaturamentoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout faturamentoCliente = (CardLayout) (cardPanel.getLayout());
                faturamentoCliente.show(cardPanel, "faturamentoCliente");
            }
        });

        this.itemFaturamentoPeriodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout faturamentoPeriodo = (CardLayout) (cardPanel.getLayout());
                faturamentoPeriodo.show(cardPanel, "faturamentoPeriodo");
            }
        });

        this.itemLucroPeriodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout lucroPeriodo = (CardLayout) (cardPanel.getLayout());
                lucroPeriodo.show(cardPanel, "lucroPeriodo");
            }
        });

        this.itemVendaClientePeriodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout vendaClientePeriodo = (CardLayout) (cardPanel.getLayout());
                vendaClientePeriodo.show(cardPanel, "vendaClientePeriodo");
            }
        });

        this.itemRankingProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout rankingProdutosMaisVendidos = (CardLayout) (cardPanel.getLayout());
                rankingProdutosMaisVendidos.show(cardPanel, "rankingProdutosMaisVendidos");
            }
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
}