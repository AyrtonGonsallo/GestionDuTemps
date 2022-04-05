package login;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import objets.Singleton;
import objets.Utilisateur;



public class InscriptionController implements Initializable {
	@FXML
	private Button confirm;
	@FXML
	private Button retour;
	@FXML
	private TextField user_id_up;
	@FXML
	private TextField age;
	@FXML
	private TextField email;
	@FXML
	private PasswordField user_password1;
	@FXML
	private PasswordField user_password2;
	
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	
	@FXML
	public void confirmation(ActionEvent event) {
		new Main().son1();;
		//si l'utilisateur remplit correctement les champs, il va se diriger dans la page login ---->Main.setPane(2)
		//si l'utilisateur commet une erreur sur les mots de passe, alors 
		Main.setPane(9);
	}
	@FXML
	public void retour(ActionEvent event) {
		new Main().son2();
		Main.setPane(6);
	}
	
	@FXML
	public void add_users(ActionEvent event) {
		Date d = new Date(Calendar.getInstance().getTime().getTime());

		try {
			if(user_password1.getText().equals(user_password2.getText())){
			Utilisateur u=new Utilisateur();
			List<Utilisateur>users=Singleton.getInstance().getUsers();
			if(users.size()==0){
				u.setId(1);
			}else{
				u.setId(users.get(users.size()-1).getId()+1);
			}
			
			u.setLogin(user_id_up.getText());
			u.setMotdepasse( user_password1.getText());
			u.setEmail( email.getText());
			u.setAge(Integer.valueOf(age.getText()));
			u.setDate_creation( d);
			Singleton.getInstance().addUser(u);
			JOptionPane.showMessageDialog(null, "saved");
			Main.setPane(8);
			}
			else {
				Main.setPane(9);
		}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
