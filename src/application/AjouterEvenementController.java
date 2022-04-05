package application;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
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
	private TextArea txt_description;
	@FXML
	private DatePicker date;
	@FXML
	private Spinner<Integer>heure; 
	@FXML
	private Spinner<Integer>minutes;
	@FXML
	private Spinner<Integer>secondes;
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
			List<Evenement>events=Singleton.getInstance().getEvents();
			Evenement e=new Evenement();
			e.setDate(Date.valueOf(date.getValue()));
			e.setDescription(txt_description.getText());
			e.setHeure(Time.valueOf(heure.getValue()+":"+minutes.getValue()+":"+secondes.getValue()) );
			if(events.size()==0){
				e.setId(1);
			}else{
				e.setId(events.get(events.size()-1).getId()+1);
			}
			
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
		heure.setValueFactory(
	            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24)
	        );
		minutes.setValueFactory(
	            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60)
	        );
		secondes.setValueFactory(
	            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60)
	        );
		
	}

}
