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
@Table(name="codepostal")
public class CodePostal implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="codepostal_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String label;
	@OneToMany(mappedBy="codePostal")
	private List<Ville> villes;
	
	public CodePostal() {
		// TODO Auto-generated constructor stub
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

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}
	
	

}
