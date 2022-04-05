package application;
	
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import objets.note;
import objets.Singleton;
import objets.Utilisateur;




public class NotesController implements Initializable{

	@FXML
	private ListView<String>liste;
	@FXML
	private TextArea com;//aller vers le menu
	@FXML
	private Button retour;//aller vers le menu
	@FXML
	private Button envoyer;
	@FXML
	private Button infos;
	@FXML
	public void retour(ActionEvent event) {
		new Main().son2();
		Main.setPane(0);
	}
	public void allerinfos(ActionEvent event) throws IOException {
		Utilisateur u=Singleton.getInstance().getUser(Main.id);
		try {
			
			if(u!=null) {
				new Main().son1();
				FXMLLoader l=new FXMLLoader();
				l.setLocation(getClass().getResource( "/login/InfosCompte.fxml"));
				Object tableViewParent=l.load();
				login.InfosCompteController jc=l.getController();
				jc.initData(u);
				Main.root.getChildren().add((AnchorPane)tableViewParent);
				Main.grid.set(13,(AnchorPane)tableViewParent);
		        Main.setInd_c(13);
				Main.root.getChildren().remove(Main.grid.get(1));
				Main.setPane(13);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void envoyer(ActionEvent event) {
		new Main().son3();
		addCom();
	}
	Date d = new Date(Calendar.getInstance().getTime().getTime());
	private void addCom() {
		
		
		try {
			note c=new note();
			List<note>ln=Singleton.getInstance().getnotes();
			if(ln.size()==0){
				c.setId(1);
			}else{
				c.setId(ln.get(ln.size()-1).getId()+1);
			}
			
			c.setDate(d);
			c.setTexte(com.getText());
			c.setUser_id(Main.id);
			Singleton.getInstance().addnote(c);
			updateView();
			
			JOptionPane.showMessageDialog(null, "Votre note a été ajouté");
			new Main().son3();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		liste.getItems().add("initialisaton...");
		
	}
	
	public void updateView(){
		liste.getItems().clear();
		List<note>listec=Singleton.getInstance().getUsernotes(Main.id);
		for(note c: listec){
			liste.getItems().add(c.getTexte()+"   le "+c.getDate().toString());
		}
				
	}

	
	
}