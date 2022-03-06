package objets;

import java.io.Serializable;
import java.sql.Time;

public class Emploi_du_temps implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int user_id;
	private Time horraire_fin;
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Emploi_du_temps [id=" + id + ", horraire_fin=" + horraire_fin + ", horraire_debut=" + horraire_debut
				+ ", titre=" + titre + ", status=" + status + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Time getHorraire_fin() {
		return horraire_fin;
	}
	public void setHorraire_fin(Time horraire_fin) {
		this.horraire_fin = horraire_fin;
	}
	public Time getHorraire_debut() {
		return horraire_debut;
	}
	public void setHorraire_debut(Time horraire_debut) {
		this.horraire_debut = horraire_debut;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private Time horraire_debut;
	private String titre;
	private String description;
	private String status;
	
}
