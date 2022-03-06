package login;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.Calendar;

import javax.swing.JOptionPane;

import application.Emploi_du_temps;
import application.Evenement;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;

public class MysqlConnect {
	 Connection conn = null;
	    public static Connection ConnectDb(){
	    
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/focus","root","mysql");
	            JOptionPane.showMessageDialog(null, "Connection Established");
	            return conn;
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	            return null;
	   }
	}
	    public static ObservableList<Evenement> getDataEvenement(){
			Connection conn = ConnectDb();
			String sql = "select * from evenement where id_utilisateur=?";
			ObservableList<Evenement> list = FXCollections.observableArrayList();
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, Main.id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					list.add(new Evenement(rs.getString("titre"),rs.getTime("heure"),rs.getString("description")));
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return list;
		}
	   public static ObservableList<Emploi_du_temps> getDataEmploi_du_temps(){
			Connection conn = ConnectDb();
			String sql = "select * from emploi_du_temps where id_utilisateur=?";
			ObservableList<Emploi_du_temps> list = FXCollections.observableArrayList();
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, Main.id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					list.add(new Emploi_du_temps(rs.getInt("id"),rs.getInt("id_utilisateur"),rs.getTime("horaire_debut"),rs.getTime("horaire_fin"),rs.getString("status"),rs.getString("titre"),rs.getString("description")));
				}
			} catch(Exception e) {
				
			}
			return list;
		}
		//mettre a jour le status
		public static int MAJEmploi(Emploi_du_temps em){
			Connection conn = ConnectDb();
			String sql = "update emploi_du_temps set status=? where id=?";
			
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, em.getStatus());
				ps.setInt(2, em.getId());
				ps.execute();
			} catch(Exception e) {
				
			}
			return 0;
		}
		//calculer le ratio de taches accomplies
		public static double calcRatio(int uid) {
			double total=0,faites=0;
			Connection conn = ConnectDb();
			String sql1 = "select count(*) as total from emploi_du_temps where id_utilisateur=?";
			String sql2 = "select count(*) as faites from emploi_du_temps where id_utilisateur=? and status= ?";
			try {
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps1.setInt(1, uid);
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setInt(1, uid);
				ps2.setString(2,"achevée" );
				ResultSet rs1 = ps1.executeQuery();
				ResultSet rs2 = ps2.executeQuery();
				while (rs1.next() &&rs2.next()) {
					total=(double)rs1.getInt("total");
					faites=(double)rs2.getInt("faites");
			}
				double p=(faites/total)*100;
				return p;
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		public static String EvenementsAvider(){
			Date d = new Date(Calendar.getInstance().getTime().getTime());
			Connection conn = ConnectDb();
			String s="les évenements suivants peuvent etres supprimés:\n";
			String sql = "select * from evenement where id_utilisateur=? and DATEDIFF(date,?) >=2";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, Main.id);
				ps.setDate(2, d);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					s+=rs.getString("titre")+" ajouté le "+rs.getDate("date")+"\n";
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return s;
		}
		public static String HorairesAvider(){
			long now = System.currentTimeMillis();
			Time t = new Time(now);
			Connection conn = ConnectDb();
			String s="les horaires suivants sont passés et peuvent etres supprimés:\n";
			String sql = "Select * from emploi_du_temps where id_utilisateur=? and CAST(horaire_fin As Time) <=?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, Main.id);
				ps.setTime(2, t);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					s+=rs.getString("titre")+" commencant a "+rs.getTime("horaire_debut")+" et finissant a "+rs.getTime("horaire_fin")+"\n";
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return s;
		}
		
		 public static int viderHoraires(){
				Connection conn = ConnectDb();
				long now = System.currentTimeMillis();
				Time t = new Time(now);
				String sql = "delete from emploi_du_temps where id_utilisateur=? and CAST(horaire_fin As Time) <=?";
				try {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, Main.id);
					ps.setTime(2, t);
					ps.execute();
				} catch(Exception e) {
					e.printStackTrace();
				}
				return 0;
			}
		 public static int viderEvenements(){
				Connection conn = ConnectDb();
				Date d = new Date(Calendar.getInstance().getTime().getTime());
				String sql = "delete from evenement where id_utilisateur=? and DATEDIFF(date,?) >=2";
				try {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, Main.id);
					ps.setDate(2, d);
					ps.execute();
				} catch(Exception e) {
					e.printStackTrace();
				}
				return 0;
			}

}
