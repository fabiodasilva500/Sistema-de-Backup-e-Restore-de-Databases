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
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.Timer;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;


import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

import Controller.BackupController;
import Dao.BakupDao;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollBar;

public class BackupView  extends JFrame  implements BackupController{

	private JPanel contentPane;
	private JComboBox cbEmail;
	private String selecionado;
	private String database = "";
	private String diretorio = "";
	private int verificaDiretorioFullBackup=0;
	private int verificaDiretorioBackupSimples=0;
	private int verificaDatabaseBackupSimples=0;
	private JTextField tfDatabase;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					BackupView frame = new BackupView();
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
	public BackupView() {
		super("Realização de Backup de Databases");
		gerarFormulario();
	}

	public void gerarFormulario(){
		setResizable(false);
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

		JLabel lblFullBackup = new JLabel("Full Backup");
		lblFullBackup.setForeground(Color.BLUE);
		lblFullBackup.setBackground(Color.RED);
		lblFullBackup.setBounds(10, 51, 193, 14);
		contentPane.add(lblFullBackup);


		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.RED);
		separator_2.setBounds(0, 473, 914, 2);
		contentPane.add(separator_2);

		JButton btnDiretorioFullBackup = new JButton("Diret\u00F3rio");
		btnDiretorioFullBackup.setForeground(new Color(0, 0, 0));
		btnDiretorioFullBackup.setBounds(214, 99, 118, 23);
		contentPane.add(btnDiretorioFullBackup);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(331, 517, 89, 23);
		contentPane.add(btnVoltar);

		btnDiretorioFullBackup.setBackground(Color.cyan);
		btnVoltar.setBackground(Color.cyan);

		JLabel lblSelecionaDiretorioDestino = new JLabel("Selecione o Diret\u00F3rio de Destino");
		lblSelecionaDiretorioDestino.setForeground(new Color(0, 0, 0));
		lblSelecionaDiretorioDestino.setBounds(10, 103, 217, 14);
		contentPane.add(lblSelecionaDiretorioDestino);

		JLabel lblBackupDatabaseEspecifica = new JLabel("Backup espec\u00EDfico");
		lblBackupDatabaseEspecifica.setForeground(Color.BLUE);
		lblBackupDatabaseEspecifica.setBounds(10, 192, 193, 14);
		contentPane.add(lblBackupDatabaseEspecifica);

		JLabel lblDatabase = new JLabel("Selecione a Database Desejada");
		lblDatabase.setForeground(new Color(0, 0, 0));
		lblDatabase.setBounds(10, 274, 217, 14);
		contentPane.add(lblDatabase);

		JButton btnDatabaseBackupSimples = new JButton("Database ");
		btnDatabaseBackupSimples.setForeground(new Color(0, 0, 0));
		btnDatabaseBackupSimples.setBackground(Color.CYAN);
		btnDatabaseBackupSimples.setBounds(237, 270, 118, 23);
		contentPane.add(btnDatabaseBackupSimples);

		JLabel lblDiretorio = new JLabel("Selecione o Diret\u00F3rio Desejado");
		lblDiretorio.setForeground(new Color(0, 0, 0));
		lblDiretorio.setBounds(10, 352, 217, 14);
		contentPane.add(lblDiretorio);

		JButton btnDiretorioBackupSimples = new JButton("Diret\u00F3rio");
		btnDiretorioBackupSimples.setForeground(new Color(0, 0, 0));
		btnDiretorioBackupSimples.setBackground(Color.CYAN);
		btnDiretorioBackupSimples.setBounds(237, 348, 118, 23);
		contentPane.add(btnDiretorioBackupSimples);

		JButton btnEfetuarFullBackup = new JButton("Full Backup");
		btnEfetuarFullBackup.setForeground(new Color(0, 0, 0));
		btnEfetuarFullBackup.setBackground(Color.CYAN);
		btnEfetuarFullBackup.setBounds(408, 99, 103, 23);
		contentPane.add(btnEfetuarFullBackup);

		JButton btnEfetuarBackupSimples = new JButton("Backup Simples");
		btnEfetuarBackupSimples.setForeground(new Color(0, 0, 0));
		btnEfetuarBackupSimples.setBackground(Color.CYAN);
		btnEfetuarBackupSimples.setBounds(437, 348, 140, 23);
		contentPane.add(btnEfetuarBackupSimples);

		tfDatabase = new JTextField();
		tfDatabase.setBounds(487, 271, 297, 20);
		contentPane.add(tfDatabase);
		tfDatabase.setColumns(10);
		tfDatabase.setEnabled(false);

