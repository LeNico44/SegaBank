/**
 * 
 */
package fr.epsi.segabank.dao;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import fr.epsi.segabank.model.Client;

/**
 * @author nicolas
 *
 */
public class ClientDAO implements IDAO<Client> {
	private static EntityManager em = JPAConnectionManager.getEm();
	
	//private static final String REQUETE_1 = "SELECT * FROM book";
	

	@Override
	public void create(Client o) throws SQLException {

		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
		
	}

	@Override
	public void update(Client o) throws SQLException {

		em.getTransaction().begin();
		em.merge(o);
		em.getTransaction().commit();
		
	}

	@Override
	public void delete(Client o) throws SQLException {
		em.getTransaction().begin();
		em.remove(o);
		em.getTransaction().commit();
		
	}
}
