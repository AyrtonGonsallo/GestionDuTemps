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
	String []citations={"�Je n'est jamais rev� du succes. J'ai travaill� pour l'avoir� - Este� Lauder",
			"�Si j'ai vu si loin, c'est que j'�tais mont� sur des �paules de g�ants.� - Sir Isaac Newton",
			"�La vie, c'est comme une bicyclette, il faut avancer pour ne pas perdre l'�quilibre.� - Albert Einstein ",
			"� L'intelligence est la capacit� de s'adapter au changement.� - Stephen Hawking",
			"�J'ai �t� chanceux. J'ai eu quatre ans de bonheur. Certains personnes n'ont seulement quatre jours.� - Harold Finch",
			"�Quiconque regarde le monde comme si c'�tait une partie d'�checs m�rite de perdre.� - Harold Finch",
			"�Le temps ne cicatrise pas les outrages du temps.� - Alain Bosquet",
			"�Mieux vaut un temps d'�t� stable, plut�t qu'un temps d�testable.� - James Stuart Mills ",
			"�Qui dit c�r�bral ne dit pas n�cessairement intelligent. Repassez �a de temps en temps.� - Elie Feron",
			"�La grande affaire et la seule qu�on doive avoir, c�est de vivre heureux.� - Voltaire",
			"�L�univers m�embarrasse et je ne puis songer que cette horloge existe et n�ait pas d�horloger.� - Voltaire ",
			"�L'�me d�r�gl�e est comme un tonneau perc� � cause de sa nature insatiable.� - Socrate",
			"�Les yeux de l�esprit ne commencent � �tre per�ants que quand ceux du corps commencent � baisser.� - Platon",
			"�La victoire sur soi est la plus grande des victoires.� - Platon",
			"�On peut en savoir plus sur quelqu'un en une heure de jeu qu'en une ann�e de conversation.� - Platon",
			"�Le plus grand bien qui puisse �tre dans un �tat est d�avoir de vrais philosophes.� - Descartes",
			"�Qui vit de combattre un ennemi a tout int�r�t de le laisser en vie.� - Nietzsche",
			"�La folie est quelque chose de rare chez l'individu ; elle est la r�gle pour les groupes, les partis, les peuples, les �poques. � - Nietzsche"
	};
	
	private Utilisateur u;
	public void initData (Utilisateur user){
		u=user;
		utilisateurs.setText(utilisateurs.getText()+u.getLogin());
		ident.setText(ident.getText()+String.valueOf(user.getId()));
		citation.setText(citations[((int)(Math.random()*20))%citations.length]);
	}
	@FXML
	private Label ident;//id de l'utilisateur connect�
	@FXML
	private Label citation;
	@FXML
	private Label utilisateurs;//utilisateurs connect�s
	@FXML
	private Button param;//aller vers parametres
	@FXML
	private Button suppr;//aller vers supprimer
	@FXML
	private Button actus;//aller vers actualit�s
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
	public void espaceAdmin(ActionEvent event) {
		new Main().son2();
		Main.setPane(15);
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
		JOptionPane.showMessageDialog(null, "deconnect�");
		Main.setPane(6);
	}
	
	
	public void initialize(URL arg0, ResourceBundle rb) {
		citation.setText(citations[((int)(Math.random()*20))%citations.length]);
		
	}

	
	
}