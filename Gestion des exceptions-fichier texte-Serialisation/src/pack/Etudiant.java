package pack;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class Etudiant implements Serializable{

		    
			private String prenom;
			private String nom;
			private String nivEtude;
			private String specialite;
			private String mat;
			
			
			public Etudiant(String prenom, String nom, String mat, String nivEtude, String specialite){
				this.setPrenom(prenom);
				this.setNom(nom);
			    this.setMat(mat);
				this.setNivEtude(nivEtude);
				this.setSpecialite(specialite);		
				}
			
			public String getPrenom() {
				return prenom;
			}

			public void setPrenom(String prenom) {
				this.prenom = prenom;
			}

			public String getNom() {
				return nom;
			}

			public void setNom(String nom) {
				this.nom = nom;
			}

			public String getNivEtude() {
				return nivEtude;
			}

			public void setNivEtude(String nivEtude) {
				this.nivEtude = nivEtude;
			}

			public String getSpecialite() {
				return specialite;
			}

			public void setSpecialite(String specialite) {
				this.specialite = specialite;
			}

			public String getMat() {
				return mat;
			}

			public void setMat(String mat) {
				this.mat = mat;
			}
			
			
			

			

}

