/**
 * 
 */
package fr.epsi.segabank.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import fr.epsi.tpjpa.model.Animal;
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
	@OneToMany(mappedBy="compte")
	private List<Compte> comptes;

	/**
	 * 
	 */
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Agence() {
		// TODO Auto-generated constructor stub
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

}
