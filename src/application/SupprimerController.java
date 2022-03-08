package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import objets.Singleton;

public class SupprimerController implements Initializable{
	public void initData() {
		suppEven.setText(Singleton.getInstance().EvenementsAvider());
		suppHor.setText(Singleton.getInstance().HorairesAvider());
	}
	public void update(){
		suppEven.setText(Singleton.getInstance().EvenementsAvider());
		suppHor.setText(Singleton.getInstance().HorairesAvider());
	}
	@FXML
	TextArea suppEven;
	@FXML
	TextArea suppHor;
	@FXML
	public void viderE() {
		Singleton.getInstance().viderEvenements();
		update();
	}
	@FXML
	public void viderH() {
		Singleton.getInstance().viderHoraires();
		update();
	}
	@FXML
	public void retour(ActionEvent event) {
		new Main().son2();
		Main.setPane(0);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

	

}
