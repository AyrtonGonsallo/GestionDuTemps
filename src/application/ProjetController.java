package application;


import java.net.URL;
import objets.*;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;




import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;


public class ProjetController implements Initializable {
	@FXML
	Label date;
	@FXML
	Label ratio;
	@FXML
	ObservableList<Projet> listProjets;
	@FXML
	private TableView<Projet> table_horaire;
	@FXML
	private TableColumn<Projet,String> col_desc;
	@FXML
	private TableColumn<Projet,String> col_titre;
	@FXML
	private TableColumn<Projet,String> col_statut;
	@FXML
	private Button accueil_button;
	@FXML
	private Button add_horaire_button;
	
	@FXML
	public void nouveauHoraire(ActionEvent event) {
		new Main().son1();;
		Main.setPane(17);
	}
	@FXML
	public void retour(ActionEvent event) {
		new Main().son2();
		Main.setPane(0);
	}
	//mettre a jour le status

	public int changeStatus(@SuppressWarnings("rawtypes") CellEditEvent edittedCell)
    {
		new Main().son3();
		String val=edittedCell.getNewValue().toString();
		if(val.equals("achevée")||val.equals("inachevée")) {
			Projet even =  table_horaire.getSelectionModel().getSelectedItem();
			even.setStatus(edittedCell.getNewValue().toString() );
			Singleton.getInstance().updateProjets(even);
			initData(Main.id);
			ratio.setText(Singleton.getInstance().calcRatioProjets(Main.id) +"%");
			return 0;
		}
        JOptionPane.showMessageDialog(null, "entrez achevée ou inachevée");
			return -1;
    }
	//calculer le ratio de taches
	//private Utilisateur u;
	public void initData (int id){
		DecimalFormat d=new DecimalFormat(".##");
		ratio.setText(Singleton.getInstance().calcRatioProjets(id) +"%");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		date.setText(dtf.format(now));
		col_titre.setCellValueFactory(new PropertyValueFactory<Projet,String>("titre"));
		col_desc.setCellValueFactory(new PropertyValueFactory<Projet,String>("description"));
		col_statut.setCellValueFactory(new PropertyValueFactory<Projet,String>("status"));
		listProjets = Singleton.getInstance().getObservableProjets(Main.id);
		table_horaire.setItems(listProjets);
		table_horaire.setEditable(true);
		col_statut.setCellFactory(TextFieldTableCell.forTableColumn());
		
	}
	public void updateTable() {
		listProjets = Singleton.getInstance().getObservableProjets(Main.id);
		table_horaire.setItems(listProjets);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle rb) {
		ratio.setText(Singleton.getInstance().calcRatioProjets(Main.id) +"%");
		
	}
	
	
}
