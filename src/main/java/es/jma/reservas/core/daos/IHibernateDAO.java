/**
 * 
 */
package es.jma.reservas.core.daos;

import org.hibernate.Session;

/**
 * Definición de métodos para las sesiones de Hibernate
 * @author Jorge Miranda
 *
 */
public interface IHibernateDAO {
	/**
	 * Get de la sesión
	 * @return
	 */
	public Session getSession();
	
	/**
	 * Set de la sesión
	 * @param session
	 */
	public void setSession(Session session);
	
}
