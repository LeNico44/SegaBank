/**
 * 
 */
package fr.epsi.segabank.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.epsi.segabank.model.Agence;

/**
 * @author nicolas
 *
 */
public class AgenceDAO implements IDAO<Agence> {
	private static EntityManager em = JPAConnectionManager.getEm();
	
	//private static final String REQUETE_1 = "SELECT * FROM book";
	

	@Override
	public void create(Agence o) throws SQLException {
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
		
	}

	@Override
	public void update(Agence o) throws SQLException {

		em.getTransaction().begin();
		em.merge(o);
		em.getTransaction().commit();
		
	}

	@Override
	public void delete(Agence o) throws SQLException {
		em.getTransaction().begin();
		Query query1 = em.createQuery("DELETE FROM agence WHERE agence_id = " + o.getId());
		query1.executeUpdate();
//		em.remove(0);
		em.getTransaction().commit();
		
	}
}
