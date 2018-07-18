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
	private int code;
	@OneToMany(mappedBy="codePostal")
	private List<Ville> villes;
	
	public CodePostal() {
		// TODO Auto-generated constructor stub
	}
	
	public CodePostal(int codePostal) {
		// TODO Auto-generated constructor stub
		this.code = codePostal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}
	
	

}
