/**
 * 
 */
package fr.epsi.segabank.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

/**
 * @author nicolas
 *
 */
@Entity
@Table(name="adresse")
public class Adresse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="adresse_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String label;
	@ManyToOne
	@JoinColumn(name="ville_id")
	private Ville ville;
	@ManyToOne
	@JoinColumn(name="agence_id")
	private Agence agence;
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;

	public Adresse() {
		// TODO Auto-generated constructor stub
	}
	public Adresse(String label) {
		// TODO Auto-generated constructor stub
		this.label = label; 
	}
	
	public Adresse(String label, Agence agence, Ville ville) {
		// TODO Auto-generated constructor stub
		this.label = label; 
		this.agence = agence;
		this.ville = ville;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
}
