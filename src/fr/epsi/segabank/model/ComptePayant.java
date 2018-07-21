package fr.epsi.segabank.model;

import javax.persistence.*;

/**
 * @author lolo
 *
 */
//Declaration de la classe a JPA 
//Toujours choisir "persistence"
@Entity
@DiscriminatorValue("payant")
public class ComptePayant extends Compte {

	private static int fivePerCent = 5;
	
	public ComptePayant() {
		
	}
	
	public ComptePayant(String label, double solde, Client client, Agence agence) {
		super(label, solde, client, agence);
		this.setAgence(agence);
	}
	
	//METHODES

	public double getFivePerCent(double montant_operation) {
		
		double montant_maj = montant_operation -(montant_operation * fivePerCent / 100); 
		
		return montant_maj;
	}
	
}
