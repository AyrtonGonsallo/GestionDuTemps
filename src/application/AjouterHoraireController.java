package application;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import objets.Emploi_du_temps;
import objets.Singleton;
import objets.Status;

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
	
	
	public void AddHoraire() {
	Time deb=Time.valueOf( horaire_debut.getText());
	Time fin=Time.valueOf( horaire_fin.getText());
		if(deb.before(fin)) {
		
		try {
			Emploi_du_temps e=new Emploi_du_temps();
			e.setDescription(description.getText());
			e.setHorraire_debut(deb);
			e.setHorraire_fin(fin);
			e.setId(Singleton.getInstance().getEmplois().size()+1);
			e.setStatus(Status.inachevée.toString());
			e.setTitre(txt_activite.getText());
			e.setUser_id(Main.id);
			Singleton.getInstance().addEmplois(e);
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
