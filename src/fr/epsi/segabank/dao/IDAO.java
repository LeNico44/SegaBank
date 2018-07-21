/**
 * 
 */
package fr.epsi.segabank.dao;

import java.sql.SQLException;

/**
 * @author nicolas
 *
 */
public interface IDAO<T> {
	
	public void create( T o ) throws SQLException;
	public void update( T o ) throws SQLException;
	public void delete( T o ) throws SQLException;
	
}
