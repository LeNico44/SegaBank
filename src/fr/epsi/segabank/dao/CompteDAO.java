/**
 * 
 */
package fr.epsi.segabank.dao;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import fr.epsi.segabank.model.Compte;

/**
 * @author nicolas
 *
 */
public class CompteDAO implements IDAO<Compte> {
	private static EntityManager em = JPAConnectionManager.getEm();
	
	private static final String REQUETE_1 = "SELECT * FROM transaction WHERE id_compte = ";

	@Override
	public void create(Compte o) throws SQLException{
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}

	@Override
	public void update(Compte o) throws SQLException {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.merge(o);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Compte o) throws SQLException {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.remove(em.contains(o) ? o : em.merge(o));//???Mais ça marche !!!
		em.getTransaction().commit();
	}
	
	public void detail(Compte o) throws SQLException {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.createQuery(REQUETE_1 + o.getId_compte());
		em.getTransaction().commit();
	}

}
