package fr.epsi.segabank.model;

import java.sql.Date;

import javax.persistence.*;
/**
 * @author lolo
 *
 */
//Declaration de la classe a JPA 
//Toujours choisir "persistence"
@Entity
@Table(name = "transaction")

public class Transaction {
	
    @Id
    @Column(name = "id_transac")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_transac;
	private double value;
	private Date date;
	private String label;
	private boolean type;
	
	@ManyToOne
	@JoinColumn(name = "id_compte")
	private Compte compte;
	
	@ManyToOne
	@JoinColumn(name= "client_id")
	private Client client;
	
	public Transaction() {
		
	}	
	
	public Integer getId_transac() {
		return id_transac;
	}

	public void setId_transac(Integer id_transac) {
		this.id_transac = id_transac;
	}


	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}


	public void  debit(){
		
	}
	public void credit() {
		
	}
	
	
	
}
