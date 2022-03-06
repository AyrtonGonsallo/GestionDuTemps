package application;

import java.sql.Date;

public class Infosdujour {
	public int user_id;
	public String information;
	public Date jour;
	public Infosdujour() {
		
	}
	public Infosdujour(String inf,Date d,int uid) {
		information=inf;
		user_id=uid;
		jour=d;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	
}
