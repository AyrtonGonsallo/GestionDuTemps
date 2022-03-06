package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ActusController implements Initializable{

	@FXML
	private WebView infos;
	@FXML
	private Label date;
	@FXML
	private Button retour;
	@FXML
	public void retourner(ActionEvent event) {
		new Main().son2();
		Main.setPane(0);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		WebEngine engine=infos.getEngine();
		engine.load("https://www.lemonde.fr/actualite-en-continu/");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String []jours= {"dimanche","lundi","mardi","mercredi","jeudi","vendredi","samedi"};
		String[] mois= {"janvier","fevrier","mars","avril","mai","juin","juillet","aout","septembre","octobre","novembre","decembre"};
		date.setText("Aujourd'hui le "+jours[now.getDayOfWeek().getValue()]+" "+now.getDayOfMonth()+" "+mois[now.getMonthValue()]+" "+now.getYear()+" il est "+dtf.format(now));
		
		
	}

}
