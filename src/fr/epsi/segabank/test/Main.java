/**
 * 
 */
package fr.epsi.segabank.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author nicolas
 *
 */
public class Main {

	/**
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SegaBank");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("La Banque de monsieur Sega !");
	}

}