		btnDiretorioFullBackup.addActionListener(new ActionListener() {



			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelecionarDiretorio();				
			}
		});


		btnDatabaseBackupSimples.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelecionarDatabase();
			}
		});


		btnDiretorioBackupSimples.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelecionarDiretorio();
			}
		});




		btnEfetuarFullBackup.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				EnviarDadosParaFullBackup(diretorio);


			}
		});


		btnEfetuarBackupSimples.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EnviarDadosParaBackupSimples(database, diretorio);
			}
		});

		btnVoltar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				BackupView.this.dispose();   
				new MenuView().setVisible(true);

			}
		});

		JLabel lblDatabase_1 = new JLabel("Database");
		lblDatabase_1.setForeground(Color.BLACK);
		lblDatabase_1.setBounds(408, 274, 131, 14);
		contentPane.add(lblDatabase_1);


		JLabel lblPlanoDeFundo = new JLabel("");
		lblPlanoDeFundo.setForeground(Color.BLACK);
		lblPlanoDeFundo.setIcon(new ImageIcon(BackupView.class
				.getResource("planoDeFundo.jpg")));
		lblPlanoDeFundo.setBounds(0, 0, 868, 583);
		contentPane.add(lblPlanoDeFundo);




	}

	public void SelecionarDiretorio(){
		verificaDiretorioFullBackup=1;
		verificaDiretorioBackupSimples=1;

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
		verificaDatabaseBackupSimples=1;
		String banco = "";
		File arquivo = null;
		String diretorioBase = System.getProperty("user.home") + 
				"/Users/FSilva/Documents/SQL Server Management Studio/Projects/";

		File dir = new File(diretorioBase);

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(dir);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		FileNameExtensionFilter filtro = new
				FileNameExtensionFilter("Arquivos com extensão .sql (.sql)", "sql");

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
			tfDatabase.setText(database);  
		}

	}


	public void EnviarDadosParaBackupSimples(String database, String diretorio){
		if(verificaDatabaseBackupSimples==1 && verificaDiretorioBackupSimples==1){

			BakupDao backup=new BakupDao();
			backup.efetuarBackupSimples(tfDatabase.getText(), diretorio);
			
			boolean efetuar = backup.efetuarBackupSimples(tfDatabase.getText(), diretorio);
			System.out.println(tfDatabase.getText());


			if(efetuar){
				JOptionPane.showMessageDialog(null,"Backup efetuado com sucesso","Êxito",JOptionPane.INFORMATION_MESSAGE);
				verificaDiretorioBackupSimples=0;
				verificaDatabaseBackupSimples=0;
				verificaDiretorioFullBackup=0;

			}
			else{
				JOptionPane.showMessageDialog(null, "Backup não efetuado","Atenção",JOptionPane.CANCEL_OPTION);
				verificaDiretorioBackupSimples=0;
				verificaDatabaseBackupSimples=0;
				verificaDiretorioFullBackup=0;

			}
		}

		else{
			JOptionPane.showMessageDialog(null,"Diretório e/ou database não selecionado(s).","Atenção",JOptionPane.WARNING_MESSAGE);
		}
	}

	public void EnviarDadosParaFullBackup(String diretorio){
		if(verificaDiretorioFullBackup==1){
			BakupDao backup=new BakupDao();

			mensagemAutomatica();	

			backup.efetuarFullBackup(diretorio);
			boolean efetuar = backup.efetuarFullBackup(diretorio);


			if(efetuar){
				JOptionPane.showMessageDialog(null,"Backup efetuado com sucesso","Êxito",JOptionPane.INFORMATION_MESSAGE);
				verificaDiretorioFullBackup=0;
			}
			else{
				JOptionPane.showMessageDialog(null, "Backup não efetuado","Atenção",JOptionPane.CANCEL_OPTION);
				verificaDiretorioFullBackup=0;
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Diretório não selecionado.","Atenção",JOptionPane.WARNING_MESSAGE);
		}
	}


	public void mensagemAutomatica(){
		JOptionPane meuJOPane = new JOptionPane("Por favor aguarde até que o processo de backup seja finalizado");//instanciando o JOptionPane  
		final JDialog dialog = meuJOPane.createDialog(null, "Aguarde");//aqui uso um JDialog para manipular  
		//meu JOptionPane  
		dialog.setModal(true);    
		//Usando o javax.swing.Timer para poder gerar um evento em um tempo determinado  
		//Veja o construtor da classe Timer para mais explicações  
		Timer timer = new Timer(10 * 1000, new ActionListener() {    
			public void actionPerformed(ActionEvent ev) {    
				dialog.dispose();  //o evento(no caso fechar o meu JDialog)  
			}    
		});    
		timer.start();  
		dialog.setVisible(true);  
		timer.stop();  
	}



}



