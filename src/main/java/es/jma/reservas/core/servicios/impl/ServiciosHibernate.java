/**
 * 
 */
package es.jma.reservas.core.servicios.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase utilizada por los servicios de Hibernate
 * @author Jorge Miranda
 *
 */
public abstract class ServiciosHibernate {
	@Autowired
	protected SessionFactory sessionFactory;
	//Atributo para no hacer commits nunca
	protected boolean txEnabled;
	
	protected ServiciosHibernate(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
		this.txEnabled = true;
	}
	
	/**
	 * Hacer commit de una transacción si procede
	 * @param tx
	 */
	protected void commit(Transaction tx)
	{
		if (tx != null)
		{
			if (txEnabled)
			{
				tx.commit();
			}

		}
	}
	
	
	/**
	 * Hacer rollback de una transacción si procede
	 * @param tx
	 */
	protected void rollback(Transaction tx)
	{
		if (tx != null)
		{
			tx.rollback();
		}
	}
	
	/**
	 * Cerrar sesión de Hibernate si procede
	 * @param session
	 */
	protected void cerrarSesion(Session session)
	{
		if ((session != null) && (txEnabled))
		{
			session.close();
		}
	}
	

	/**
	 * @return the txEnabled
	 */
	public boolean isTxEnabled() {
		return txEnabled;
	}

	/**
	 * @param txEnabled the txEnabled to set
	 */
	public void setTxEnabled(boolean txEnabled) {
		this.txEnabled = txEnabled;
	}
	
	
}
