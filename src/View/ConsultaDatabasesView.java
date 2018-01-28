package View;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;

import Controller.ConsultaDatabaseController;
import Dao.ConsultaDatabaseDao;

import Entidades.Database;

import java.io.*;  
import java.util.zip.*;
import javax.swing.JButton; 

import net.sf.jasperreports.engine.JasperPrint;

public class ConsultaDatabasesView extends JFrame implements ConsultaDatabaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private	javax.swing.table.DefaultTableModel tablemodel = new DefaultTableModel();
	private JTable tabela;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaDatabasesView frame = new ConsultaDatabasesView();
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
	@SuppressWarnings("deprecation")
	public ConsultaDatabasesView() {
		super("Lista de Databases Existentes");
		gerarFormulario();
	}


	public void gerarFormulario(){
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDatabasesDoSeu = new JLabel("");
		lblDatabasesDoSeu.setBounds(53, 11, 371, 46);
		contentPane.add(lblDatabasesDoSeu);

		ConsultaDatabaseDao dao = new ConsultaDatabaseDao();
		List<Database> conteudo = dao.consultarDados();
		tablemodel = new javax.swing.table.DefaultTableModel(new Object [] {"Nome", "Data ", "Hora"}, 0);
		setLocation(100, 80);


		for (Database db : conteudo)
		{
			tablemodel.addRow(new Object [] {db.getNome(), db.getData(), db.getHora()} );

		}


		tabela = new JTable(tablemodel);
		JTableHeader titulos = tabela.getTableHeader();
		titulos.setBackground(Color.yellow);
		titulos.setForeground(Color.red);

		tabela.setBackground(Color.cyan);
		tabela.setForeground(Color.blue);
		tabela.setBounds(10, 93, 414, 137);
		tabela.disable();		
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(27, 39, 737, 453);
		contentPane.add(scrollPane);

		JButton btnPDF = new JButton("Gerar PDF");
		btnPDF.setBackground(Color.CYAN);
		btnPDF.setBounds(187, 524, 102, 23);
		contentPane.add(btnPDF);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(Color.CYAN);
		btnVoltar.setBounds(492, 524, 102, 23);
		contentPane.add(btnVoltar);


		btnPDF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gerarPDF();
			}
		});



		btnVoltar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ConsultaDatabasesView.this.dispose();   
				new MenuView().setVisible(true);

			}
		});


		JLabel lblPlanoDeFundo = new JLabel("");
		lblPlanoDeFundo.setIcon(new ImageIcon(ConsultaDatabasesView.class
				.getResource("planoDeFundo.jpg")));
		lblPlanoDeFundo.setBounds(0, 0, 868, 583);
		contentPane.add(lblPlanoDeFundo);





	}




	public void gerarPDF(){
		ConsultaDatabaseDao dao=new ConsultaDatabaseDao();
		dao.preparaPDF();


	}
}
