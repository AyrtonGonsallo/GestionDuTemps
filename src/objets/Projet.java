package objets;

import java.io.Serializable;

public class Projet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int user_id;
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Projet [id=" + id + ", user id="+user_id+ ", titre=" + titre + ", status=" + status + "]";
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
	private String titre;
	private String description;
	private String status;
}
