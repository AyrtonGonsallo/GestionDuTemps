package application;
	
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import login.MysqlConnect;





public class CalendrierController implements Initializable{
	@FXML
	private Label msg;//aller vers le menu
	@FXML
	private AnchorPane jan;//aller vers le menu
	@FXML
	private AnchorPane fev;//aller vers le menu
	@FXML
	private AnchorPane mar;//aller vers le menu
	@FXML
	private AnchorPane avr;//aller vers le menu
	@FXML
	private AnchorPane mai;//aller vers le menu
	@FXML
	private AnchorPane jui;//aller vers le menu
	@FXML
	private AnchorPane jul;//aller vers le menu
	@FXML
	private AnchorPane aou;//aller vers le menu
	@FXML
	private AnchorPane sep;//aller vers le menu
	@FXML
	private AnchorPane oct;//aller vers le menu
	@FXML
	private AnchorPane nov;//aller vers le menu
	@FXML
	private AnchorPane dec;//aller vers le menu
	
	@FXML
	private TextField an;//aller vers le menu
	@FXML
	private Button valider;//aller vers le menu
	@FXML
	private Button retour;//aller vers le menu
	@FXML
	public void afficher(ActionEvent event) {
		new Main().son1();;
		int année=2021;
		//recuperer l'année
		try {
			année=Integer.parseInt(an.getText());
		}
		catch(Exception ex) {
			jan.getChildren().removeAll(jan.getChildren());
			fev.getChildren().removeAll(fev.getChildren());
			mar.getChildren().removeAll(mar.getChildren());
			avr.getChildren().removeAll(avr.getChildren());
			mai.getChildren().removeAll(mai.getChildren());
			jui.getChildren().removeAll(jui.getChildren());
			jul.getChildren().removeAll(jul.getChildren());
			aou.getChildren().removeAll(aou.getChildren());
			sep.getChildren().removeAll(sep.getChildren());
			oct.getChildren().removeAll(oct.getChildren());
			nov.getChildren().removeAll(nov.getChildren());
			dec.getChildren().removeAll(dec.getChildren());
			msg.setText("Ce n'est pas un nombre, veuillez appuyer sur Quitter");
			
		}
		if(année>999999||année<1970) {
			jan.getChildren().removeAll(jan.getChildren());
			fev.getChildren().removeAll(fev.getChildren());
			mar.getChildren().removeAll(mar.getChildren());
			avr.getChildren().removeAll(avr.getChildren());
			mai.getChildren().removeAll(mai.getChildren());
			jui.getChildren().removeAll(jui.getChildren());
			jul.getChildren().removeAll(jul.getChildren());
			aou.getChildren().removeAll(aou.getChildren());
			sep.getChildren().removeAll(sep.getChildren());
			oct.getChildren().removeAll(oct.getChildren());
			nov.getChildren().removeAll(nov.getChildren());
			dec.getChildren().removeAll(dec.getChildren());
			msg.setText("vous avez entrez une mauvaise année, veuillez appuyer sur Quitter");
		}
		//date initiale
		LocalDate date = LocalDate.of(année, 1,1);
		// Creation des mois et des jours
		Vector<AnchorPane>mois=new Vector<AnchorPane>();
		mois.add(jan);
		mois.add(fev);
		mois.add(mar);
		mois.add(avr);
		mois.add(mai);
		mois.add(jui);
		mois.add(jul);
		mois.add(aou);
		mois.add(sep);
		mois.add(oct);
		mois.add(nov);
		mois.add(dec);
		for(int i=0;i<12;i++) {
			//effacer les valeurs prealables
			try {
				mois.elementAt(i).getChildren().removeAll(mois.elementAt(i).getChildren());
			}
			catch(Exception ex2) {
				
			}
			//le conteneur pour chaque mois
			GridPane panneau_jour=new GridPane();
			//recuperer le nombre de jours du mois
			int ndj=date.lengthOfMonth();
			for(int jours=0,ligne=0;jours<ndj;jours++) {
				//---Creation des jours
				VBox v=new VBox();
				Text numJour=new Text(String.valueOf(date.getDayOfMonth()));
				numJour.setFill(Paint.valueOf("000000"));
				numJour.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20));
				v.getChildren().add(numJour);
				LocalDate d=date;
				v.setOnMouseClicked(e -> {
					
					try {
						loadFootage(d);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				v.setBorder(new Border(new BorderStroke(Paint.valueOf("FFFFFF"), BorderStrokeStyle.DASHED, new CornerRadii(5), new BorderWidths(1))));
				v.setPrefHeight(30.0);
				v.setPrefWidth(40.0);
				if(jours>0&&jours%6==0) {
					ligne++;
					
				}
				if(jours%6==0) {
					panneau_jour.add(v,0,ligne,1,1);
				}
				else {
					panneau_jour.add(v,jours%6,ligne,1,1);
				}
				GridPane.setMargin(v, new Insets(3));
				//changer de jour
				date=date.plusDays(1);
			}
			mois.elementAt(i).getChildren().add(panneau_jour);
		}
	}
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	public void loadFootage(LocalDate d) throws IOException {
		conn = MysqlConnect.ConnectDb();
		String sql = "select * from infosdujour where jour = ? and user_id = ? ";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");   
		Date dateSql=Date.valueOf(dtf.format(d));
		try {
			pst = conn.prepareStatement(sql);
			pst.setDate(1,dateSql );
			pst.setInt(2, Main.id);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				new Main().son1();;
				Infosdujour i=new Infosdujour(rs.getString("information"),rs.getDate("jour"),rs.getInt("user_id"));
				FXMLLoader l=new FXMLLoader();
				l.setLocation(getClass().getResource( "/application/jours.fxml"));
				Object tableViewParent=l.load();
				application.JoursController jc=l.getController();
				jc.initData(d,i);
				Main.root.getChildren().add((AnchorPane)tableViewParent);
				Main.grid.set(11,(AnchorPane)tableViewParent);
		        Main.setInd_c(11);
				Main.root.getChildren().remove(Main.grid.get(2));
				Main.setPane(11);
			}else
			{
				new Main().son1();;
				FXMLLoader l=new FXMLLoader();
				l.setLocation(getClass().getResource( "/application/jours.fxml"));
				Object tableViewParent=l.load();
				application.JoursController jc=l.getController();
				jc.initData(d);
				Main.root.getChildren().add((AnchorPane)tableViewParent);
				Main.grid.set(11,(AnchorPane)tableViewParent);
		        Main.setInd_c(11);
				Main.root.getChildren().remove(Main.grid.get(2));
				Main.setPane(11);
			}
			}
		catch(SQLException e) {
			
		}
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