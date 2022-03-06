package login;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import objets.Singleton;

public class AccueilController implements Initializable{
	@FXML
	private Button quitter;
	@FXML
	private Button seconnter_button;
	@FXML
	private Button sinscrire_button;
	@FXML
	public void connexion(ActionEvent event) {
		new Main().son1();
		Main.setPane(8);
	}
	@FXML
	public void inscription(ActionEvent event) {
		new Main().son1();
		Main.setPane(7);
	}
	@FXML
	public void quitter(ActionEvent event) {
		new Main().son2();
		Platform.exit();
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Singleton.getInstance();
		
	}

}
