/**
 * 
 */
package fr.epsi.segabank.dao;

import java.sql.SQLException;

import fr.epsi.segabank.model.Compte;

/**
 * @author nicolas
 *
 */
public abstract interface IDAO<T> {
	
	public abstract void create(T o) throws SQLException;
	public abstract void update(T o) throws SQLException;
	public abstract void delete(T o) throws SQLException;
	
}
