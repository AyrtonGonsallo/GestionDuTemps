package objets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
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
    	this.notes=new ArrayList<note>();
    	this.infos=new ArrayList<InfosDuJour>();
    	this.emplois=new ArrayList<Emploi_du_temps>();
    	this.events=new ArrayList<Evenement>();
    	this.projets=new ArrayList<Projet>();
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
    
    public List<note> getUsernotes(int userid) {
    	List<note>liste=new ArrayList<>();
    	Thread t=new Thread(){
			public void run() {
				for(note c: notes){
		    		if(c.getUser_id()==userid){
		    			liste.add(c);
		    		}
				}
			};
		};
		t.start();
    	try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return liste;
	}
    public List<note> getnotes() {
    	
		return this.notes;
	}


	public void addnote(note e) {
		this.notes.add(e);
	}
	public void delnote(int pos) {
		this.notes.remove(pos);
	}
	
	public List<Projet> getProjets() {
		return projets;
	}

	public List<InfosDuJour> getInfos() {
		return infos;
	}
	public InfosDuJour getInfosDuJour(int user_id,String jour) {
		InfosDuJour res=null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		for(InfosDuJour i:this.infos){
			if(i.getUser_id()==user_id && format.format(i.getJour()).equals(jour)){
				res=i;
				break;
			}
		}
		return res; 
	}


	public void addInfos(InfosDuJour e) {
		boolean update=false;
		for(InfosDuJour info:this.infos){
			if(info.getJour().toString().equals(e.getJour().toString())){
				info.setInformation(e.getInformation());
				update=true;
			}
		}
		if(update==false){
			this.infos.add(e);
		}
		
	}


	public List<Emploi_du_temps> getEmplois() {
		return emplois;
	}
	public Emploi_du_temps getEmploi(int id) {
		Emploi_du_temps emp = null;
		for (Emploi_du_temps e:this.emplois){
			if(e.getId()==id){
				emp=e;
				break;
			}
			
		}
		return emp;
	}
	public Evenement getEvent(int id) {
		Evenement ev = null;
		for (Evenement e:this.events){
			if(e.getId()==id){
				ev=e;
				break;
			}
			
		}
		return ev;
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
	public ObservableList<Emploi_du_temps> getObservableEmplois(int uid) {
		ObservableList<Emploi_du_temps> list = FXCollections.observableArrayList();
		for (Emploi_du_temps e:this.emplois){
			if(e.getUser_id()==uid){
				list.add(e);
			}
			
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
				
				break;
			}
		}
	}
	public void delEmplois(int id) {
		List<Emploi_du_temps>lem=this.emplois;
		for( int i = 0; i < lem.size(); i++ ){
			Emploi_du_temps em=lem.get(i);
			if(em.getId()==id){
				this.emplois.remove(i);
				JOptionPane.showMessageDialog(null, "supression faite!");
				break;
			}
		}
	}
	//projets
	public Projet getProjet(int id) {
		Projet  ev = null;
		for (Projet  e:this.projets){
			if(e.getId()==id){
				ev=e;
				break;
			}
			
		}
		return ev;
	}
	public double getTotalProjetsUser(int user_id) {
		double i=0;
		for(Projet e:this.projets){
			if(e.getUser_id()==user_id){
				i++;
			}
		}
		return i;
	}
	public double getTotalProjetsFiniUser(int user_id) {
		double i=0;
		for(Projet e:this.projets){
			if(e.getUser_id()==user_id && e.getStatus().equals(Status.achevée.toString())){
				i++;
			}
		}
		return i;
	}
	public ObservableList<Projet> getObservableProjets(int uid) {
		ObservableList<Projet> list = FXCollections.observableArrayList();
		for (Projet e:this.projets){
			if(e.getUser_id()==uid){
				list.add(e);
			}
			
		}
		return list;
	}


	public void addProjets(Projet e) {
		this.projets.add(e);
	}
	public void updateProjets(Projet e) {
		int pos=0;
		List<Projet>lem=this.projets;
		for( int i = 0; i < lem.size(); i++ ){
			Projet em=lem.get(i);
			if(em.equals(e)){
				pos=i;
				break;
			}
		}
		this.projets.remove(pos);
		this.projets.add(pos, e);
	}
	public void delProjets(Projet e) {
		List<Projet>lem=this.projets;
		for( int i = 0; i < lem.size(); i++ ){
			Projet em=lem.get(i);
			if(em.equals(e)){
				this.projets.remove(i);
				
				break;
			}
		}
	}
	public void delProjets(int id) {
		List<Projet>lem=this.projets;
		for( int i = 0; i < lem.size(); i++ ){
			Projet em=lem.get(i);
			if(em.getId()==id){
				this.projets.remove(i);
				JOptionPane.showMessageDialog(null, "supression faite!");
				break;
			}
		}
	}
	
	public void delInfos(int id) {
		List<InfosDuJour>lem=this.infos;
		for( int i = 0; i < lem.size(); i++ ){
			InfosDuJour em=lem.get(i);
			if(em.getId()==id){
				this.infos.remove(i);
				JOptionPane.showMessageDialog(null, "supression faite!");
				break;
			}
		}
	}
	public void delUser(int id) {
		List<Utilisateur>lem=this.users;
		for( int i = 0; i < lem.size(); i++ ){
			Utilisateur em=lem.get(i);
			if(em.getId()==id){
				this.users.remove(i);
				JOptionPane.showMessageDialog(null, "supression faite!");
				break;
			}
		}
	}
	public void delNote(int id) {
		List<note>lem=this.notes;
		for( int i = 0; i < lem.size(); i++ ){
			note em=lem.get(i);
			if(em.getId()==id){
				this.notes.remove(i);
				JOptionPane.showMessageDialog(null, "supression faite!");
				break;
			}
		}
	}

	public List<Evenement> getEvents() {
		return events;
	}
	public ObservableList<Evenement> getObservableEvents(int id) {
		ObservableList<Evenement> list = FXCollections.observableArrayList();
		for (Evenement e:this.events){
			if(e.getUser_id()==id){
				list.add(e);
			}
			
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
				break;
			}
		}
	}
	public void delEvent(int id) {
		List<Evenement>lem=this.events;
		for( int i = 0; i < lem.size(); i++ ){
			Evenement em=lem.get(i);
			if(em.getId()== id){
				this.events.remove(i);
				JOptionPane.showMessageDialog(null, "supression faite!");
				break;
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
	public double calcRatio(int uid) {
		double total=Singleton.getInstance().getTotalEmploisUser(uid),faites=Singleton.getInstance().getTotalEmploisFiniUser(uid);
		double p=(faites/total)*100;
		if(total==0.0){
			return 0.0;
		}
		return p;
		
	}
	public double calcRatioProjets(int uid) {
		double total=Singleton.getInstance().getTotalProjetsUser(uid),faites=Singleton.getInstance().getTotalProjetsFiniUser(uid);
		double p=(faites/total)*100;
		if(total==0.0){
			return 0.0;
		}
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
		Date d = new Date(Calendar.getInstance().getTime().getTime());
		List<Evenement>leV=getInstance().getEvents();
		for(int i=0;i<leV.size();i++){
			if(this.events.size()>i){
				Evenement e=leV.get(i);
				if(e.getUser_id()==Main.id && ChronoUnit.DAYS.between(e.getDate().toLocalDate(), d.toLocalDate())>=2){
					this.delEvent(e);
					i--;
				}
			}
			
		}
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
		long now = System.currentTimeMillis();
		Time t3 = new Time(now);
		Time t = Time.valueOf(t3.toString());
		List<Emploi_du_temps>leM=getInstance().getEmplois();
		for( int i = 0; i < leM.size(); i++ ){
			if(this.emplois.size()>i){
				Emploi_du_temps em=leM.get(i);
				if(em.getUser_id()==Main.id && (em.getHorraire_fin().compareTo(t))<0){
					this.delEmplois(em);
					i--;
				}
			}
			
		}
		return 0;
	}
	

	private List<note> notes;
    private List<InfosDuJour>infos;
    private List<Emploi_du_temps>emplois;
    private List<Evenement>events;
    private List<Utilisateur>users;
    private List<Projet>projets;

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
			File f1=new File("notes.txt");
			f1.createNewFile();
			FileOutputStream notes= new FileOutputStream(f1);
			
			ObjectOutputStream OutCommemtaires = new ObjectOutputStream(notes);
			//for(note c:this.notes){
				OutCommemtaires.writeObject(this.notes);
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
			File f6=new File("projets.txt");
			f6.createNewFile();
			FileOutputStream projets= new FileOutputStream(f6);
			ObjectOutputStream OutProjets = new ObjectOutputStream(projets);
			OutProjets.writeObject(this.projets);
			OutProjets.close();
			Thread.sleep(2000);
			JOptionPane.showMessageDialog(null, "sauvegarde faite!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    public void load(){
    	try {
    		File f1=new File("notes.txt");
    		f1.createNewFile();
    		File f2=new File("utilisateurs.txt");
    		f2.createNewFile();
    		File f3=new File("infosDuJour.txt");
    		f3.createNewFile();
    		File f4=new File("evenements.txt");
    		f4.createNewFile();
    		File f5=new File("emplois.txt");
    		f5.createNewFile();
    		File f6=new File("projets.txt");
    		f6.createNewFile();
			FileInputStream notes= new FileInputStream(f1);
			ObjectInputStream InCommemtaires = new ObjectInputStream(notes);
			
				this.notes=(List<note>) InCommemtaires.readObject();
			
			InCommemtaires.close();
			FileInputStream utilisateurs= new FileInputStream(f2);
			ObjectInputStream InUtilisateurs = new ObjectInputStream(utilisateurs);
			
				this.users=(List<Utilisateur>) InUtilisateurs.readObject();
			
			InUtilisateurs.close();
			FileInputStream infosDuJour= new FileInputStream(f3);
			ObjectInputStream InInfosDuJour = new ObjectInputStream(infosDuJour);
			
				this.infos=(List<InfosDuJour>) InInfosDuJour.readObject();
			
			InInfosDuJour.close();
			FileInputStream evenements= new FileInputStream(f4);
			ObjectInputStream InEvenements = new ObjectInputStream(evenements);
			
				this.events=(List<Evenement>) InEvenements.readObject();
			
			InEvenements.close();
			FileInputStream emplois= new FileInputStream(f5);
			ObjectInputStream InEmplois = new ObjectInputStream(emplois);
			
				this.emplois=(List<Emploi_du_temps>) InEmplois.readObject();
			
			InEmplois.close();
			FileInputStream projets= new FileInputStream(f6);
			ObjectInputStream InProjets = new ObjectInputStream(projets);
			
				this.projets=(List<Projet>) InProjets.readObject();
			
			InProjets.close();
			Thread.sleep(2000);
			JOptionPane.showMessageDialog(null, "données chargées");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}