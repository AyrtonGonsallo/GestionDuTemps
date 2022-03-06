package login;

import java.sql.Date;

public class Login {

	public int id;
	public String email;
	public String identifiant;
	public int age;
	public Date date_creation;
	public Login() {
		
	}
	
	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public Login(int id, String email, String identifiant, int age, Date date_creation) {
		super();
		this.id = id;
		this.email = email;
		this.identifiant = identifiant;
		this.age = age;
		this.date_creation = date_creation;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

}
