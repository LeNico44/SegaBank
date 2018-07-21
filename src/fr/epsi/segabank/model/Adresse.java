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
	private int numeroVoie;
	private String voie;
	private int codePostal;
	private String ville;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="agence_id")
	private Agence agence;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="client_id")
	private Client client;

	public Adresse() {
		// TODO Auto-generated constructor stub
	}
	public Adresse(int numeroVoie, String voie, int codePostal, String ville) {
		// TODO Auto-generated constructor stub
		this.numeroVoie = numeroVoie; 
		this.voie = voie;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	public Adresse(int numeroVoie, String voie, int codePostal, String ville, Agence agence) {
		// TODO Auto-generated constructor stub
		this.numeroVoie = numeroVoie; 
		this.voie = voie;
		this.codePostal = codePostal;
		this.ville = ville;
		this.agence = agence;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumeroVoie() {
		return numeroVoie;
	}
	public void setNumeroVoie(int numeroVoie) {
		this.numeroVoie = numeroVoie;
	}
	public String getVoie() {
		return voie;
	}
	public void setVoie(String voie) {
		this.voie = voie;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
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
