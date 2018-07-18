/**
 * 
 */
package fr.epsi.segabank.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;

import fr.epsi.segabank.model.Adresse;
import fr.epsi.segabank.model.Agence;
import fr.epsi.segabank.model.CodePostal;
import fr.epsi.segabank.model.Ville;

/**
 * @author nicolas
 *
 */
public class Main {

	private static List<Ville> villes;
	private static Scanner scanner = new Scanner( System.in );
	private static Scanner scanner2 = new Scanner( System.in );
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SegaBank");
	private static EntityManager em = emf.createEntityManager();

	/**
	 * 
	 */
	public static void main(String[] args) {
		
		
		dspMainMenu();

	}
	
	public static void dspMainMenu() {
        int response;
        do {
            System.out.println( "*****************************************" );
            System.out.println( "**************Menu Agence****************" );
            System.out.println( "                                         " );
            System.out.println( "* 1 - Ajouter une agence                *" );
            System.out.println( "* 2 - Modifier une agence               *" );
            System.out.println( "* 3 - Supprimer une agence              *" );
            System.out.println( "* 4 - Lister les agences                *" );
            System.out.println( "                                         " );
            System.out.println( "**************Menu Client****************" );
            System.out.println( "                                         " );
            System.out.println( "* 5 - Ajouter un client                 *" );
            System.out.println( "* 6 - Modifier un client                *" );
            System.out.println( "* 7 - Supprimer un client               *" );
            System.out.println( "* 8 - Lister les clients                *" );
            System.out.println( "                                         " );
            System.out.println( "**************Menu Compte****************" );
            System.out.println( "                                         " );
            System.out.println( "* 9 - Ajouter un compte                 *" );
            System.out.println( "* 10 - Créer une transaction            *" );
            System.out.println( "* 11 - Supprimer un compte              *" );
            System.out.println( "* 12 - Lister les comptes               *" );
            System.out.println( "                                         " );
            System.out.println( "*****************************************" );
            System.out.println( "* 13 - Quitter                          *" );
            System.out.println( "*****************************************" );
            System.out.println( "*****************************************" );
            System.out.print( "        * Entrez votre choix : " );
            response = scanner.nextInt();
        } while ( 0 >= response || response > 13 );
        scanner.nextLine();
        switch ( response ) {
            case 1:
                addAgence();
                break;
            case 2:
                //editAgence();
                break;
            case 3:
                deleteAgence();
                break;
            case 4:
                dspAgences();
                break;

        }
    }

	public static void addAgence() {

		Query query1 = em.createQuery("FROM CodePostal");
		List<CodePostal> codes = query1.getResultList();
		
		System.out.println( "************************************************" );
        System.out.println( "***************Ajout d'une agence***************" );
		
		System.out.println("");
		
		//----------------------------------------------
		System.out.println("Entrez un nom d'agence : ");
		String nomDAgence = scanner.nextLine();
		
		System.out.println("Entrez la ville de l'agence : ");
		String villeAgence = scanner.nextLine();
		
		System.out.println("Entrez un code postal");
		int codePostalAgence = scanner2.nextInt();
		
		System.out.println("Entrez l'adresse de l'agence : ");
		String adresseAgence = scanner.nextLine();
		//-----------------------------------------------
		
		System.out.println("Entrez un code agence : ");
		int codeAgence = scanner2.nextInt();
		
		Ville ville = new Ville(villeAgence);
		
		villes = new ArrayList<>();
		villes.add(ville);
		
		CodePostal codePostal = new CodePostal();
		if ( codes.size() > 0 ) {
			for(CodePostal code  : codes) {
				if(codePostalAgence == code.getCode() ) {
					ville.setCodePostal(code);
				}else {
					codePostal.setCode(codePostalAgence);
					ville.setCodePostal(codePostal);
				}
			}
		}else {
			codePostal.setCode(codePostalAgence);
			ville.setCodePostal(codePostal);
		}
		
		codePostal.setVilles(villes);
		
		Adresse adresse = new Adresse(adresseAgence, null, ville);
		
		Agence agence = new Agence(nomDAgence, codeAgence, adresse);
		
		adresse.setAgence(agence);
		
		
		
		if(codePostal.getCode() != 0) {
			em.getTransaction().begin();
			em.persist(ville);
			em.persist(adresse);
			em.persist(codePostal);
			em.persist(agence);
			em.getTransaction().commit();
		}else {
			em.getTransaction().begin();
			em.persist(ville);
			em.persist(adresse);
			em.persist(agence);
			em.getTransaction().commit();
		}
		
		
		
		System.out.println("");
		System.out.println("**********************");
		System.out.println("L'agence à été créée !");
        dspMainMenu();
    }
	
	public static void deleteAgence() {
		Query query1 = em.createQuery("FROM Agence");
		List<Agence> agences = query1.getResultList();
		
		Agence lAgence = new Agence();
	       
   	 	System.out.println( "***********************************************" );
        System.out.println( "************Suppression d'une agence***********" );
        if ( agences.size() > 0 ) {
            System.out.println( "Sélectionnez l'agence à supprimer..." );
            System.out.println( "**************************************************************" );
            System.out.println( "index    |id             | nom           " );
            System.out.println( "**************************************************************" );
            int index = 0;
            for ( Agence agence  : agences ) {
            System.out.println( index++ + "        | " + agence.getId() + "            | " + agence.getLabel() );
            }
            int response;
            do {
                System.out.print( "Entrez Le n° Index à supprimer (-1 pour annuler) : " );
                response = scanner2.nextInt();
            } while ( response < -1 || response >= agences.size() );
            if ( -1 != response ) {
                lAgence = agences.get( response );
                
                System.out.println( "Etes vous sûr de vouloir supprimer  (" + lAgence.getLabel() + ") ?(o/n)" );                     
                String rep = scanner.nextLine();
                
                if (rep.equals("o")) {
                String label = lAgence.getLabel();
                
                
                    if ( !label.equals( "" ) && !label.equals( lAgence.getId() ) ) {
                        lAgence.setLabel( label );
                        em.getTransaction().begin();
                		em.remove(lAgence);
                		em.getTransaction().commit();
                    }
                }
            }
        } else {
            System.out.println( "Aucun élément trouvé !!!" );
        }
  
        dspMainMenu();
    }
	
	public static void dspAgences() {
		Query query1 = em.createQuery("FROM Agence");
		List<Agence> agences = query1.getResultList();
		
		Agence lAgence = new Agence();
	       
   	 	System.out.println( "***********************************************" );
        System.out.println( "****************Liste des agences**************" );
        if ( agences.size() > 0 ) {
        	System.out.println( "******************************************************" );
            System.out.println( "index |id             | nom           " );
            System.out.println( "******************************************************" );
            int index = 0;
            for ( Agence agence  : agences ) {
            System.out.println( index++ + "        | " + agence.getId() + "            | " + agence.getLabel() );
            System.out.println( "******************************************************" );
            }
            
        } else {
            System.out.println( "Aucun élément trouvé !!!" );
        }
  
        dspMainMenu();
	}
}
