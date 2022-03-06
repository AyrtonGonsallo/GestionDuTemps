package application;
	
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import objets.Singleton;
import objets.Utilisateur;




public class AccueilController implements Initializable{
	private Utilisateur u;
	public void initData (Utilisateur user){
		u=user;
		utilisateurs.setText(utilisateurs.getText()+u.getLogin());
		ident.setText(ident.getText()+String.valueOf(user.getId()));
	}
	@FXML
	private Label ident;//id de l'utilisateur connecté
	@FXML
	private Label utilisateurs;//utilisateurs connectés
	@FXML
	private Button param;//aller vers parametres
	@FXML
	private Button suppr;//aller vers supprimer
	@FXML
	private Button actus;//aller vers actualités
	@FXML
	private Button cal;//aller vers le calendrier
	@FXML
	private Button today;
	@FXML
	private Button quitter;//aller vers le calendrier
	@FXML
	public void affichercal(ActionEvent event) {
		new Main().son1();
		Main.setPane(2);
	}
	public void afficheractus(ActionEvent event) {
		new Main().son1();
		Main.setPane(12);
	}
	public void supprimer(ActionEvent event) throws IOException {
		FXMLLoader l=new FXMLLoader();
		l.setLocation(getClass().getResource( "/application/Supprimer.fxml"));
		Object tableViewParent=l.load();
		application.SupprimerController sc=l.getController();
		sc.initData();
		Main.root.getChildren().add((AnchorPane)tableViewParent);
		Main.grid.set(14,(AnchorPane)tableViewParent);
        Main.setInd_c(14);
		Main.root.getChildren().remove(Main.grid.get(0));
		Main.setPane(14);
		new Main().son3();
	}
	@FXML
	public void aujourdhui(ActionEvent event) throws IOException {
		new Main().son1();;
		FXMLLoader le=new FXMLLoader();
		le.setLocation(getClass().getResource( "/application/Evenement.fxml"));
		Object tableViewParent=le.load();
		application.EvenementController ec=le.getController();
		Main.le=le;
		ec.initData(Main.id);
		Main.root.getChildren().add((Pane)tableViewParent);
		
		Main.grid.set(3,(Pane)tableViewParent);
        Main.setInd_c(3);
		Main.root.getChildren().remove(Main.grid.get(0));
	}
	@FXML
	public void quitter(ActionEvent event) {
		new Main().son2();
		Platform.exit();
	}
	@FXML
	public void save(ActionEvent event) {
		Thread t = new Thread() {
		      public void run() {
		        Singleton.getInstance().save();
		      }
		    };
		    t.start();
		
		new Main().son2();
		
	}
	@FXML
	public void button_param(ActionEvent event) {
		new Main().son1();;
		Main.setPane(1);
	}
	public void deconnecter(ActionEvent event) {
		new Main().son1();
		JOptionPane.showMessageDialog(null, "deconnecté");
		Main.setPane(6);
	}
	
	
	public void initialize(URL arg0, ResourceBundle rb) {
		
		
	}

	
	
}