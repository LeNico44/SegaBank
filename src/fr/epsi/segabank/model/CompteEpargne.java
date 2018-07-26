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
@DiscriminatorValue("epargne")
public class CompteEpargne extends Compte {
	
		
	private double taux;
	
	public CompteEpargne() {
		
	}
	
	public CompteEpargne(String label, double solde, Client client, Agence agence, double taux) {
		super(label, solde, client, agence);
		this.taux = taux;
	}
	
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

	//METHODE

	public double calculTauxInteret(double soldeCE) {
        
        double soldeEpargne = soldeCE + (soldeCE * taux / 100);
        
        return soldeEpargne;
    }
    
     public String toString() {            
            
        return  super.toString() +" Le taux d'interet est : " +taux;    
            
    }
	
	

}
