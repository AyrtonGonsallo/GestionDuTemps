package application;

import java.sql.Time;

public class Evenement {
		public int user_id;
		public String titre;
		public String description;
		public Time heure;
		public Evenement(String titre,Time h,String desc) {
			this.titre=titre;
			this.heure=h;
			this.description=desc;
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
		public Time getHeure() {
			return heure;
		}
		public void setHeure(Time heure) {
			this.heure = heure;
		}
		@Override
		public String toString() {
			return this.titre+" a "+this.heure;
		}
}
