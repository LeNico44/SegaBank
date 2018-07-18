package fr.epsi.segabank.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author lolo
 *
 */
//Declaration de la classe a JPA 
//Toujours choisir "persistence"
@Entity
@DiscriminatorValue("EPARGNE")
public class CompteEpargne extends Compte {
	
		
	private double taux;
	
	public CompteEpargne() {
		
	}
	
	public CompteEpargne(double taux) {
		this();
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
