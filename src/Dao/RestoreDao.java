package Dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


import Entidades.Database;


public class RestoreDao {
	public boolean efetuarFullRestore(String database, String diretorio) {
		boolean efetuado = false;

		try {
			Connection con = JTDSUtil.getConnection();
			String sql = "{call restore_todos(?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, database);
			cs.setString(2, diretorio);
			cs.execute();
			cs.close();
			efetuado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return efetuado;
	}


	public int verificaExistencia(String database, String diretorio) {
		int databaseExistente = 0;
		Database a=new Database();
		try{
			Connection con = JTDSUtil.getConnection();
			String sql = "Select dbo.verifica_existencia(?,?)";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, database);
			stmt.setString(2, diretorio);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				databaseExistente = (rs.getInt(1));

				if(databaseExistente==1){
					String resposta = JOptionPane.showInputDialog("A database já existe, deseja realmente sobrescrevê-la?");
					if(resposta.equalsIgnoreCase("Sim")){
						efetuarRestoreSimples(database, diretorio);
					}
					else
						if(!resposta.equalsIgnoreCase("Sim") && databaseExistente==1){
							JOptionPane.showMessageDialog(null,"Devido a sua solicitação o restore não foi realizado","Atenção",JOptionPane.INFORMATION_MESSAGE);
						}
				}
				else{
					if(databaseExistente==0){
						efetuarRestoreSimples(database, diretorio);
					}
				}

				stmt.close();
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}


		return databaseExistente;

	}



	public boolean efetuarRestoreSimples(String database, String diretorio) {
		boolean efetuado = false;

		try {
			Connection con = JTDSUtil.getConnection();
			String sql = "{call restore_database(?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, database);
			cs.setString(2, diretorio);
			cs.execute();
			cs.close();
			efetuado = true;
			JOptionPane.showMessageDialog(null,"Restore efetuado com sucesso","Êxito",JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Atenção",JOptionPane.CANCEL_OPTION);

		}

		return efetuado;
	}
}
