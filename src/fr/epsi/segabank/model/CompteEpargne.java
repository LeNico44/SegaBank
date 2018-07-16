package fr.epsi.segabank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * @author lolo
 *
 */
//Declaration de la classe a JPA 
//Toujours choisir "persistence"
@Entity
@Table(name = "compteEpargne")


public class CompteEpargne extends Compte {
	
		
	private double taux;
	
	
	
	public CompteEpargne(double taux) {
		super();
		this.taux = taux;
	}



	public double getTaux() {
		return taux;
	}



	public void setTaux(double taux) {
		this.taux = taux;
	}



	public void interetE() {
		
		
	}
	

}
