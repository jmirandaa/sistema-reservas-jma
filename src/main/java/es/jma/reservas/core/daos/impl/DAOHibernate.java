/**
 * 
 */
package es.jma.reservas.core.daos.impl;

import org.hibernate.Session;


/**
 * @author jmiranda
 *
 */
public abstract class DAOHibernate {
	protected Session session;

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public abstract void setSession(Session session);
	
	
}
