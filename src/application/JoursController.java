package application;



import java.net.URL;
import java.sql.Date;

import objets.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;



public class JoursController implements Initializable{
	@FXML
	private Label datedujour;
	public void initData (LocalDate d,InfosDuJour i){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");   
		datedujour.setText(dtf.format(d));
		infos.setText(i.getInformation());
	}
	public void initData (LocalDate d){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");   
		datedujour.setText(dtf.format(d));
	}
	@FXML
	private Button retour;
	@FXML
	private TextArea infos;
	@FXML
	public void retourner(ActionEvent event) {
		new Main().son2();
		Main.setPane(0);
	}
	
	public void enregistrer(ActionEvent event) {
		new Main().son3();
		String tabjour[]=datedujour.getText().split("/");
		String jour=tabjour[2]+"-"+tabjour[1]+"-"+tabjour[0];
		Date d=Date.valueOf(jour);
		
		
		try {
			InfosDuJour i=new InfosDuJour();
			i.setId(Singleton.getInstance().getInfos().size()+1);
			i.setInformation(infos.getText());
			i.setJour(d);
			i.setUser_id(Main.id);
			Singleton.getInstance().addInfos(i);
			new Main().son3();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}
	

}
