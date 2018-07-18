package fr.epsi.segabank.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.epsi.segabank.model.Agence;

public class Test {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SegaBank");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Agence a = em.find(Agence.class, 6);
		System.out.println(a.getLabel());		
		em.remove(a);
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

}
