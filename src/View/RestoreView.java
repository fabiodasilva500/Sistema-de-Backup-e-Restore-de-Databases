package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.Timer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;


import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

import Controller.RestoreController;
import Dao.BakupDao;
import Dao.RestoreDao;

import java.awt.Color;

public class RestoreView  extends JFrame  implements RestoreController{

	private JPanel contentPane;
	private JComboBox cbEmail;
	private String selecionado;
	private String database = "";
	private String diretorio = "";
	private JTextField tfDatabaseSelecionada;
	private JTextField tfAcompanhamento;
	private JTextField tfDatabaseAtual;
	private int verificaDiretorioRestoreSimples=0;
	private int verificaDatabaseRestoreSimples=0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestoreView frame = new RestoreView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RestoreView() {
		super("Realização de Restore de Databases");
		gerarFormulario();
	}

	public void gerarFormulario(){
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 500, 800, 600);
		setResizable(false);
		setLocation(100, 80);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.RED);
		separator.setBounds(0, 51, 1041, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.RED);
		separator_1.setBounds(0, 192, 914, 2);
		contentPane.add(separator_1);

		JLabel lblFullrestore = new JLabel("Full restore");
		lblFullrestore.setForeground(Color.BLUE);
		lblFullrestore.setBounds(10, 51, 193, 14);
		contentPane.add(lblFullrestore);


		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.RED);
		separator_2.setBounds(0, 473, 914, 2);
		contentPane.add(separator_2);

		JButton btnDiretorioFullRestore = new JButton("Diret\u00F3rio");
		btnDiretorioFullRestore.setForeground(new Color(0, 0, 0));
		btnDiretorioFullRestore.setBounds(220, 92, 151, 23);
		contentPane.add(btnDiretorioFullRestore);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(323, 512, 89, 23);
		contentPane.add(btnVoltar);

		btnDiretorioFullRestore.setBackground(Color.cyan);
		btnVoltar.setBackground(Color.cyan);

		JLabel lblSelecionaDiretorioDestino = new JLabel("Selecione o Diret\u00F3rio de Destino");
		lblSelecionaDiretorioDestino.setForeground(new Color(0, 0, 0));
		lblSelecionaDiretorioDestino.setBounds(10, 96, 217, 14);
		contentPane.add(lblSelecionaDiretorioDestino);

		JLabel lblrestoreDatabaseEspecifica = new JLabel("Restore espec\u00EDfico");
		lblrestoreDatabaseEspecifica.setForeground(Color.BLUE);
		lblrestoreDatabaseEspecifica.setBounds(10, 192, 193, 14);
		contentPane.add(lblrestoreDatabaseEspecifica);

		JLabel lblDatabase = new JLabel("Selecione o arquivo de Restore desejado");
		lblDatabase.setForeground(new Color(0, 0, 0));
		lblDatabase.setBounds(10, 274, 235, 14);
		contentPane.add(lblDatabase);

		JButton btnDatabaseRestoreSimples = new JButton("Database");
		btnDatabaseRestoreSimples.setForeground(new Color(0, 0, 0));
		btnDatabaseRestoreSimples.setBackground(Color.CYAN);
		btnDatabaseRestoreSimples.setBounds(270, 270, 118, 23);
		contentPane.add(btnDatabaseRestoreSimples);

		JLabel lblDiretorio = new JLabel("Selecione o Diret\u00F3rio Desejado");
		lblDiretorio.setForeground(new Color(0, 0, 0));
		lblDiretorio.setBounds(10, 352, 217, 14);
		contentPane.add(lblDiretorio);

		JButton btnDiretorioRestoreSimples = new JButton("Diret\u00F3rio");
		btnDiretorioRestoreSimples.setForeground(new Color(0, 0, 0));
		btnDiretorioRestoreSimples.setBackground(Color.CYAN);
		btnDiretorioRestoreSimples.setBounds(270, 348, 118, 23);
		contentPane.add(btnDiretorioRestoreSimples);

		JButton btnEfetuarRestoreSimples = new JButton("Enviar");
		btnEfetuarRestoreSimples.setForeground(new Color(0, 0, 0));
		btnEfetuarRestoreSimples.setBackground(Color.CYAN);
		btnEfetuarRestoreSimples.setBounds(532, 348, 103, 23);
		contentPane.add(btnEfetuarRestoreSimples);

		tfDatabaseSelecionada = new JTextField();
		tfDatabaseSelecionada.setBounds(270, 420, 287, 20);
		contentPane.add(tfDatabaseSelecionada);
		tfDatabaseSelecionada.setColumns(10);
		tfDatabaseSelecionada.setEnabled(false);

		btnDiretorioFullRestore.addActionListener(new ActionListener() {



			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelecionarDiretorioFullRestore();
				EnviarDadosParaFullrestore(database, diretorio);
			}
		});


		btnDatabaseRestoreSimples.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelecionarDatabase();
			}
		});


		btnDiretorioRestoreSimples.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelecionarDiretorioRestoreSimples();
			}
		});



		btnEfetuarRestoreSimples.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EnviarDadosParaRestoreSimples(database, diretorio);
				System.out.println("Database:"+database);
				System.out.println("Dir:"+diretorio);
			}
		});

		btnVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				new MenuView().setVisible(true);
				RestoreView.this.dispose();   

			}
		});


		JLabel lblAcompanhamentoDoProcesso = new JLabel("Resultado do restore anterior***");
		lblAcompanhamentoDoProcesso.setForeground(Color.BLACK);
		lblAcompanhamentoDoProcesso.setBounds(10, 148, 224, 14);
		contentPane.add(lblAcompanhamentoDoProcesso);


		tfAcompanhamento = new JTextField();
		tfAcompanhamento.setEnabled(false);
		tfAcompanhamento.setBounds(220, 145, 546, 20);
		contentPane.add(tfAcompanhamento);
		tfAcompanhamento.setColumns(10);
		
		JLabel lblDatabaseSelecionada = new JLabel("Database Selecionada");
		lblDatabaseSelecionada.setForeground(Color.BLACK);
		lblDatabaseSelecionada.setBounds(10, 423, 193, 14);
		contentPane.add(lblDatabaseSelecionada);

		JLabel lblDatabaseAtual = new JLabel("Database Atual");
		lblDatabaseAtual.setForeground(Color.BLACK);
		lblDatabaseAtual.setBounds(420, 96, 123, 14);
		contentPane.add(lblDatabaseAtual);

		tfDatabaseAtual = new JTextField();
		tfDatabaseAtual.setEnabled(false);
		tfDatabaseAtual.setColumns(10);
		tfDatabaseAtual.setBounds(573, 93, 193, 20);
		contentPane.add(tfDatabaseAtual);



		JLabel lblPlanoDeFundo = new JLabel("");
		lblPlanoDeFundo.setForeground(new Color(0, 0, 0));
		lblPlanoDeFundo.setIcon(new ImageIcon(RestoreView.class
				.getResource("planoDeFundo.jpg")));
		lblPlanoDeFundo.setBounds(-12, 0, 868, 583);
		contentPane.add(lblPlanoDeFundo);
	}

	public void SelecionarDiretorioFullRestore(){
		File arquivo = null;
		String diretorioBase = System.getProperty("user.home") + 
				"/Desktop";
		File dir = new File(diretorioBase);

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(dir);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);


		int retorno = chooser.showSaveDialog(null);

		if (retorno == JFileChooser.APPROVE_OPTION){
			diretorio = chooser.getSelectedFile().getAbsolutePath();
			diretorio = diretorio + "\\";

		}

		procurarArquivos(diretorio);	
	}


	public void SelecionarDiretorioRestoreSimples(){

		tfAcompanhamento.setText("");
		tfDatabaseAtual.setText("");

		verificaDiretorioRestoreSimples=1;
		File arquivo = null;
		String diretorioBase = System.getProperty("user.home") + 
				"/Desktop";
		File dir = new File(diretorioBase);

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(dir);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);


		int retorno = chooser.showSaveDialog(null);

		if (retorno == JFileChooser.APPROVE_OPTION){
			diretorio = chooser.getSelectedFile().getAbsolutePath();
			diretorio = diretorio + "\\";
		}
	}

	public void SelecionarDatabase(){
		verificaDatabaseRestoreSimples=1;
		String banco = "";
		File arquivo = null;
		String diretorioBase = System.getProperty("user.home") + 
				"/Users/FSilva/Documents/SQL Server Management Studio/Projects/";

		File dir = new File(diretorioBase);

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(dir);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(true);

		FileNameExtensionFilter filtro = new
				FileNameExtensionFilter("Arquivos com extensão .bak (.bak)", "bak");

		chooser.setAcceptAllFileFilterUsed(false);
		chooser.addChoosableFileFilter(filtro);

		int retorno = chooser.showSaveDialog(null);

		if (retorno == JFileChooser.APPROVE_OPTION){
			database = chooser.getSelectedFile().getAbsolutePath();
			database = database +"\\";


			String mensagem = database;

			StringTokenizer quebraVariavel = new StringTokenizer(mensagem, "\\");  


			while(quebraVariavel.hasMoreTokens()) {  
				banco = quebraVariavel.nextToken(); 
				database = banco;

			}


			mensagem = database;

			quebraVariavel = new StringTokenizer(mensagem, ".sql");  
			while(quebraVariavel.hasMoreTokens()){
				banco = quebraVariavel.nextToken(); 
			}

			database = "a"+database;
			String result=database.replace(" ", " ").substring(1, database.length()-4);    
			database = result;
			tfDatabaseSelecionada.setText(database);

		} 
	}


	public void EnviarDadosParaRestoreSimples(String database, String diretorio){
		if(verificaDatabaseRestoreSimples==1 && verificaDiretorioRestoreSimples==1){
			RestoreDao restore=new RestoreDao();
			restore.verificaExistencia(database, diretorio);
			verificaDatabaseRestoreSimples=0;
			verificaDiretorioRestoreSimples=0;
		}
		else{
			JOptionPane.showMessageDialog(null,"Database e/ou diretório não selecionado(s)","Atenção",JOptionPane.CANCEL_OPTION);
		}
	}	

	public void EnviarDadosParaFullrestore(String database, String diretorio){

		RestoreDao restore=new RestoreDao();
		if(!database.equalsIgnoreCase("avaliacao3") && !database.equalsIgnoreCase("master")){
			mensagemAutomatica();

			restore.efetuarFullRestore(database, diretorio);

			boolean efetuar = restore.efetuarFullRestore(database, diretorio);

			if(efetuar){
				tfAcompanhamento.setText("Restore efetuado com sucesso");
			}
			else{
				tfAcompanhamento.setText("Restore não efetuado");
			}
		}
		else
			if(database.equalsIgnoreCase("avaliacao3")){
				tfAcompanhamento.setText("Database Atual");
			}
			else{
				tfAcompanhamento.setText("Restore efetuado com sucesso"); 
			}

	}


	public List procurarArquivos(String diretorio) {
		RestoreDao dao=new RestoreDao();
		String banco = "";

		List lista = new ArrayList();
		//Cria um objeto File com o path informado e depois lista os arquivos dessa pasta
		File[] arquivos = (new File(diretorio)).listFiles();
		//Percorre todos os arquivos encontrados
		for (File atual : arquivos) {
			if (atual.isDirectory()) {
				//Se for um diretório, então chama este mesmo metodo (recursividade)
				List temp = procurarArquivos(atual.getAbsolutePath());
				if (temp != null && !temp.isEmpty()) {
					//Verifica se a lista retornada não está vazia (não encontrou nenhum arquivo)
					lista.add(arquivos);
				}
			} else {
				//Se for um arquivo, então verifica se ele deve ir para a lista
				if (atual.getName().endsWith(".BAK")) {

					String mensagem = atual.getAbsolutePath();
					mensagem = mensagem +"\\";

					StringTokenizer quebraVariavel = new StringTokenizer(mensagem, "\\");  

					while(quebraVariavel.hasMoreTokens()) {  
						banco = quebraVariavel.nextToken(); 
						database = banco;

					}


					mensagem = database;

					quebraVariavel = new StringTokenizer(mensagem, ".BAK");  
					while(quebraVariavel.hasMoreTokens()){
						banco = quebraVariavel.nextToken(); 
					}

					database = "a"+database;
					String result=database.replace(" ", " ").substring(1, database.length()-4);    

					database = result;
					tfDatabaseAtual.setText(database);

					EnviarDadosParaFullrestore(result, diretorio);
				}

				lista.add(atual.getAbsolutePath());

			}
		}

		return lista;
	}

	public void mensagemAutomatica(){
		JOptionPane meuJOPane = new JOptionPane("Efetuando o processo de restore da database:"+database);//instanciando o JOptionPane  
		final JDialog dialog = meuJOPane.createDialog(null, "Aguarde");//aqui uso um JDialog para manipular  
		//meu JOptionPane  
		dialog.setModal(true);    
		//Usando o javax.swing.Timer para poder gerar um evento em um tempo determinado  
		//Veja o construtor da classe Timer para mais explicações  
		Timer timer = new Timer(2 * 1000, new ActionListener() {    
			public void actionPerformed(ActionEvent ev) {    
				dialog.dispose();  //o evento(no caso fechar o meu JDialog)  
			}    
		});    
		timer.start();  
		dialog.setVisible(true);  
		timer.stop();  
	}





}



