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
 * Formulário de cadastro de revendas.
 * @author Gabriel Gonçalves Faria Costa
 */
class RevendaCAD extends JFrame implements ActionListener{

	private static final long serialVersionUID = 8514433644902896797L;
	
	/*
	 * Campos de quantidade e preço unitário precisam ser formatados 
	 * para aceitar doubles.
	 */
	private JFormattedTextField qtd;
	private JFormattedTextField prc;
	private int prod;
	private int comp;
	private NumberFormat format;
	private ButtonGroup grupo;
	private JRadioButton lucro;
	private JRadioButton preco;

	public RevendaCAD(int prod, int comp){
		this.prod = prod;
		this.comp = comp;
		
		// Define formato decimal para JFormattedTextField.
		format = DecimalFormat.getInstance();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		format.setRoundingMode(RoundingMode.HALF_UP);
		
		setSize(250,290);
		setResizable(false);
		setTitle("Cadastro de Revendas");
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Quantidade:");
		lblNewLabel.setBounds(12, 72, 70, 14);
		getContentPane().add(lblNewLabel);

		qtd = new JFormattedTextField(format);
		qtd.setBounds(86, 69, 120, 20);
		qtd.setColumns(10);
		qtd.setValue(new Double(0.0));
		getContentPane().add(qtd);

		prc = new JFormattedTextField(format);
		prc.setColumns(10);
		prc.setValue(new Double(0.0));
		prc.setBounds(46, 139, 120, 20);
		getContentPane().add(prc);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(67, 178, 99, 23);
		getContentPane().add(btnCadastrar);
		btnCadastrar.addActionListener(this);

		JLabel lblCdigoDoProduto = new JLabel("Código do Produto: "+prod);
		lblCdigoDoProduto.setBounds(16, 11, 150, 14);
		getContentPane().add(lblCdigoDoProduto);

		JLabel label = new JLabel("Código da Compra: "+comp);
		label.setBounds(16, 36, 150, 14);
		getContentPane().add(label);
		
		lucro = new JRadioButton("Lucro(%)");
		lucro.setBounds(12, 109, 83, 23);
		preco = new JRadioButton("Preço unit.($)");
		preco.setBounds(97, 109, 105, 23);
		grupo = new ButtonGroup();
		grupo.add(lucro);
		grupo.add(preco);
		getContentPane().add(lucro);
		getContentPane().add(preco);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e){
		// um dos dois radiobuttons devem ser selecionados.
		if(lucro.isSelected() || preco.isSelected()) {
			double qt = ((Number)qtd.getValue()).doubleValue();
			double pc = ((Number)prc.getValue()).doubleValue();
			if(qt > 0 && pc > 0){ //verifica se preço e quantidade são maiores que zero.
				Controlador controlcad = new Controlador();
				controlcad.carregaProdutos();
				/*
				 * Se margem de lucro selecionada então cp = preço unitário da compra.
				 * preço de revenda = preço de compra + porcentagem de lucro do preço de compra
				 */
				if(lucro.isSelected()) {
					double cp =  controlcad.getPrecoCompra(prod,comp);
					pc = cp+cp*(pc/100); 
				}
				if(controlcad.cadVenda(prod,comp,qt,pc))
					JOptionPane.showMessageDialog(null,"Revenda registrada.");
				else JOptionPane.showMessageDialog(null,"Revenda não registrada.");
				qtd.setValue(new Double(0.0));
				prc.setValue(new Double(0.0));
				controlcad.gravaProdutos();
			}
		}
	}
}
