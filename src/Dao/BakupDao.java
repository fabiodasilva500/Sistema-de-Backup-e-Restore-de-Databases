package Dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class BakupDao {
	public boolean efetuarFullBackup(String diretorio) {
		boolean efetuado = false;

		try {
			Connection con = JTDSUtil.getConnection();
			String sql = "{call full_backup(?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, diretorio);
			cs.execute();
			cs.close();
			efetuado = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Atenção",JOptionPane.CANCEL_OPTION);
		}

		return efetuado;
	}


	public boolean efetuarBackupSimples(String database, String diretorio) {
		boolean efetuado = false;
		System.out.println("Diretório:"+diretorio);
		System.out.println("Database:"+database);

		try {
			Connection con = JTDSUtil.getConnection();
			String sql = "{call backup_database_selecionada(?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, database);
			cs.setString(2, diretorio);
			cs.execute();
			cs.close();
			efetuado = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Atenção",JOptionPane.CANCEL_OPTION);
		}

		return efetuado;
	}
}
