package application;

import java.net.URL;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import objets.Emploi_du_temps;
import objets.Singleton;
import objets.Status;

public class AjouterHoraireController implements Initializable {
	@FXML
	private Button enregistrer;
	@FXML
	private Button retour;
	@FXML
	private Spinner<Integer>heureD; 
	@FXML
	private Spinner<Integer>minutesD;
	@FXML
	private Spinner<Integer>secondesD;
	@FXML
	private Spinner<Integer>heureF; 
	@FXML
	private Spinner<Integer>minutesF;
	@FXML
	private Spinner<Integer>secondesF;
	@FXML
	private TextField txt_activite;
	@FXML
	private TextArea description;
	
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
	Time deb=Time.valueOf( heureD.getValue()+":"+minutesD.getValue()+":"+secondesD.getValue());
	Time fin=Time.valueOf(  heureF.getValue()+":"+minutesF.getValue()+":"+secondesF.getValue());
		if(deb.before(fin)) {
		
		try {
			List<Emploi_du_temps>lempl=Singleton.getInstance().getEmplois();
			Emploi_du_temps e=new Emploi_du_temps();
			e.setDescription(description.getText());
			e.setHorraire_debut(deb);
			e.setHorraire_fin(fin);
			if(lempl.size()==0){
				e.setId(1);
			}else{
				e.setId(lempl.get(lempl.size()-1).getId()+1);
			}
			
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
		heureD.setValueFactory(
	            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24)
	        );
		minutesD.setValueFactory(
	            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60)
	        );
		secondesD.setValueFactory(
	            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60)
	        );
		heureF.setValueFactory(
	            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24)
	        );
		minutesF.setValueFactory(
	            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60)
	        );
		secondesF.setValueFactory(
	            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60)
	        );
	}

}
