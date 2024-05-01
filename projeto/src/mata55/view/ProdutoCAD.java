package mata55.view;

import mata55.controller.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * Formulário de cadastro de Produtos.
 * @author Gabriel Gonçalves Faria Costa
 */
class ProdutoCAD extends JFrame implements ActionListener{

	private static final long serialVersionUID = -2451168052787359995L;
	private JTextField nome;
	private JTextField descricao;
	private JTextField medida;

	public ProdutoCAD(){
		this.setSize(250,200);
		this.setResizable(false);
		this.setTitle("Cadastro de Produtos");
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(46, 14, 46, 14);
		getContentPane().add(lblNewLabel);

		nome = new JTextField();
		nome.setBounds(93, 11, 120, 20);
		nome.setColumns(10);
		getContentPane().add(nome);

		JLabel label_1 = new JLabel("Descrição:");
		label_1.setBounds(26, 39, 66, 14);
		getContentPane().add(label_1);

		descricao = new JTextField();
		descricao.setColumns(10);
		descricao.setBounds(93, 36, 120, 20);
		getContentPane().add(descricao);

		JLabel label_3 = new JLabel("Unidade:");
		label_3.setBounds(36, 64, 53, 14);
		getContentPane().add(label_3);

		medida = new JTextField();
		medida.setColumns(10);
		medida.setBounds(93, 61, 120, 20);
		getContentPane().add(medida);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(65, 92, 99, 23);
		getContentPane().add(btnCadastrar);
		btnCadastrar.addActionListener(this);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e){
		String nm = nome.getText().trim();
        String ds = descricao.getText().trim();
        String md = medida.getText().trim();
        if(nm.length() > 0 && ds.length() > 0 && md.length() > 0){
        	Controlador controlcad = new Controlador();
        	controlcad.carregaProdutos();
            if(controlcad.cadProduto(nm,ds,md))
                JOptionPane.showMessageDialog(null,"Produto registrado.");
            else JOptionPane.showMessageDialog(null,"Produto não registrado.");
            nome.setText("");
            descricao.setText("");
            medida.setText("");
            controlcad.gravaProdutos();
        } 
	}
}
