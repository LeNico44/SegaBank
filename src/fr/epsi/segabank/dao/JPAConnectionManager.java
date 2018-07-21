/**
 * 
 */
package fr.epsi.segabank.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author nicolas
 *
 */
public class JPAConnectionManager {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SegaBank");
	private static EntityManager em = emf.createEntityManager();
	
	public static EntityManager getEm() {
		return em;
	}
	
}
