package View;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;

import Controller.MenuController;



public class MenuView extends JFrame implements MenuController {

	private JPanel contentPane;
	private JLabel lblData = new JLabel("");
	private Date data;
	private DateFormat formato= new SimpleDateFormat("hh:mm:ss");
	private JLabel lblHorario = new JLabel();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView frame = new MenuView();
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
	public MenuView() {
		super("Menu Principal");
		gerarFormulario();
		inicializaDataHora();
	}

	public void gerarFormulario(){
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		setLocation(100, 80);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		lblHorario.setForeground(Color.RED);

		contentPane.add(lblHorario);

		Timer timer = new Timer();
		timer.start();


		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLUE);
		menuBar.setBounds(0, 0, 800, 21);
		contentPane.add(menuBar);

		JMenuItem menuBackup = new JMenuItem("Backup");
		menuBar.add(menuBackup);


		JMenuItem menuRestore = new JMenuItem("Restore");
		menuBar.add(menuRestore);


		JMenuItem menuConsultaDatabases = new JMenuItem("Consulta databases");
		menuBar.add(menuConsultaDatabases);

		JMenuItem menuCorreioEletronico = new JMenuItem("Correio Eletrônico");
		menuBar.add(menuCorreioEletronico);


		JLabel lblSistemaDeLocacao = new JLabel("");
		contentPane.add(lblSistemaDeLocacao);
		lblSistemaDeLocacao.setIcon(new ImageIcon(MenuView.class
				.getResource("nuvem.jpg")));
		lblData.setForeground(Color.RED);


		lblData.setBounds(234, 527, 93, 14);
		contentPane.add(lblData);


		lblSistemaDeLocacao.setForeground(Color.BLUE);
		lblSistemaDeLocacao.setFont(new Font("Arial", Font.PLAIN, 25));
		lblSistemaDeLocacao.setBounds(195, 88, 400, 394);
		contentPane.add(lblSistemaDeLocacao);


		lblHorario.setBounds(508, 527, 210, 14);
		contentPane.add(lblHorario);



		JLabel lblPlanoDeFundo = new JLabel("");
		lblPlanoDeFundo.setIcon(new ImageIcon(MenuView.class
				.getResource("planoDeFundo.jpg")));
		lblPlanoDeFundo.setBounds(0, 22, 868, 561);
		contentPane.add(lblPlanoDeFundo);




		ActionListener listenerMenuBackup = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BackupView().setVisible(true);
				MenuView.this.dispose();
			}
		};

		ActionListener listenerMenuRestore = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new RestoreView().setVisible(true);
				MenuView.this.dispose();

			}

		};

		ActionListener listenerMenuConsultaDatabases = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new ConsultaDatabasesView().setVisible(true);
				MenuView.this.dispose();

			}
		};

		ActionListener listenerMenuCorreioEletronico = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new CorreioEletronicoView().setVisible(true);
				MenuView.this.dispose();

			}
		};




		menuBackup.addActionListener(listenerMenuBackup);
		menuRestore.addActionListener(listenerMenuRestore);
		menuConsultaDatabases.addActionListener(listenerMenuConsultaDatabases);
		menuCorreioEletronico.addActionListener(listenerMenuCorreioEletronico);
	}


	public void inicializaDataHora(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");   
		Calendar c = Calendar.getInstance(); 
		c.add(Calendar.DAY_OF_MONTH,0);  
		lblData.setText(sdf.format(c.getTime()));
		SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
	}



	class Timer extends Thread{
		@Override
		public void run() {

			while (true) {
				try {
					data = new Date();
					lblHorario.setText(formato.format(data));
					Timer.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


		}
	}
}