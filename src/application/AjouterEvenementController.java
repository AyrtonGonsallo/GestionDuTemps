package application;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import objets.Evenement;
import objets.Singleton;;

public class AjouterEvenementController implements Initializable {
	@FXML
	private Button enregistrer;
	@FXML
	private Button retour;
	@FXML
	private TextField txt_titre;
	@FXML
	private TextField txt_description;
	@FXML
	private TextField date;
	@FXML
	private TextField heure;
	@FXML
	public void enregistrer() {
		new Main().son3();
		addEvenement();
		Main.setPane(3);
	}
	@FXML
	public void retour() {
		new Main().son2();
		Main.setPane(3);
	}
	
	
	public void addEvenement() {
		try {
			Evenement e=new Evenement();
			e.setDate(Date.valueOf(date.getText()));
			e.setDescription(txt_description.getText());
			e.setHeure(Time.valueOf(heure.getText()) );
			e.setId(Singleton.getInstance().getEvents().size()+1);
			e.setTitre(txt_titre.getText());
			e.setUser_id(Main.id);
			Singleton.getInstance().addEvent(e);
			application.EvenementController ec=Main.le.getController();
			ec.updateTable();
			ec.initData(Main.id);
			JOptionPane.showMessageDialog(null, "Evenement ajouté avec succès!");
			new Main().son3();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

}
