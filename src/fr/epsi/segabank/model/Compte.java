package fr.epsi.segabank.model;

import java.util.*;

import javax.persistence.*;

/**
 * @author lolo
 *
 */
//Declaration de la classe a JPA 
//Toujours choisir "persistence"
@Entity
@Table(name = "compte")
public class Compte {

	//Mapping ID
	@Id	
	@Column(name = "id_compte")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_compte;
	
	private String label;
	private double solde;
	
	@OneToMany(mappedBy="compte")
	private List<Transaction> transaction;
	
	@ManyToOne
	@JoinColumn(name= "client_id")
	private Client client;
	
	public Compte() {
		
	}

	public int getId_compte() {
		return id_compte;
	}

	public void setId_compte(int id_compte) {
		this.id_compte = id_compte;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	

	
}
