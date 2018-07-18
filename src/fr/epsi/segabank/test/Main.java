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
import fr.epsi.segabank.model.Client;
import fr.epsi.segabank.model.Compte;
import fr.epsi.segabank.model.CompteEpargne;
import fr.epsi.segabank.model.ComptePayant;
import fr.epsi.segabank.model.CompteSimple;

/**
 * @author nicolas
 *
 */
public class Main {

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
                editAgence();
                break;
            case 3:
                deleteAgence();
                break;
            case 4:
                dspAgences();
                break;
            case 9:
            	addCompte(); 
            	break;
            case 10:
            	addTransaction();
            	break;
            case 12:
            	dspComptes();
            	break;

        }
    }
	
	//AGENCE

	public static void addAgence() {
		
		System.out.println( "************************************************" );
        System.out.println( "***************Ajout d'une agence***************" );
		
		System.out.println("");
		
		System.out.println("Entrez un nom d'agence : ");
		String nomDAgence = scanner.nextLine();
		
		System.out.println("Entrez un code agence : ");
		int codeAgence = scanner2.nextInt();
		
		System.out.println("Entrez le numero de la voie : ");
		int numeroVoie = scanner2.nextInt();
		
		System.out.println("Entrez le type et le nom de la voie :");
		String voie = scanner.nextLine();
		
		System.out.println("Entrez un code postal");
		int codePostal = scanner2.nextInt();
		
		System.out.println("Entrez la ville de l'agence : ");
		String ville = scanner.nextLine();

		
		
		Adresse adresse = new Adresse(numeroVoie, voie, codePostal, ville);
		
		Agence agence = new Agence(nomDAgence, codeAgence, adresse);
		
		adresse.setAgence(agence);
		
		
		

		em.getTransaction().begin();
		em.persist(adresse);
		em.persist(agence);
		em.getTransaction().commit();
		
		
		
		System.out.println("");
		System.out.println("**********************");
		System.out.println("L'agence à été créée !");
        dspMainMenu();
    }
	
	public static void editAgence() {
		System.out.println("Bientôt vous pourrez modifier une agence...");
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
                System.out.println("delete " + lAgence.getLabel());
                        em.getTransaction().begin();
                		em.remove(agences.get( response ));
                		em.getTransaction().commit();

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
	
	//COMPTE
	
	public static void addCompte() {
		System.out.println( "************************************************" );
        System.out.println( "***************Ajout d'un compte****************" );
		
		System.out.println("");
		int response;
        do {
        	System.out.println( "* 1 - Compte depuis une agence     *" );
        	System.out.println( "* 2 - Compte depuis un client      *" );
        	System.out.print  ( "        * Entrez votre choix : "      );
        	response = scanner.nextInt();
	   
         } while ( 0 >= response || response > 3 );    
	         scanner.nextLine();  
	         switch ( response ) {        
	         case 1:
	        	 choixAgence();
	        	 break;        
	         case 2:
	        	 //choixClient();
	             break;        
	    }
	}
	
	public static void choixAgence() {
		Query query1 = em.createQuery("FROM Agence");
		List<Agence> agences = query1.getResultList();
	       
   	 	System.out.println( "***********************************************" );
        System.out.println( "****************Liste des agences**************" );
        if ( agences.size() > 0 ) {
            System.out.println( "Sélectionnez l'agence pour laquelle créer un compte" );
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
                Agence lAgence = agences.get( response );
                Client clientNull = null;
                addCompteAgence(lAgence, clientNull);
            }
        } else {
            System.out.println( "Aucune agence n'a été trouvée !!!" );
        }
	}
	
	public static void addCompteAgence(Agence agence, Client client) {
		System.out.println( "************************************************" );
        System.out.println( "***************Ajout d'un compte depuis l'agence***************" );
		
		System.out.println("");
		int response;
        do {
        	System.out.println( "* 1 - Compte Simple                *" );
        	System.out.println( "* 2 - Compte Epargne               *" );
        	System.out.println( "* 3 - Compte Payant                *" );
        	System.out.print  ( "        * Entrez votre choix : "      );
        	response = scanner.nextInt();
	   
         } while ( 0 >= response || response > 3 );    
	         scanner.nextLine();  
	         switch ( response ) {        
	         case 1:
	        	 createCompteSimple(agence, client);
	        	 break;        
	         case 2:
	             createCompteEpargne(agence, client);
	             break;        
	         case 3:
	        	 createComptePayant(agence, client);
	        	 break;
	        
	    }
	}
	
	private static void createCompteSimple(Agence agence, Client client) {
		
		System.out.println("Entrez un nom de compte : ");
		String label = scanner.nextLine();
		
		System.out.println("Entrez le solde de départ : ");
		double solde = scanner2.nextDouble();
		
		System.out.println("Entrez le découvert autorisé : ");
		double overdraft = scanner2.nextDouble();
		
		CompteSimple compte = new CompteSimple(label, solde, client, agence, overdraft);
 
		em.getTransaction().begin();
		em.persist(compte);
		em.getTransaction().commit();
		
		
		
		System.out.println("");
		System.out.println("**********************");
		System.out.println("Le compte à été créé !");
        dspMainMenu();
		
	}
	
	private static void createCompteEpargne(Agence agence, Client client) {
		
		System.out.println("Entrez un nom de compte : ");
		String label = scanner.nextLine();
		
		System.out.println("Entrez le solde de départ : ");
		double solde = scanner2.nextDouble();
		
		System.out.println("Entrez le taux du compte : ");
		double taux = scanner.nextDouble();
		
		CompteEpargne compte = new CompteEpargne(label, solde, client, agence, taux);

		em.getTransaction().begin();
		em.persist(compte);
		em.getTransaction().commit();
		
		
		System.out.println("");
		System.out.println("**********************");
		System.out.println("Le compte à été créé !");
        dspMainMenu();
	}
	
	private static void createComptePayant(Agence agence, Client client) {
		
		System.out.println("Entrez un nom de compte : ");
		String label = scanner.nextLine();
		
		System.out.println("Entrez le solde de départ : ");
		double solde = scanner2.nextDouble();
		
		ComptePayant compte = new ComptePayant(label, solde, client, agence);

		em.getTransaction().begin();
		em.persist(compte);
		em.getTransaction().commit();
		
		
		System.out.println("");
		System.out.println("**********************");
		System.out.println("Le compte à été créé !");
        dspMainMenu();
	}
    
	private static void dspComptes() {
		Query query1 = em.createQuery("FROM Compte");
		List<Compte> comptes = query1.getResultList();
	       
   	 	System.out.println( "***********************************************" );
        System.out.println( "****************Liste des comptes simples**************" );
        if ( comptes.size() > 0 ) {
        	System.out.println( "******************************************************" );
            System.out.println( "index |id             | nom           " );
            System.out.println( "******************************************************" );
            int index = 0;
            for ( Compte compte  : comptes ) {
            	index++;
	            System.out.println( index + "        | " + compte.toString() );
	            System.out.println( "******************************************************" );
            }
            
        } else {
            System.out.println( "Aucun compte trouvé !!!" );
        }
  
        dspMainMenu();
	}
	
	//TRANSACTION
	
	public static void addTransaction() {
        
        int response;
        do {
       
       System.out.println( "************************************************" );
      System.out.println( "***************Transaction***************" );
       
       System.out.println("");
       
       //----------------------------------------------
       
       System.out.println( "* 1 - Crediter un compte               *" );
      System.out.println( "* 2 - Debiter  un compte               *" );
      System.out.print  ( "        * Entrez votre choix : "      );
      response = scanner.nextInt();
  
        } while ( 0 >= response || response > 13 );    
        scanner.nextLine();  
        switch ( response ) {        
        case 1:
           creditCompte();
          break;        
        case 2:
            debitCompte();
          break;
   }
       
   }
   public static void creditCompte() {
       
   }
   
   public static void debitCompte() {
       
   }
}
