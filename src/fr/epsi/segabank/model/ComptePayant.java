package fr.epsi.segabank.model;

import javax.persistence.*;

/**
 * @author lolo
 *
 */
//Declaration de la classe a JPA 
//Toujours choisir "persistence"
@Entity
@Table(name = "comptePayant")

public class ComptePayant extends Compte {


		
	private double overdraft;
	
	
	public ComptePayant() {
		
	}

	public double getOverdraft() {
		return overdraft;
	}


	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}
	
	
	
}
