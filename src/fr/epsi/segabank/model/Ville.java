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
@Table(name="ville")
public class Ville implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ville_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String label;
	@OneToMany(mappedBy="ville")
	private List<Adresse> adresses;
	@ManyToOne
	@JoinColumn(name="codepostal_id")
	private CodePostal codePostal;

	public Ville() {
		// TODO Auto-generated constructor stub
	}
	
	public Ville(String label) {
		// TODO Auto-generated constructor stub
		this.label = label;
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

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	public CodePostal getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(CodePostal codePostal) {
		this.codePostal = codePostal;
	}
	
	

}
