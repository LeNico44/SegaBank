/**
 * 
 */
package fr.epsi.segabank.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import fr.epsi.segabank.model.Compte;
/**
 * @author nicolas
 *
 */
@Entity
@Table(name="client")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="client_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String nom;
	private String prenom;
	@ManyToOne
	@JoinColumn(name="agence_id")
	private Agence agence;
	@OneToOne
	@JoinColumn(name="adresse_id")
	private Adresse adresse;
	@OneToMany(mappedBy="client")
	private List<Compte> comptes;
	@OneToMany(mappedBy="client")
	private List<Transaction> transactions;

	/**
	 * 
	 */
	
	public Client() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
