package mata55.view;

import mata55.controller.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// necessário para setRoundingMode(RoundingMode.HALF_UP);
import java.math.RoundingMode;
import javax.swing.*;
// necessário para NumberFormat 
import java.text.*;

/**
 * Formulário de cadastro de compras.
 * @author Gabriel Gonçalves Faria Costa
 */
class CompraCAD extends JFrame implements ActionListener{


	private static final long serialVersionUID = -332727085836221309L;
	private JTextField descricao;
	/*
	 * Campos de quantidade e preço unitário precisam ser formatados 
	 * para aceitar doubles.
	 */
	private JFormattedTextField qtd;
	private JFormattedTextField prc;
	private int prod;
	private int forn;
	private NumberFormat format;
	

	public CompraCAD(int prod, int forn){
		this.prod = prod;
		this.forn = forn;
		
		// Define formato decimal para JFormattedTextField.
		format = DecimalFormat.getInstance();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		format.setRoundingMode(RoundingMode.HALF_UP);
		
		setSize(250,240);
		setResizable(false);
		setTitle("Cadastro de Compras");
		getContentPane().setLayout(null);

		JLabel label_1 = new JLabel("Descrição:");
		label_1.setBounds(16, 75, 66, 14);
		getContentPane().add(label_1);

		descricao = new JTextField();
		descricao.setColumns(10);
		descricao.setBounds(86, 72, 120, 20);
		getContentPane().add(descricao);

		JLabel lblNewLabel = new JLabel("Quantidade:");
		lblNewLabel.setBounds(16, 100, 70, 14);
		getContentPane().add(lblNewLabel);

		qtd = new JFormattedTextField(format);
		qtd.setBounds(86, 97, 120, 20);
		qtd.setColumns(10);
		qtd.setValue(new Double(0.0));
		getContentPane().add(qtd);

		JLabel label_3 = new JLabel("Preço unit.:");
		label_3.setBounds(16, 126, 66, 14);
		getContentPane().add(label_3);

		prc = new JFormattedTextField(format);
		prc.setColumns(10);
		prc.setValue(new Double(0.0));
		prc.setBounds(86, 123, 120, 20);
		getContentPane().add(prc);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(67, 162, 99, 23);
		getContentPane().add(btnCadastrar);
		btnCadastrar.addActionListener(this);

		JLabel lblCdigoDoProduto = new JLabel("Código do Produto: "+prod);
		lblCdigoDoProduto.setBounds(16, 11, 150, 14);
		getContentPane().add(lblCdigoDoProduto);

		JLabel label = new JLabel("Código do Fornecedor: "+forn);
		label.setBounds(16, 36, 150, 14);
		getContentPane().add(label);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e){
		String dc = descricao.getText().trim();
		double qt = ((Number)qtd.getValue()).doubleValue();
		double pc = ((Number)prc.getValue()).doubleValue();
		if(qt > 0 && dc.length() > 0 && pc > 0){
			Controlador controlcad = new Controlador();
			controlcad.carregaProdutos();
			controlcad.carregaPessoas();
			if(controlcad.cadCompra(prod,forn,qt,dc,pc))
				JOptionPane.showMessageDialog(null,"Compra registrada.");
			else JOptionPane.showMessageDialog(null,"Compra não registrada.");
			qtd.setValue(new Double(0.0));
			descricao.setText("");
			prc.setValue(new Double(0.0));
			controlcad.gravaProdutos();
		}
	}
}
