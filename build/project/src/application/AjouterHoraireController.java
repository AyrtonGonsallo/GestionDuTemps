package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import login.MysqlConnect;

public class AjouterHoraireController {
	@FXML
	private Button enregistrer;
	@FXML
	private Button retour;
	@FXML
	private TextField horaire_debut;
	@FXML
	private TextField horaire_fin;
	@FXML
	private TextField txt_activite;
	@FXML
	private TextField description;
	
	@FXML
	public void enregistrer(ActionEvent event) {
		new Main().son3();
		AddHoraire();
		Main.setPane(3);//On retourne dans la page Evenement
	}
	
	@FXML
	public void retour(ActionEvent event) {
		new Main().son2();
		Main.setPane(3);
	}
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public void AddHoraire() {
	Time deb=Time.valueOf( horaire_debut.getText());
	Time fin=Time.valueOf( horaire_fin.getText());
		if(deb.before(fin)) {
		conn = MysqlConnect.ConnectDb();
		String sql = "insert into emploi_du_temps (horaire_debut,horaire_fin,titre,status,description,id_utilisateur)values(?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setTime(1, deb);
			pst.setTime(2, fin);
			pst.setString(3, txt_activite.getText());
			pst.setString(4, "inachevée");
			pst.setString(5, description.getText());
			pst.setInt(6,Main.id );
			pst.execute();
			application.EvenementController ec=Main.le.getController();
			ec.updateTable();
			ec.initData(Main.id);
			JOptionPane.showMessageDialog(null, "Horaire Ajouté avec succès");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		}
		else {
			JOptionPane.showMessageDialog(null, "l'heure de debut doit etre avant celle de fin");
		}
		
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

}
