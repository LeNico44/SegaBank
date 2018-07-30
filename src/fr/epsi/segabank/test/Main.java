/**
 * 
 */
package fr.epsi.segabank.test;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.epsi.segabank.dao.AgenceDAO;
import fr.epsi.segabank.dao.ClientDAO;
import fr.epsi.segabank.dao.CompteDAO;
import fr.epsi.segabank.model.Adresse;
import fr.epsi.segabank.model.Agence;
import fr.epsi.segabank.model.Client;
import fr.epsi.segabank.model.Compte;
import fr.epsi.segabank.model.CompteEpargne;
import fr.epsi.segabank.model.ComptePayant;
import fr.epsi.segabank.model.CompteSimple;
import fr.epsi.segabank.model.Transaction;

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
        int response = 0;
        do {
            System.out.println( "*****************************************" );
            System.out.println( "**************Menu Agence****************" );
            System.out.println( "                                         " );
            System.out.println( "*  1 - Ajouter une agence               *" );
            System.out.println( "*  2 - Modifier une agence              *" );
            System.out.println( "*  3 - Supprimer une agence             *" );
            System.out.println( "*  4 - Lister les agences               *" );
            System.out.println( "                                         " );
            System.out.println( "**************Menu Client****************" );
            System.out.println( "                                         " );
            System.out.println( "*  5 - Ajouter un client                *" );
            System.out.println( "*  6 - Modifier un client               *" );
            System.out.println( "*  7 - Supprimer un client              *" );
            System.out.println( "*  8 - Lister les clients               *" );
            System.out.println( "                                         " );
            System.out.println( "**************Menu Compte****************" );
            System.out.println( "                                         " );
            System.out.println( "*  9 - Ajouter un compte                *" );
            System.out.println( "* 10 - Modifier un compte               *" );
            System.out.println( "* 11 - Supprimer un compte              *" );
            System.out.println( "* 12 - Lister les comptes               *" );
            System.out.println( "                                         " );
            System.out.println( "***********Menu Transaction**************" );
            System.out.println( "                                         " );
            System.out.println( "* 13 - Créer une transaction            *" );
            System.out.println( "                                         " );
            System.out.println( "*****************************************" );
            System.out.println( "* 14 - Quitter                          *" );
            System.out.println( "*****************************************" );
            System.out.println( "*****************************************" );
            System.out.print( "        * Entrez votre choix : " );
            
            try {
            	response = Integer.parseInt(scanner.nextLine());
    		} catch (NumberFormatException e) {
    			System.out.println("Vous tentez de rentrer autre chose qu'un nombre correspondant aux choix du menu.");
    			System.out.println("Veuillez réessayer");
    		}
            
        } while ( 0 >= response || response > 13 );
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
            case 5:
                addClient();
                break;
            case 6:
                editClient();
                break;
            case 7:
                deleteClient();
                break;
            case 8:
                dspClients();
                break;
            case 9:
            	addCompte(); 
            	break;
            case 10:
            	editCompte();
            	break;
            case 11:
            	deleteCompte();
            	break;
            case 12:
            	dspComptes();
            	break;
            case 13:
            	addTransaction();
            	break;
            case 14:
            	//méthode pour quitter l'application
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
		
		
		AgenceDAO agenceDao = new AgenceDAO();
        try {
        	agenceDao.create( agence );
        } catch ( java.sql.SQLException e ) {
            System.out.println( e.getMessage() );
        }
		
		
		
		System.out.println("");
		System.out.println("**********************");
		System.out.println("L'agence à été créée !");
        dspMainMenu();
    }
	
	public static void editAgence() {
		Query query1 = em.createQuery("FROM Agence");
		List<Agence> agences = query1.getResultList();
		
		Agence lAgence = new Agence();
	       
   	 	System.out.println( "***********************************************" );
        System.out.println( "************Modification d'une agence***********" );
        if ( agences.size() > 0 ) {
            System.out.println( "Sélectionnez l'agence à modifier..." );
            System.out.println( "**************************************************************" );
            System.out.println( "index    |id             | nom           " );
            System.out.println( "**************************************************************" );
            int index = 0;
            for ( Agence agence  : agences ) {
            System.out.println( index++ + "        | " + agence.getId() + "            | " + agence.getLabel());
            }
            int response;
            do {
                System.out.print( "Entrez Le n° Index à modifier (-1 pour annuler) : " );
                response = scanner2.nextInt();
            } while ( response < -1 || response >= agences.size() );
            if ( -1 != response ) {
            	lAgence = agences.get( response );
                
                System.out.println( "Etes vous sûr de vouloir modifier  (" + lAgence.getLabel() +") ?(o/n)" );                     
                String rep = scanner.nextLine();

                if (rep.equals("o")) {
                	
                	System.out.println("Modifiez le nom de l'agence (" +  lAgence.getLabel() + ") ou copier le nom actuel et valider avec la touche enter");
                	lAgence.setLabel(scanner.nextLine());

                    System.out.println("Modifiez le code de l'agence (" +  lAgence.getCodeAgence() + ") ou copier le code actuel et valider avec la touche enter");
                    lAgence.setCodeAgence(scanner2.nextInt());
                    
                    System.out.println("Modifiez la numéro de la voie de l'adresse de l'agence (" +  lAgence.getAdresse().getNumeroVoie() + ") ou copier le numéro actuel et valider avec la touche enter");
                    lAgence.getAdresse().setNumeroVoie(scanner2.nextInt());
                    
                    System.out.println("Modifiez le type et le nom de la voie de l'adresse de l'agence (" +  lAgence.getAdresse().getVoie() + ") ou copier la voie actuelle et valider avec la touche enter");
                    lAgence.getAdresse().setVoie(scanner.nextLine());
                    
                    System.out.println("Modifiez le code postal de l'agence (" +  lAgence.getAdresse().getCodePostal() + ") ou copier le code postal actuel et valider avec la touche enter");
                    lAgence.getAdresse().setCodePostal(scanner2.nextInt());
                    
                    System.out.println("Modifiez la ville de l'agence (" +  lAgence.getAdresse().getVille() + ") ou copier la ville actuelle et valider avec la touche enter");
                    lAgence.getAdresse().setVille(scanner.nextLine());
                    
                    AgenceDAO agenceDao = new AgenceDAO();
                    try {
                    	agenceDao.update( lAgence );
                    } catch ( java.sql.SQLException e ) {
                        System.out.println( e.getMessage() );
                    }
                }
            }
        } else {
            System.out.println( "Aucun élément trouvé !!!" );
        }
  
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
                	
                	AgenceDAO agenceDao = new AgenceDAO();
	                try {
	                	agenceDao.delete( lAgence );
	                } catch ( java.sql.SQLException e ) {
	                    System.out.println( e.getMessage() );
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
	       
   	 	System.out.println( "***********************************************" );
        System.out.println( "****************Liste des agences**************" );
        if ( agences.size() > 0 ) {
        	System.out.println( "******************************************************" );
            System.out.println( "index |id             | nom             | adresse " );
            System.out.println( "******************************************************" );
            int index = 0;
            for ( Agence agence  : agences ) {
            System.out.println( index++ + "        | " + agence.getId() + "            | " + agence.getLabel() +"        | " + agence.getAdresse().getNumeroVoie() + " " + agence.getAdresse().getVoie() + " " + agence.getAdresse().getCodePostal() + " " + agence.getAdresse().getVille());
            System.out.println( "******************************************************" );
            }
            
        } else {
            System.out.println( "Aucun élément trouvé !!!" );
        }
  
        dspMainMenu();
	}
	
	//CLIENT
	
	public static void addClient() {
		Query query1 = em.createQuery("FROM Agence");
		List<Agence> agences = query1.getResultList();
	       
   	 	System.out.println( "***********************************************" );
        System.out.println( "****************Liste des agences**************" );
        if ( agences.size() > 0 ) {
            System.out.println( "Sélectionnez l'agence pour laquelle créer un client." );
            System.out.println( "**************************************************************" );
            System.out.println( "index    |id             | nom           " );
            System.out.println( "**************************************************************" );
            int index = 0;
            for ( Agence agence  : agences ) {
            System.out.println( index++ + "        | " + agence.getId() + "            | " + agence.getLabel() );
            }
            int response;
            do {
                System.out.print( "Entrez Le n° Index de l'agence sélèctionnée (-1 pour annuler) : " );
                response = scanner2.nextInt();
            } while ( response < -1 || response >= agences.size() );
            if ( -1 != response ) {
                Agence lAgence = agences.get( response );
                addClientAgence(lAgence);
            }else {
            	dspMainMenu();
            }
        } else {
            System.out.println( "Aucune agence n'a été trouvée !!!" );
            dspMainMenu();
        }
	}
	
	public static void addClientAgence(Agence agence) {
		
		System.out.println( "************************************************" );
        System.out.println( "****************Ajout d'un client***************" );
		
		System.out.println("");
		
		System.out.println("Entrez le nom du client : ");
		String nom = scanner.nextLine();
		
		System.out.println("Entrez le prenom du client : ");
		String prenom = scanner.nextLine();
		
		System.out.println("Entrez le numero de la voie : ");
		int numeroVoie = scanner2.nextInt();
		
		System.out.println("Entrez le type et le nom de la voie :");
		String voie = scanner.nextLine();
		
		System.out.println("Entrez un code postal");
		int codePostal = scanner2.nextInt();
		
		System.out.println("Entrez la ville de l'agence : ");
		String ville = scanner.nextLine();

		
		
		Adresse adresse = new Adresse(numeroVoie, voie, codePostal, ville);
		
		Client client = new Client(nom, prenom, agence, adresse);
		
		adresse.setClient(client);
		

        ClientDAO clinetDao = new ClientDAO();
        try {
        	clinetDao.create( client );
        } catch ( java.sql.SQLException e ) {
            System.out.println( e.getMessage() );
        }
		
		
		System.out.println("");
		System.out.println("**********************");
		System.out.println("L'agence à été créée !");
        dspMainMenu();
    }
	
	public static void editClient() {
		Query query1 = em.createQuery("FROM Client");
		List<Client> clients = query1.getResultList();
		
		Client leClient = new Client();
	       
   	 	System.out.println( "***********************************************" );
        System.out.println( "************Modification d'un client***********" );
        if ( clients.size() > 0 ) {
            System.out.println( "Sélectionnez le client à modifier..." );
            System.out.println( "**************************************************************" );
            System.out.println( "index    |id             | nom           " );
            System.out.println( "**************************************************************" );
            int index = 0;
            for ( Client client  : clients ) {
            System.out.println( index++ + "        | " + client.getId() + "            | " + client.getNom() + "            | " + client.getPrenom());
            }
            int response;
            do {
                System.out.print( "Entrez Le n° Index à modifier (-1 pour annuler) : " );
                response = scanner2.nextInt();
            } while ( response < -1 || response >= clients.size() );
            if ( -1 != response ) {
            	leClient = clients.get( response );
                
                System.out.println( "Etes vous sûr de vouloir modifier  (" + leClient.getNom() + " " + leClient.getPrenom() +") ?(o/n)" );                     
                String rep = scanner.nextLine();

                if (rep.equals("o")) {
                	
                	System.out.println("Modifiez le nom du client (" +  leClient.getNom() + ") ou copier le nom actuel et valider avec la touche enter");
                    leClient.setNom(scanner.nextLine());

                    System.out.println("Modifiez le prénom du client (" +  leClient.getPrenom() + ") ou copier le prénom actuel et valider avec la touche enter");
                    leClient.setPrenom(scanner.nextLine());
                    
                    System.out.println("Modifiez la numéro de la voie de l'adresse du client (" +  leClient.getAdresse().getNumeroVoie() + ") ou copier le numéro actuel et valider avec la touche enter");
                    leClient.getAdresse().setNumeroVoie(scanner2.nextInt());
                    
                    System.out.println("Modifiez le type et le nom de la voie de l'adresse du client (" +  leClient.getAdresse().getVoie() + ") ou copier la voie actuelle et valider avec la touche enter");
                    leClient.getAdresse().setVoie(scanner.nextLine());
                    
                    System.out.println("Modifiez le code postal du client (" +  leClient.getAdresse().getCodePostal() + ") ou copier le code postal actuel et valider avec la touche enter");
                    leClient.getAdresse().setCodePostal(scanner2.nextInt());
                    
                    System.out.println("Modifiez la ville du client (" +  leClient.getAdresse().getVille() + ") ou copier la ville actuelle et valider avec la touche enter");
                    leClient.getAdresse().setVille(scanner.nextLine());
                    
                    System.out.println("Choisir dans la liste l'agence du client. L'agence actuelle (" + leClient.getAgence().getLabel() +") ");
                    
                    Query query2 = em.createQuery("FROM Agence");
            		List<Agence> agences = query2.getResultList();
            		
            		Agence lAgence = new Agence();
            	       
               	 	System.out.println( "*********************************************************" );
                    System.out.println( "************Modification de l'agence du client***********" );
                    if ( agences.size() > 0 ) {
                        System.out.println( "Sélectionnez une agence pour le client..." );
                        System.out.println( "**************************************************************" );
                        System.out.println( "index    |id             | nom           " );
                        System.out.println( "**************************************************************" );
                        int index2 = 0;
                        for ( Agence agence  : agences ) {
                        System.out.println( index2++ + "        | " + agence.getId() + "            | " + agence.getLabel());
                        }
                        int response2;
                        do {
                            System.out.print( "Entrez Le n° Index sélectionné (-1 pour annuler) : " );
                            response2 = scanner2.nextInt();
                        } while ( response2 < -1 || response2 >= agences.size() );
                        if ( -1 != response2 ) {
                        	lAgence = agences.get( response2 );
                        	leClient.setAgence(lAgence);
                            
                            }
                        }
                    } else {
                        System.out.println( "Aucun élément trouvé !!!" );
                    }
                    
                    em.getTransaction().begin();
            		em.merge(leClient);
            		em.getTransaction().commit();
                }
            
        } else {
            System.out.println( "Aucun élément trouvé !!!" );
        }
  
        dspMainMenu();
	}
	
	public static void deleteClient() {
		Query query1 = em.createQuery("FROM Client");
		List<Client> clients = query1.getResultList();
		
		Client leClient = new Client();
	       
   	 	System.out.println( "***********************************************" );
        System.out.println( "************Suppression d'un client***********" );
        if ( clients.size() > 0 ) {
            System.out.println( "Sélectionnez le client à supprimer..." );
            System.out.println( "**************************************************************" );
            System.out.println( "index    |id             | nom           " );
            System.out.println( "**************************************************************" );
            int index = 0;
            for ( Client client  : clients ) {
            System.out.println( index++ + "        | " + client.getId() + "            | " + client.getNom() + "            | " + client.getPrenom());
            }
            int response;
            do {
                System.out.print( "Entrez Le n° Index à supprimer (-1 pour annuler) : " );
                response = scanner2.nextInt();
            } while ( response < -1 || response >= clients.size() );
            if ( -1 != response ) {
            	leClient = clients.get( response );
                
                System.out.println( "Etes vous sûr de vouloir supprimer  (" + leClient.getNom() + " " + leClient.getPrenom() +") ?(o/n)" );                     
                String rep = scanner.nextLine();
                
                
                if (rep.equals("o")) {
                System.out.println("delete " + leClient.getNom() + " " + leClient.getPrenom());
                        em.getTransaction().begin();
                		em.remove(leClient);
                		em.getTransaction().commit();
                }
            }
        } else {
            System.out.println( "Aucun élément trouvé !!!" );
        }
  
        dspMainMenu();
    }
	
	public static void dspClients() {
		Query query1 = em.createQuery("FROM Client");
		List<Client> clients = query1.getResultList();
	       
   	 	System.out.println( "***********************************************" );
        System.out.println( "****************Liste des clients**************" );
        if ( clients.size() > 0 ) {
        	System.out.println( "***************************************************************************************************************************" );
            System.out.println( "index |id             | nom          | prenom          | adresse                                           | agence" );
            System.out.println( "***************************************************************************************************************************" );
            int index = 0;
            for ( Client client  : clients ) {
            System.out.println( index++ + "     | " + client.getId() + "            | " + client.getNom() + "        | " + client.getPrenom() +"        | " + client.getAdresse().getNumeroVoie() + " " + client.getAdresse().getVoie() + " " + client.getAdresse().getCodePostal() + " " + client.getAdresse().getVille() + "            | " + client.getAgence().getLabel());
            System.out.println( "***************************************************************************************************************************" );
            }
            
        } else {
            System.out.println( "Aucun élément trouvé !!!" );
        }
  
        dspMainMenu();
	}
	
	//COMPTE
	
	public static void addCompte() {
		Agence lAgence = new Agence();
		Client leClient = new Client();
		
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
                System.out.print( "Entrez Le n° Index de votre choix (-1 pour annuler) : " );
                response = scanner2.nextInt();
            } while ( response < -1 || response >= agences.size() );
            if ( -1 != response ) {
                lAgence = agences.get( response ); 
            }
            
        } else {
            System.out.println( "Aucune agence n'a été trouvée !!!" );
            dspMainMenu();
        }
        
        Query query2 = em.createQuery("FROM Client");
		List<Client> clients = query2.getResultList();
	       
   	 	System.out.println( "***********************************************" );
        System.out.println( "****************Liste des clients**************" );
        if ( clients.size() > 0 ) {
            System.out.println( "Sélectionnez le client pour laquelle créer un compte" );
            System.out.println( "**************************************************************" );
            System.out.println( "index    |id             | nom           | prenom" );
            System.out.println( "**************************************************************" );
            int index = 0;
            for ( Client client  : clients ) {
            System.out.println( index++ + "        | " + client.getId() + "            | " + client.getNom() + "            | " + client.getPrenom());
            }
            int response;
            do {
                System.out.print( "Entrez Le n° Index de votre choix (-1 pour annuler) : " );
                response = scanner2.nextInt();
            } while ( response < -1 || response >= clients.size() );
            if ( -1 != response ) {
                leClient = clients.get( response );
            }
        } else {
            System.out.println( "Aucun client n'a été trouvée !!!" );
            dspMainMenu();
        }
        
        addCompteClient(lAgence, leClient);
	}
	
	
	public static void addCompteClient(Agence agence, Client client) {
		System.out.println( "************************************************" );
        System.out.println( "****************Ajout d'un compte***************" );
		
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
		
		Compte compte = new CompteSimple(label, solde, client, agence, overdraft);
		
		CompteDAO compteDAO = new CompteDAO();
 
		try {
			compteDAO.create( compte );
        } catch ( java.sql.SQLException e ) {
            System.out.println( e.getMessage() );
        }
		
		
		System.out.println("");
		System.out.println("**********************");
		System.out.println("Le compte à été créé !");
        dspMainMenu();
		
	}
	
	private static void createCompteEpargne(Agence agence, Client client) {
		
		System.out.println("Entrez le taux du compte : ");
		double taux = scanner2.nextDouble();
		
		System.out.println("Entrez un nom de compte : ");
		String label = scanner.nextLine();
		
		System.out.println("Entrez le solde de départ : ");
		double solde = scanner2.nextDouble();
		
		CompteEpargne compte = new CompteEpargne(label, solde, client, agence, taux);

		CompteDAO compteDAO = new CompteDAO();
		 
		try {
			compteDAO.create( compte );
        } catch ( java.sql.SQLException e ) {
            System.out.println( e.getMessage() );
        }
		
		
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

		CompteDAO compteDAO = new CompteDAO();
		 
		try {
			compteDAO.create( compte );
        } catch ( java.sql.SQLException e ) {
            System.out.println( e.getMessage() );
        }
		
		
		System.out.println("");
		System.out.println("**********************");
		System.out.println("Le compte à été créé !");
        dspMainMenu();
	}
	
	private static void editCompte() {
		Query query1 = em.createQuery("FROM Compte");
		List<Compte> comptes = query1.getResultList();
				
  	 	System.out.println( "***********************************************" );
        System.out.println( "************Modification d'un compte************" );
        if ( comptes.size() > 0 ) {
        	System.out.println( "Sélectionnez le compte à modifier..." );
        	System.out.println( "**************************************************************" );
        	System.out.println( "index    |id             | nom           " );
        	System.out.println( "**************************************************************" );
        	int index = 0;
           
        	for ( Compte compte  : comptes ) {
    			System.out.println( index++ + "        | " + compte.getId_compte() + "            | " + compte.getLabel() );
        	}
        	int response;
        	String rep;
        	do {
        		System.out.print( "Entrez Le n° Index à modifier (-1 pour annuler) : " );
        		response = scanner2.nextInt();
        	} while ( response < -1 || response >= comptes.size() );
        	
        	CompteDAO compteDAO = new CompteDAO();
        	
        	System.out.println( "Etes vous sûr de vouloir modifier  (" + comptes.get(response).getLabel() + ") ?(o/n)" );                     
			rep = scanner.nextLine();
			if (rep.equals("o")) {
				
				System.out.println("Modifiez le nom du compte (" +  comptes.get(response).getLabel() + ") ou copier le nom actuel et valider avec la touche enter");
				comptes.get(response).setLabel(scanner.nextLine());
      
	          	try {
	          		compteDAO.update( comptes.get(response) );
	       		} catch ( java.sql.SQLException e ) {
	       			System.out.println( e.getMessage() );
	       		}
			} else {
        		System.out.println( "Aucun élément trouvé !!!" );
        	}
        }
        dspMainMenu();
	}
	
	private static void deleteCompte() {
		Query query1 = em.createQuery("FROM Compte");
		List<Compte> comptes = query1.getResultList();
				
  	 	System.out.println( "***********************************************" );
        System.out.println( "************Suppression d'un compte************" );
        if ( comptes.size() > 0 ) {
        	System.out.println( "Sélectionnez le compte à supprimer..." );
        	System.out.println( "**************************************************************" );
        	System.out.println( "index    |id             | nom           " );
        	System.out.println( "**************************************************************" );
        	int index = 0;
           
        	for ( Compte compte  : comptes ) {
    			System.out.println( index++ + "        | " + compte.getId_compte() + "            | " + compte.getLabel() );
        	}
        	int response;
        	String rep;
        	do {
        		System.out.print( "Entrez Le n° Index à supprimer (-1 pour annuler) : " );
        		response = scanner2.nextInt();
        	} while ( response < -1 || response >= comptes.size() );
        	
        	CompteDAO compteDAO = new CompteDAO();
        	
        	System.out.println( "Etes vous sûr de vouloir supprimer  (" + comptes.get(response).getLabel() + ") ?(o/n)" );                     
			rep = scanner.nextLine();
			if (rep.equals("o")) {
          	System.out.println("delete "  );
	          	try {
	          		compteDAO.delete( comptes.get(response) );
	       		} catch ( java.sql.SQLException e ) {
	       			System.out.println( e.getMessage() );
	       		}
			} else {
        		System.out.println( "Aucun élément trouvé !!!" );
        	}
        }
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
	       System.out.println( "******************Transaction*******************" );
       
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
	   String type = "credit";
	   
	   Query query1 = em.createQuery("FROM Compte");
	   List<Compte> comptes = query1.getResultList();
				
	   System.out.println( "****************************************************" );
       System.out.println( "************Choix d'un compte à créditer************" );
       if ( comptes.size() > 0 ) {
       	System.out.println( "Sélectionnez le compte à créditer**************************************" );
       	System.out.println( "index    |id             | nom            | type                | solde" );
       	System.out.println( "***********************************************************************" );
       	int index = 0;
          
       	List<String> types = new ArrayList();
          
       	for ( Compte compte  : comptes ) {
   			System.out.println( index++ + "        | " + compte.getId_compte() + "            | " + compte.getLabel() + "            | " + compte.getType() + "            | " + compte.getSolde() );
   			types.add(compte.getType());
       	}
       	int response;
       	String rep;
       	do {
       		System.out.print( "Entrez Le n° Index du compte à créditer (-1 pour annuler) : " );
       		response = scanner2.nextInt();
       	} while ( response < -1 || response >= comptes.size() );
       	
       	if ( -1 != response ) {

       		if (types.get(response).equals("simple")) {
       			CompteSimple compteSimple = new CompteSimple();
       			compteSimple = (CompteSimple) comptes.get(response);
    
       			System.out.println( "Etes vous sûr de vouloir créditer le compte (" + compteSimple.getLabel() + ") ?(o/n)" );                     
       			rep = scanner.nextLine();
       			if (rep.equals("o")) {
	 	              	System.out.println("le compte à créditer : " + compteSimple.getLabel()  );
	 	              	System.out.println("Indiquez le montant à créditer : ");
	 	              	Double value = scanner2.nextDouble();
	 	              	System.out.println("Indiquez un label pour le crédit : ");
	 	              	String label = scanner.nextLine();
	 	              	System.out.println("Etes vous sûr de vouloir créditer de " + value + "€ le compte n° " + compteSimple.getId_compte() + " ?(o/n)");
	 	              	String validation = scanner.nextLine();
	 	              	
	 	              	if (validation.equals("o")) {
	 	              		Transaction transaction = new Transaction(value, label, compteSimple, type, compteSimple.getClient());
	 	              		compteSimple.setSolde(compteSimple.operation(value, type));
	 	              		
	 	              		em.getTransaction().begin();
	 	              		em.merge(compteSimple);
	 	              		em.persist(transaction);
	 	              		em.getTransaction().commit();
	 	              	}
	 	              	System.out.println("Le nouveau solde du compte : " + compteSimple.getSolde());
       			}
                
       		}else if (types.get(response).equals("payant")) {
	        	 ComptePayant comptePayant = new ComptePayant();
	        	 comptePayant = (ComptePayant) comptes.get(response);
	        	 
	        	 System.out.println( "Etes vous sûr de vouloir créditer le compte (" + comptePayant.getLabel() + ") ?(o/n)" );                     
	             rep = scanner.nextLine();
	             if (rep.equals("o")) {
	 	              	System.out.println("le compte à créditer : " + comptePayant.getLabel()  );
	 	              	System.out.println("Indiquez le montant à créditer : ");
	 	              	Double value = scanner2.nextDouble();
	 	              	System.out.println("Indiquez un label pour le crédit : ");
	 	              	String label = scanner.nextLine();
	 	              	System.out.println("Etes vous sûr de vouloir créditer de " + value + "€ le compte n° " + comptePayant.getId_compte() + " ?(o/n)");
	 	              	String validation = scanner.nextLine();
	 	              	
	 	              	if (validation.equals("o")) {
	 	              		Transaction transaction = new Transaction(value, label, comptePayant, type, comptePayant.getClient());
	 	              		comptePayant.setSolde(comptePayant.operation(value, type));
	 	              		
	 	              		em.getTransaction().begin();
	 	              		em.merge(comptePayant);
	 	              		em.persist(transaction);
	 	              		em.getTransaction().commit();
	 	              	}
	 	              	System.out.println("Le nouveau solde du compte : " + comptePayant.getSolde());
    			}
           	 
       		}else if (types.get(response).equals("epargne")){
            	 CompteEpargne compteEpargne = new CompteEpargne();
            	 compteEpargne = (CompteEpargne) comptes.get(response);
            	 
            	 System.out.println( "Etes vous sûr de vouloir créditer le compte (" + compteEpargne.getLabel() + ") ?(o/n)" );                     
            	 rep = scanner.nextLine();
            	 if (rep.equals("o")) {
	 	              	System.out.println("le compte à créditer : " + compteEpargne.getLabel()  );
	 	              	System.out.println("Indiquez le montant à créditer : ");
	 	              	Double value = scanner2.nextDouble();
	 	              	System.out.println("Indiquez un label pour le crédit : ");
	 	              	String label = scanner.nextLine();
	 	              	System.out.println("Etes vous sûr de vouloir créditer de " + value + "€ le compte n° " + compteEpargne.getId_compte() + " ?(o/n)");
	 	              	String validation = scanner.nextLine();
	 	              	
	 	              	if (validation.equals("o")) {
	 	              		Transaction transaction = new Transaction(value, label, compteEpargne, type, compteEpargne.getClient());
	 	              		compteEpargne.setSolde(compteEpargne.operation(value, type));
	 	              		
	 	              		em.getTransaction().begin();
	 	              		em.merge(compteEpargne);
	 	              		em.persist(transaction);
	 	              		em.getTransaction().commit();
	 	              	}
	 	              	System.out.println("Le nouveau solde du compte : " + compteEpargne.getSolde());
            	 }
       		}
       	} else {
       		System.out.println( "Aucun élément trouvé !!!" );
       	}
       }
       dspMainMenu();
   }

   public static void debitCompte() {
	   String type = "debit";
	   
	   Query query1 = em.createQuery("FROM Compte");
	   List<Compte> comptes = query1.getResultList();
				
	   System.out.println( "***************************************************" );
       System.out.println( "************Choix d'un compte à débiter************" );
       if ( comptes.size() > 0 ) {
       	System.out.println( "Sélectionnez le compte à débiter***************************************");
       	System.out.println( "index    |id             | nom            | type                | solde" );
       	System.out.println( "***********************************************************************" );
       	int index = 0;
          
       	List<String> types = new ArrayList();
          
       	for ( Compte compte  : comptes ) {
   			System.out.println( index++ + "        | " + compte.getId_compte() + "            | " + compte.getLabel() + "            | " + compte.getType() + "            | " + compte.getSolde() );
   			types.add(compte.getType());
       	}
       	int response;
       	String rep;
       	do {
       		System.out.print( "Entrez Le n° Index du compte à débiter (-1 pour annuler) : " );
       		response = scanner2.nextInt();
       	} while ( response < -1 || response >= comptes.size() );
       	
       	if ( -1 != response ) {
       		if (types.get(response).equals("simple")) {
       			CompteSimple compteSimple = new CompteSimple();
       			compteSimple = (CompteSimple) comptes.get(response);
    
       			System.out.println( "Etes vous sûr de vouloir débiter le compte (" + compteSimple.getLabel() + ") ?(o/n)" );                     
       			rep = scanner.nextLine();
       			if (rep.equals("o")) {
 	              	System.out.println("le compte à débiter : " + compteSimple.getLabel()  );
 	              	System.out.println("Indiquez le montant à débiter : ");
 	              	Double value = scanner2.nextDouble();
 	              	System.out.println("Indiquez un label pour le débit : ");
 	              	String label = scanner.nextLine();
 	              	System.out.println("Etes vous sûr de vouloir débiter de " + value + "€ le compte n° " + compteSimple.getId_compte() + " ?(o/n)");
 	              	String validation = scanner.nextLine();
 	              	
 	              	if (validation.equals("o")) {
 	              		Transaction transaction = new Transaction(value, label, compteSimple, type, compteSimple.getClient());
 	              		compteSimple.setSolde(compteSimple.operation(value, type));
 	              		
 	              		em.getTransaction().begin();
 	              		em.merge(compteSimple);
 	              		em.persist(transaction);
 	              		em.getTransaction().commit();
 	              	}
 	              System.out.println("Le nouveau solde du compte : " + compteSimple.getSolde());
   			}
                
       		}else if (types.get(response).equals("payant")) {
	        	 ComptePayant comptePayant = new ComptePayant();
	        	 comptePayant = (ComptePayant) comptes.get(response);
	        	 
	        	 System.out.println( "Etes vous sûr de vouloir débiter le compte  (" + comptePayant.getLabel() + ") ?(o/n)" );                     
	             rep = scanner.nextLine();
	             if (rep.equals("o")) {
	 	              	System.out.println("le compte à débiter : " + comptePayant.getLabel()  );
	 	              	System.out.println("Indiquez le montant à débiter : ");
	 	              	Double value = scanner2.nextDouble();
	 	              	System.out.println("Indiquez un label pour le débit : ");
	 	              	String label = scanner.nextLine();
	 	              	System.out.println("Etes vous sûr de vouloir débiter de " + value + "€ le compte n° " + comptePayant.getId_compte() + " ?(o/n)");
	 	              	String validation = scanner.nextLine();
	 	              	
	 	              	if (validation.equals("o")) {
	 	              		Transaction transaction = new Transaction(value, label, comptePayant, type, comptePayant.getClient());
	 	              		comptePayant.setSolde(comptePayant.operation(value, type));
	 	              		
	 	              		em.getTransaction().begin();
	 	              		em.merge(comptePayant);
	 	              		em.persist(transaction);
	 	              		em.getTransaction().commit();
	 	              	}
	 	              	System.out.println("Le nouveau solde du compte : " + comptePayant.getSolde());
	             }
           	 
       		}else if (types.get(response).equals("epargne")) {
            	 CompteEpargne compteEpargne = new CompteEpargne();
            	 compteEpargne = (CompteEpargne) comptes.get(response);
            	 
            	 System.out.println( "Etes vous sûr de vouloir débiter le compte  (" + compteEpargne.getLabel() + ") ?(o/n)" );                     
            	 rep = scanner.nextLine();
            	 if (rep.equals("o")) {
	 	              	System.out.println("le compte à débiter : " + compteEpargne.getLabel()  );
	 	              	System.out.println("Indiquez le montant à débiter : ");
	 	              	Double value = scanner2.nextDouble();
	 	              	System.out.println("Indiquez un label pour le débit : ");
	 	              	String label = scanner.nextLine();
	 	              	System.out.println("Etes vous sûr de vouloir débiter de " + value + "€ le compte n° " + compteEpargne.getId_compte() + " ?(o/n)");
	 	              	String validation = scanner.nextLine();
	 	              	
	 	              	if (validation.equals("o")) {
	 	              		Transaction transaction = new Transaction(value, label, compteEpargne, type, compteEpargne.getClient());
	 	              		compteEpargne.setSolde(compteEpargne.operation(value, type));
 	 	              		
	 	              		em.getTransaction().begin();
	 	              		em.merge(compteEpargne);
	 	              		em.persist(transaction);
	 	              		em.getTransaction().commit();
	 	              	}
	 	              	System.out.println("Le nouveau solde du compte : " + compteEpargne.getSolde());
            	 }
       		}
       	} else {
       		System.out.println( "Aucun élément trouvé !!!" );
       	}
       }
       dspMainMenu();
   }
}
