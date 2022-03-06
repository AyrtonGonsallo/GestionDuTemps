package application;



import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import login.MysqlConnect;



public class JoursController implements Initializable{
	@FXML
	private Label datedujour;
	public void initData (LocalDate d,Infosdujour i){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");   
		datedujour.setText(dtf.format(d));
		infos.setText(i.getInformation());
	}
	public void initData (LocalDate d){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");   
		datedujour.setText(dtf.format(d));
	}
	@FXML
	private Button retour;
	@FXML
	private TextArea infos;
	@FXML
	public void retourner(ActionEvent event) {
		new Main().son2();
		Main.setPane(0);
	}
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	public void enregistrer(ActionEvent event) {
		new Main().son3();
		conn = MysqlConnect.ConnectDb();
		String sql = "insert into infosdujour(information,jour,user_id) values (?,?,?)";
		String tabjour[]=datedujour.getText().split("/");
		String jour=tabjour[2]+"-"+tabjour[1]+"-"+tabjour[0];
		Date d=Date.valueOf(jour);
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, infos.getText());
			pst.setDate(2,d);
			pst.setInt(3,Main.id );
			pst.execute();
			new Main().son3();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}
	

}
