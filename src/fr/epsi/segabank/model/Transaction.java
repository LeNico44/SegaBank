package fr.epsi.segabank.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
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
	@CreationTimestamp
	private LocalDateTime date;
	private String label;
	private String type;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "id_compte")
	private Compte compte;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name= "client_id")
	private Client client;
	
	public Transaction() {
		
	}
	
	public Transaction(double value, String label, Compte compte, String type, Client client) {
		this.value = value;
		this.label = label;
		this.compte = compte;
		this.type = type;
		this.client = client;
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
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String isType() {
		return type;
	}

	public void setType(String type) {
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
