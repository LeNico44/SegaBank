/**
 * 
 */
package fr.epsi.segabank.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import fr.epsi.segabank.model.Compte;
import fr.epsi.segabank.model.Client;
/**
 * @author nicolas
 *
 */
@Entity
@Table(name="agence")
public class Agence implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="agence_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private int codeAgence;
	private String label;
	@OneToMany(mappedBy="agence")
	private List<Compte> comptes;
	@OneToMany(mappedBy="agence")
	private List<Client> clients;
	@ManyToOne
	@JoinColumn(name="adresse_id")
	private Adresse adresse;
	
	/**
	 * 
	 */
	
	public Agence() {
		// TODO Auto-generated constructor stub
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodeAgence() {
		return codeAgence;
	}

	public void setCodeAgence(int codeAgence) {
		this.codeAgence = codeAgence;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	
}
