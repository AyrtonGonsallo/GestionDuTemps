package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import objets.Emploi_du_temps;
import objets.Evenement;
import objets.InfosDuJour;
import objets.Singleton;
import objets.Utilisateur;
import objets.note;

public class AdminController implements Initializable{
	
	@FXML
	ListView<String> liste;
	@FXML
	TextField supprId;
	@FXML
	ChoiceBox<String>supprChoice;
	@FXML
	TextField viewId;
	@FXML
	ChoiceBox<String>viewChoice;
	
	 
	@FXML
	public void retour(ActionEvent event) {
		new Main().son2();
		Main.setPane(0);
	}
	@FXML
	public void effacer(ActionEvent event) {
		liste.getItems().clear();
	}
	@FXML
	public void VoirElements(ActionEvent event) {
		liste.getItems().clear();
		if(viewChoice.getValue()!=null){
			switch (viewChoice.getValue()) {
			case "Emploi du temps":
				List<Emploi_du_temps>lemp=new ArrayList<>();
				lemp=Singleton.getInstance().getEmplois();
				if(lemp!=null && lemp.size()>0){
					for(Emploi_du_temps e:lemp){
					liste.getItems().add(e.toString());
					}
				}else{
					liste.getItems().add("aucun "+viewChoice.getValue());
				}
				
				break;
			case "Evenement":
				List<Evenement>lev=new ArrayList<>();
				lev=Singleton.getInstance().getEvents();
				if(lev!=null && lev.size()>0){
					for(Evenement e:lev){
					liste.getItems().add(e.toString());
					}
				}else{
					liste.getItems().add("aucun "+viewChoice.getValue());
				}
				
				break;
			case "Utilisateur":
				List<Utilisateur>lus=new ArrayList<>();
				lus=Singleton.getInstance().getUsers();
				if(lus!=null && lus.size()>0){
					for(Utilisateur e:lus){
					liste.getItems().add(e.toString());
					}
				}else{
					liste.getItems().add("aucun "+viewChoice.getValue());
				}
				break;
			case "Infos du Jour":
				List<InfosDuJour>lin=new ArrayList<>();
				lin=Singleton.getInstance().getInfos();
				if(lin!=null && lin.size()>0){
					for(InfosDuJour e:lin){
					liste.getItems().add(e.toString());
					}
				}else{
					liste.getItems().add("aucun "+viewChoice.getValue());
				}
				break;
			case "Note":
				List<note>ln=new ArrayList<>();
				ln=Singleton.getInstance().getnotes();
				if(ln!=null && ln.size()>0){
					for(note e:ln){
					liste.getItems().add(e.toString());
					}
				}else{
					liste.getItems().add("aucun "+viewChoice.getValue());
				}
				break;
			default:
				break;
			}
		}else{
			liste.getItems().add("valeur de l'objet non choisie...");
		}
		
	}
	@FXML
	public void VoirElementParticulier(ActionEvent event) {
		if(viewChoice.getValue()!=null && viewId.getText()!=null && !viewId.getText().equals("")){
			String id=viewId.getText();
			liste.getItems().clear();
			switch (viewChoice.getValue()) {
			case "Emploi du temps":
				Emploi_du_temps emp=null;
				emp=Singleton.getInstance().getEmploi(Integer.valueOf(id));
				if(emp!=null){
					liste.getItems().add(emp.toString());
				}
				else{
					liste.getItems().add("aucun "+supprChoice.getValue()+" a la position "+viewId.getText());
				}
				
				
				break;
			case "Evenement":
				Evenement ev=null;
				ev=Singleton.getInstance().getEvent(Integer.valueOf(id));
				if(ev!=null){
					liste.getItems().add(ev.toString());
				}else{
					liste.getItems().add("aucun "+viewChoice.getValue()+" a la position "+viewId.getText());
				}
				
				break;
			default:
				liste.getItems().add("pas encore implémenté...");
				break;
			}
		}else{
			liste.getItems().add("valeur de l'element non choisie ou id non saisit");
		}
		
	}
	
	@FXML
	public void SupprimerElement(ActionEvent event) {
		if(supprId.getText()!=null && supprChoice.getValue()!=null && !supprId.getText().equals("")){
			String id=supprId.getText();
			if(id!=null){
				switch (supprChoice.getValue()) {
				case "Emploi du temps":
					Singleton.getInstance().delEmplois(Integer.valueOf(id));
					break;
				case "Evenement":
					Singleton.getInstance().delEvent(Integer.valueOf(id));
					break;
				case "Infos du Jour":
					Singleton.getInstance().delInfos(Integer.valueOf(id));
					break;
				case "Note":
					Singleton.getInstance().delNote(Integer.valueOf(id));
					break;
				case "Utilisateur":
					Singleton.getInstance().delUser(Integer.valueOf(id));
					break;
				default:
					break;
				}
			}else{
				liste.getItems().add("Id non saisit...");
			}
		}else{
			liste.getItems().add("valeur non choisie ou id non saisit...");
		}
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		viewChoice.getItems().add("Emploi du temps");
		viewChoice.getItems().add("Evenement");
		viewChoice.getItems().add("Infos du Jour");
		viewChoice.getItems().add("Note");
		viewChoice.getItems().add("Utilisateur");
		supprChoice.getItems().add("Emploi du temps");
		supprChoice.getItems().add("Evenement");
		supprChoice.getItems().add("Infos du Jour");
		supprChoice.getItems().add("Note");
		supprChoice.getItems().add("Utilisateur");
	}

	

}
