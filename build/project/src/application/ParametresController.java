package application;
	
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import login.Login;
import login.MysqlConnect;




public class ParametresController implements Initializable{

	@FXML
	private TextArea com;//aller vers le menu
	@FXML
	private Button retour;//aller vers le menu
	@FXML
	private Button envoyer;
	@FXML
	private Button infos;
	@FXML
	public void retour(ActionEvent event) {
		new Main().son2();
		Main.setPane(0);
	}
	public void allerinfos(ActionEvent event) throws IOException {
		conn = MysqlConnect.ConnectDb();
		String sql = "select * from login where id =?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Main.id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Login log=new Login(rs.getInt("id"),rs.getString("email"),rs.getString("identifiant"),rs.getInt("age"),rs.getDate("date_creation"));
				new Main().son1();
				FXMLLoader l=new FXMLLoader();
				l.setLocation(getClass().getResource( "/login/InfosCompte.fxml"));
				Object tableViewParent=l.load();
				login.InfosCompteController jc=l.getController();
				jc.initData(log);
				Main.root.getChildren().add((AnchorPane)tableViewParent);
				Main.grid.set(13,(AnchorPane)tableViewParent);
		        Main.setInd_c(13);
				Main.root.getChildren().remove(Main.grid.get(1));
				Main.setPane(13);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void envoyer(ActionEvent event) {
		new Main().son3();
		addCom();
	}
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Date d = new Date(Calendar.getInstance().getTime().getTime());
	private void addCom() {
		conn = MysqlConnect.ConnectDb();
		String sql = "insert into commentaire(user_id,texte,date) values (?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Main.id);
			pst.setString(2, com.getText());
			pst.setDate(3,d );
			pst.execute();
			JOptionPane.showMessageDialog(null, "Votre commentaire a été ajouté");
			new Main().son3();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
	
}