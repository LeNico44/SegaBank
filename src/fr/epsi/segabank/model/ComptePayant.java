package fr.epsi.segabank.model;

import javax.persistence.*;

/**
 * @author lolo
 *
 */
//Declaration de la classe a JPA 
//Toujours choisir "persistence"
@Entity
@DiscriminatorValue("PAYANT")
public class ComptePayant extends Compte {

	private static int fivePerCent = 5;
	
	public ComptePayant() {
		
	}

	public static int getFivePerCent() {
		return fivePerCent;
	}
		
}
