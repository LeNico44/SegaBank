package fr.epsi.segabank.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.;

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
	
	public CompteSimple(String label, double solde) {
		this.setLabel(label);
		this.setSolde(solde);
	}
	
	public CompteSimple(String label, double solde, Client client, Agence agence, double overdraft) {
		super(label, solde, client, agence);
		this.overdraft = overdraft;
	}
	
	public double getOverdraft() {
		return overdraft;
	}


	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}
	
	//METHODES
	
	public String toString() {          
        
        return  super.toString() + " le decouvert autorisé est " +this.getOverdraft();    
            
    }

}
