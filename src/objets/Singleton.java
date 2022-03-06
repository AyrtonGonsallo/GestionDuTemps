package objets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.sql.Time;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Singleton
{
    /** Constructeur privé */
    private Singleton()
    {
    	this.users=new ArrayList<Utilisateur>();
    	this.commentaires=new ArrayList<Commentaire>();
    	this.infos=new ArrayList<InfosDuJour>();
    	this.emplois=new ArrayList<Emploi_du_temps>();
    	this.events=new ArrayList<Evenement>();
    	Thread t = new Thread() {
		      public void run() {
		        try{
		        	load();
		        }catch (Exception e) {
		        	e.printStackTrace();
		        }
		      }
		    };
		    t.start();
    	
    }
     
    /** Instance unique non préinitialisée */
    private static Singleton INSTANCE = null;
    
    public List<Commentaire> getCommentaires() {
		return commentaires;
	}


	public void addCommentaire(Commentaire e) {
		this.commentaires.add(e);
	}
	public void delCommentaire(int pos) {
		this.commentaires.remove(pos);
	}
	


	public List<InfosDuJour> getInfos() {
		return infos;
	}
	public InfosDuJour getInfosDuJour(int user_id,Date jour) {
		InfosDuJour res=null;
		for(InfosDuJour i:this.infos){
			if(i.getId()==user_id && i.getJour().equals(jour)){
				res=i;
			}
		}
		return res;
	}


	public void addInfos(InfosDuJour e) {
		this.infos.add(e);
	}
	public void delInfos(int pos) {
		this.infos.remove(pos);
	}


	public List<Emploi_du_temps> getEmplois() {
		return emplois;
	}
	
	public double getTotalEmploisUser(int user_id) {
		double i=0;
		for(Emploi_du_temps e:this.emplois){
			if(e.getUser_id()==user_id){
				i++;
			}
		}
		return i;
	}
	public double getTotalEmploisFiniUser(int user_id) {
		double i=0;
		for(Emploi_du_temps e:this.emplois){
			if(e.getUser_id()==user_id && e.getStatus().equals(Status.achevée.toString())){
				i++;
			}
		}
		return i;
	}
	public ObservableList<Emploi_du_temps> getObservableEmplois() {
		ObservableList<Emploi_du_temps> list = FXCollections.observableArrayList();
		for (Emploi_du_temps e:this.emplois){
			list.add(e);
		}
		return list;
	}


	public void addEmplois(Emploi_du_temps e) {
		this.emplois.add(e);
	}
	public void updateEmplois(Emploi_du_temps e) {
		int pos=0;
		List<Emploi_du_temps>lem=this.emplois;
		for( int i = 0; i < lem.size(); i++ ){
			Emploi_du_temps em=lem.get(i);
			if(em.equals(e)){
				pos=i;
				break;
			}
		}
		this.emplois.remove(pos);
		this.emplois.add(pos, e);
	}
	public void delEmplois(Emploi_du_temps e) {
		List<Emploi_du_temps>lem=this.emplois;
		for( int i = 0; i < lem.size(); i++ ){
			Emploi_du_temps em=lem.get(i);
			if(em.equals(e)){
				this.emplois.remove(i);
				i--;
			}
		}
	}


	public List<Evenement> getEvents() {
		return events;
	}
	public ObservableList<Evenement> getObservableEvents() {
		ObservableList<Evenement> list = FXCollections.observableArrayList();
		for (Evenement e:this.events){
			list.add(e);
		}
		return list;
	}


	public void addEvent(Evenement e) {
		this.events.add(e);
	}
	public void delEvent(Evenement e) {
		List<Evenement>lem=this.events;
		for( int i = 0; i < lem.size(); i++ ){
			Evenement em=lem.get(i);
			if(em.equals(e)){
				this.events.remove(i);
				i--;
			}
		}
	}

	public List<Utilisateur> getUsers() {
		return users;
	}
	public Utilisateur getUser(int id) {
		Utilisateur user=null;
		for(Utilisateur u:this.users){
			if(u.getId()==id){
				user=u;
			}
		}
		return user;
	}


	public void addUser(Utilisateur e) {
		this.users.add(e);
	}
	public void delUser(int pos) {
			this.users.remove(pos);
	}
	public double calcRatio(int uid) {
		double total=Singleton.getInstance().getTotalEmploisUser(uid),faites=Singleton.getInstance().getTotalEmploisFiniUser(uid);
		double p=(faites/total)*100;
		return p;
		
	}
	
	public String EvenementsAvider(){
		Date d = new Date(Calendar.getInstance().getTime().getTime());
		
		String s="les évenements suivants peuvent etres supprimés:\n";
		for(Evenement e: getInstance().getEvents()){
			if(e.getUser_id()==Main.id && ChronoUnit.DAYS.between(e.getDate().toLocalDate(), d.toLocalDate())>=2){
				s+=e.getTitre()+" ajouté le "+e.getDate()+"\n";
			}
		}
		
		return s;
	}
	public int viderEvenements(){
		System.out.println("avant: "+this.events);
		Date d = new Date(Calendar.getInstance().getTime().getTime());
		List<Evenement>leV=getInstance().getEvents();
		for(int i=0;i<leV.size();i++){
			Evenement e=leV.get(i);
			if(e.getUser_id()==Main.id && ChronoUnit.DAYS.between(e.getDate().toLocalDate(), d.toLocalDate())>=2){
				this.delEvent(e);
			}
		}
		System.out.println("apres: "+this.events);
		return 0;
	}
	public String HorairesAvider(){
		long now = System.currentTimeMillis();
		Time t3 = new Time(now);
		Time t = Time.valueOf(t3.toString());
		String s="les horaires suivants sont passés et peuvent etres supprimés:\n";
		List<Emploi_du_temps> leM=this.emplois;
		for( Emploi_du_temps em:leM ){
			if(em.getUser_id()==Main.id && (em.getHorraire_fin().compareTo(t))<0){
				s+=em.getTitre()+" commencant a "+em.getHorraire_debut()+" et finissant a "+em.getHorraire_fin()+"\n";
			}
		}
		
		return s;
	}
	
	public int viderHoraires(){
		System.out.println("avant: "+this.emplois);
		long now = System.currentTimeMillis();
		Time t3 = new Time(now);
		Time t = Time.valueOf(t3.toString());
		List<Emploi_du_temps>leM=getInstance().getEmplois();
		for( int i = 0; i < leM.size(); i++ ){
			Emploi_du_temps em=leM.get(i);
			if(em.getUser_id()==Main.id && (em.getHorraire_fin().compareTo(t))<0){
				this.delEmplois(em);
			}
		}
		System.out.println("apres: "+this.emplois);
		return 0;
	}
	

	private List<Commentaire> commentaires;
    private List<InfosDuJour>infos;
    private List<Emploi_du_temps>emplois;
    private List<Evenement>events;
    private List<Utilisateur>users;
    

    /** Point d'accès pour l'instance unique du singleton */
    public static Singleton getInstance()
    {           
        if (INSTANCE == null)
        {   INSTANCE = new Singleton(); 
        }
        return INSTANCE;
    }
    public void save(){
    	
		try {
			File f1=new File("commentaires.txt");
			f1.createNewFile();
			FileOutputStream commentaires= new FileOutputStream(f1);
			
			ObjectOutputStream OutCommemtaires = new ObjectOutputStream(commentaires);
			//for(Commentaire c:this.commentaires){
				OutCommemtaires.writeObject(this.commentaires);
			//}
			OutCommemtaires.close();
			File f2=new File("utilisateurs.txt");
			f2.createNewFile();
			FileOutputStream utilisateurs= new FileOutputStream(f2);
			ObjectOutputStream OutUtilisateurs = new ObjectOutputStream(utilisateurs);
			//for(Utilisateur u:this.users){
				OutUtilisateurs.writeObject(this.users);
			//}
			OutUtilisateurs.close();
			File f3=new File("infosDuJour.txt");
			f3.createNewFile();
			FileOutputStream infosDuJour= new FileOutputStream(f3);
			ObjectOutputStream OutInfosDuJour = new ObjectOutputStream(infosDuJour);
			//for(InfosDuJour i:this.infos){
				OutInfosDuJour.writeObject(this.infos);
			//}
			OutInfosDuJour.close();
			File f4=new File("evenements.txt");
			f4.createNewFile();
			FileOutputStream evenements= new FileOutputStream(f4);
			ObjectOutputStream OutEvenements = new ObjectOutputStream(evenements);
			//for(Evenement e:this.events){
				OutEvenements.writeObject(this.events);
			//}
			OutEvenements.close();
			File f5=new File("emplois.txt");
			f5.createNewFile();
			FileOutputStream emplois= new FileOutputStream(f5);
			ObjectOutputStream OutEmplois = new ObjectOutputStream(emplois);
			//for(Emploi_du_temps e:this.emplois){
				OutEmplois.writeObject(this.emplois);
			//}
			OutEmplois.close();
			Thread.sleep(2000);
			JOptionPane.showMessageDialog(null, "sauvegarde faite!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    public void load(){
    	try {
			FileInputStream commentaires= new FileInputStream(new File("commentaires.txt"));
			ObjectInputStream InCommemtaires = new ObjectInputStream(commentaires);
			
				this.commentaires=(List<Commentaire>) InCommemtaires.readObject();
			
			InCommemtaires.close();
			FileInputStream utilisateurs= new FileInputStream(new File("utilisateurs.txt"));
			ObjectInputStream InUtilisateurs = new ObjectInputStream(utilisateurs);
			
				this.users=(List<Utilisateur>) InUtilisateurs.readObject();
			
			InUtilisateurs.close();
			FileInputStream infosDuJour= new FileInputStream(new File("infosDuJour.txt"));
			ObjectInputStream InInfosDuJour = new ObjectInputStream(infosDuJour);
			
				this.infos=(List<InfosDuJour>) InInfosDuJour.readObject();
			
			InInfosDuJour.close();
			FileInputStream evenements= new FileInputStream(new File("evenements.txt"));
			ObjectInputStream InEvenements = new ObjectInputStream(evenements);
			
				this.events=(List<Evenement>) InEvenements.readObject();
			
			InEvenements.close();
			FileInputStream emplois= new FileInputStream(new File("emplois.txt"));
			ObjectInputStream InEmplois = new ObjectInputStream(emplois);
			
				this.emplois=(List<Emploi_du_temps>) InEmplois.readObject();
			
			InEmplois.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}