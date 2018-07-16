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
@Table(name = "compteSimple")

public class CompteSimple extends Compte {
	
	
	private double taux;
	
	
	public CompteSimple() {
		
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public void  calculInteret() {
		
	}
	
	
	

}
