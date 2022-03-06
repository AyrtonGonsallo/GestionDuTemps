package application;

import java.sql.Time;

public class Emploi_du_temps {
	public int id_utilisateur,id;
	public Time horaire_debut,horaire_fin;
	public String status,titre,description;
	public Emploi_du_temps(int i,int idu,Time d,Time f,String s,String t,String des) {
		id_utilisateur=idu;
		id=i;
		horaire_debut=d;
		horaire_fin=f;
		status=s;
		titre=t;
		description=des;
		
	}
	public Time getHoraire_debut() {
		return horaire_debut;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setHoraire_debut(Time horaire_deb) {
		this.horaire_debut = horaire_deb;
	}
	public Time getHoraire_fin() {
		return horaire_fin;
	}
	public void setHoraire_fin(Time horaire_fin) {
		this.horaire_fin = horaire_fin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
	

}
