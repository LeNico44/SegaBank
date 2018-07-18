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
@DiscriminatorValue("simple")
public class CompteSimple extends Compte {
	
	
	private double overdraft;
	
	
	public CompteSimple() {
		
	}
	
	public double getOverdraft() {
		return overdraft;
	}


	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}
	
	
	

}
