package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import objets.Projet;
import objets.Singleton;
import objets.Status;

public class AjouterProjetController implements Initializable {
	@FXML
	private Button enregistrer;
	@FXML
	private Button retour;
	@FXML
	private TextField txt_activite;
	@FXML
	private TextArea description;
	
	@FXML
	public void enregistrer(ActionEvent event) {
		new Main().son3();
		AddHoraire();
		Main.setPane(16);//On retourne dans la page Projet
	}
	
	@FXML
	public void retour(ActionEvent event) {
		new Main().son2();
		Main.setPane(16);
	}
	
	
	public void AddHoraire() {
		
		try {
			List<Projet>lempl=Singleton.getInstance().getProjets();
			Projet e=new Projet();
			e.setDescription(description.getText());
			if(lempl.size()==0){
				e.setId(1);
			}else{
				e.setId(lempl.get(lempl.size()-1).getId()+1);
			}
			
			e.setStatus(Status.inachevée.toString());
			e.setTitre(txt_activite.getText());
			e.setUser_id(Main.id);
			Singleton.getInstance().addProjets(e);
			application.ProjetController pc=Main.le.getController();
			pc.updateTable();
			pc.initData(Main.id);
			JOptionPane.showMessageDialog(null, "Projet Ajouté avec succès");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
