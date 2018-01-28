package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import Entidades.Database;

public class ConsultaDatabaseDao {



	public List<Database> consultarDados(){
		Connection con=JTDSUtil.getConnection();
		String sql = "select name, crdate from sys.sysdatabases where name NOT IN ('tempdb') order by name";
		List <Database> dados = new ArrayList<Database>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{	
				Database informacoes = new Database();
				informacoes.setNome(rs.getString("name"));
				SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy");
				String data = sdf.format(rs.getDate("crdate"));	
				informacoes.setData(data);
				informacoes.setHora(rs.getTime("crdate").toString());
				dados.add(informacoes);
				//System.out.println(d.getNome() +" "+ rs.getString(2)+ " " + d.getHora());
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dados;
	}


	public List<Database> preparaPDF(){
		Connection con=JTDSUtil.getConnection();
		String sql = "select name, crdate from sys.sysdatabases where name NOT IN ('tempdb') order by name";
		List <Database> dados = new ArrayList<Database>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{	
				Database informacoes = new Database();
				informacoes.setNome(rs.getString("name"));
				SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy");
				String data = sdf.format(rs.getDate("crdate"));	
				informacoes.setData(data);
				dados.add(informacoes);
				//System.out.println(d.getNome() +" "+ rs.getString(2)+ " " + d.getHora());
			}
			gerarPDF();
			JasperPrint relat;
			relat = gerarPDF();
			JasperViewer.viewReport(relat, false);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dados;
	}



	public JasperPrint gerarPDF(){
		JasperPrint rel = null;
		try {
			Connection con = JTDSUtil.getConnection();
			HashMap map = new HashMap();
			String arquivoJasper = "avaliacao3.jasper";
			rel = JasperFillManager.fillReport(arquivoJasper, map, con);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		return rel;
	}






}
