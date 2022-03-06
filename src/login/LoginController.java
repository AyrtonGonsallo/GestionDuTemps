package login;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import objets.Singleton;
import objets.Utilisateur;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class LoginController implements Initializable{
	
	@FXML
	private Button connect;
	@FXML
	private Button retour;
	@FXML
	private TextField txt_login;
	@FXML
	private PasswordField txt_password;
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	@FXML
	public void connexion(ActionEvent e) {
		//Si les champs sont bien renseignés, alors on va aller directement sur l'application
		//Si les informations sont erronées, alors on aura la page suivante:
		//Main.setPane(10);
		new Main().son1();;
		Main.setPane(0);
	}
	@FXML
	public void retour(ActionEvent event) {
		new Main().son2();
		Main.setPane(6);
	}
	// passer  l'utilisateur a l'accueil
	
	
	
	@FXML
	private void Login(ActionEvent event) throws Exception{
		boolean existe=false;
		try {
			for(Utilisateur u: Singleton.getInstance().getUsers()){

				if(u.getLogin().equals(txt_login.getText()) && u.getMotdepasse().equals(txt_password.getText())) {
					new Main().son1();
					//JOptionPane.showMessageDialog(null, "username and password are correct!");
					Main.root.getChildren().remove(Main.grid.get(8));
					FXMLLoader l=new FXMLLoader();
					l.setLocation(getClass().getResource( "/application/accueil.fxml"));
					Object tableViewParent=l.load();
					application.AccueilController ac=l.getController();
					//Scene tableViewScene=new Scene(tableViewParent);
					existe=true;
					Main.setUser(u.getLogin());
					Main.id=u.getId();
					ac.initData(u);
					Main.root.getChildren().add((Pane)tableViewParent);
					Main.grid.set(0,(Pane)tableViewParent);
			        Main.setInd_c(0);
					Main.root.getChildren().remove(Main.grid.get(6));
					break;
				}
			}
			if(existe==false){
				JOptionPane.showMessageDialog(null, "Invalide");
				Main.setPane(10);
			}
		
			
				
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
