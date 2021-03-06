package objets;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class InfosDuJour implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String information;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public Date getJour() {
		return jour;
	}
	public void setJour(Date jour) {
		this.jour = jour;
	}
	public int getUser_id() {
		return user_id;
	}
	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
		return "ID: "+id+" jour=" + format.format(jour) + ", user_id=" + user_id + " "+information;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	private Date jour;
	private int user_id;
}
