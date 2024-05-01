package mata55.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import mata55.controller.Controlador;

import javax.swing.JButton;

/**
 * Formulário de cadastro de fornecedores.
 * @author Gabriel Gonçalves Faria Costa
 */
class PessoaCAD extends JFrame implements ActionListener{

	private static final long serialVersionUID = 6597093607425473401L;
	private JTextField nome;
	private JTextField sobrenome;
	private JTextField telefone;
	private JTextField email;
	private JTextField razao;
	private JTextField logradouro;
	private JTextField numero;
	private JTextField bairro;
	private JTextField cidade;
	private JTextField estado;
	private JTextField pais;
	private JTextField cep;

	public PessoaCAD(){
		setSize(430,340);
		setResizable(false);
		setTitle("Cadastro de Fornecedores");
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(47, 14, 46, 14);
		getContentPane().add(lblNewLabel);

		nome = new JTextField();
		nome.setBounds(93, 11, 120, 20);
		nome.setColumns(10);
		getContentPane().add(nome);

		JLabel label = new JLabel("Sobrenome:");
		label.setBounds(218, 14, 72, 14);
		getContentPane().add(label);

		sobrenome = new JTextField();
		sobrenome.setColumns(10);
		sobrenome.setBounds(293, 11, 120, 20);
		getContentPane().add(sobrenome);

		JLabel label_1 = new JLabel("Telefone:");
		label_1.setBounds(32, 39, 60, 14);
		getContentPane().add(label_1);

		telefone = new JTextField();
		telefone.setColumns(10);
		telefone.setBounds(93, 36, 120, 20);
		getContentPane().add(telefone);

		JLabel label_2 = new JLabel("E-mail:");
		label_2.setBounds(244, 39, 46, 14);
		getContentPane().add(label_2);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(293, 39, 120, 20);
		getContentPane().add(email);

		JLabel label_3 = new JLabel("Razão Social:");
		label_3.setBounds(14, 64, 78, 14);
		getContentPane().add(label_3);

		razao = new JTextField();
		razao.setText("---");
		razao.setColumns(10);
		razao.setBounds(93, 61, 120, 20);
		getContentPane().add(razao);

		JLabel label_4 = new JLabel("Endereço:");
		label_4.setBounds(7, 102, 63, 14);
		getContentPane().add(label_4);

		JLabel label_5 = new JLabel("Logradouro:");
		label_5.setBounds(19, 123, 73, 14);
		getContentPane().add(label_5);

		logradouro = new JTextField();
		logradouro.setColumns(10);
		logradouro.setBounds(93, 120, 120, 20);
		getContentPane().add(logradouro);

		JLabel label_6 = new JLabel("Número:");
		label_6.setBounds(37, 148, 55, 14);
		getContentPane().add(label_6);

		numero = new JTextField();
		numero.setColumns(10);
		numero.setBounds(93, 145, 120, 20);
		getContentPane().add(numero);

		JLabel label_7 = new JLabel("Bairro:");
		label_7.setBounds(46, 172, 46, 14);
		getContentPane().add(label_7);

		bairro = new JTextField();
		bairro.setColumns(10);
		bairro.setBounds(93, 169, 120, 20);
		getContentPane().add(bairro);

		JLabel label_8 = new JLabel("Cidade:");
		label_8.setBounds(239, 123, 51, 14);
		getContentPane().add(label_8);

		cidade = new JTextField();
		cidade.setColumns(10);
		cidade.setBounds(293, 120, 120, 20);
		getContentPane().add(cidade);

		JLabel label_9 = new JLabel("Estado:");
		label_9.setBounds(239, 148, 51, 14);
		getContentPane().add(label_9);

		estado = new JTextField();
		estado.setColumns(10);
		estado.setBounds(293, 145, 120, 20);
		getContentPane().add(estado);

		JLabel label_10 = new JLabel("País:");
		label_10.setBounds(253, 172, 37, 14);
		getContentPane().add(label_10);

		pais = new JTextField();
		pais.setColumns(10);
		pais.setBounds(293, 169, 120, 20);
		getContentPane().add(pais);

		JLabel label_11 = new JLabel("CEP:");
		label_11.setBounds(253, 197, 37, 14);
		getContentPane().add(label_11);

		cep = new JTextField();
		cep.setColumns(10);
		cep.setBounds(293, 194, 120, 20);
		getContentPane().add(cep);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(140, 228, 99, 23);
		getContentPane().add(btnCadastrar);
		btnCadastrar.addActionListener(this);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e){
		String nm = nome.getText().trim();
		String sm = sobrenome.getText().trim();
		String tl = telefone.getText().trim();
		String em = email.getText().trim();
		String rz = razao.getText().trim();
		String lg = logradouro.getText().trim();
		String no = numero.getText().trim();
		String br = bairro.getText().trim();
		String cd = cidade.getText().trim();
		String uf = estado.getText().trim();
		String ps = pais.getText().trim();
		String cp = cep.getText().trim();
        if(nm.length() > 0 && sm.length() > 0 && tl.length() > 0 && em.length() > 0 && rz.length() > 0&& lg.length() > 0 && no.length() > 0 && br.length() > 0 && cd.length() > 0 && uf.length() > 0 && ps.length() > 0 && cp.length() > 0){
        	Controlador controlcad = new Controlador();
        	controlcad.carregaPessoas();
            if(controlcad.cadPessoa(nm,sm,tl,em,rz,lg,no,br,cd,uf,ps,cp))
                JOptionPane.showMessageDialog(null,"Fornecedor registrado.");
            else JOptionPane.showMessageDialog(null,"Fornecedor não registrado.");
            nome.setText("");
        	sobrenome.setText("");
        	telefone.setText("");
        	email.setText("");
        	razao.setText("---");
        	logradouro.setText("");
        	numero.setText("");
        	bairro.setText("");
        	cidade.setText("");
        	estado.setText("");
        	pais.setText("");
        	cep.setText("");
            controlcad.gravaPessoas();
        } 
	}
}
