/**
 * 
 */
package es.jma.reservas.core.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import es.jma.reservas.core.daos.ICrudDAO;

/**
 * Clase utilizada por los servicios de Hibernate
 * @author Jorge Miranda
 *
 */
public abstract class ServiciosHibernate implements ICrudDAO{
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
	
	@Override
	public void nuevo (Object objeto) throws Exception
	{
		Session session = null;
    	Transaction tx = null;
    	
		try
		{
			if (objeto != null)
			{
				if (txEnabled)
				{
					session = sessionFactory.openSession();
					tx = session.beginTransaction();
					getCrudDAO().setSession(session);
				}
				else
				{
					if (getCrudDAO().getSession() == null)
					{
						session = sessionFactory.openSession();
						tx = session.beginTransaction();
						getCrudDAO().setSession(session);
					}
				}
				
				//Insertar objeto
				getCrudDAO().nuevo(objeto);
				commit(tx);
			}
		}
		catch (Exception e)
		{
			rollback(tx);
			throw e;
		}
		finally
		{
			cerrarSesion(session);
		}		
	}
	
	@Override
	public void actualizar (Object objeto) throws Exception
	{
		Session session = null;
    	Transaction tx = null;
    	
		try
		{
			if (objeto != null)
			{
				session = sessionFactory.openSession();
				tx = session.beginTransaction();				
				getCrudDAO().setSession(session);
			
				//Actualizar
				getCrudDAO().actualizar(objeto);
				commit(tx);
			}
		}
		catch (Exception e)
		{
			rollback(tx);
			throw e;
		}
		finally
		{
			cerrarSesion(session);
		}		
	}
	
	@Override
	public void borrar (Object objeto) throws Exception
	{
		Session session = null;
    	Transaction tx = null;
    	
		try
		{
			if (objeto != null)
			{
				session = sessionFactory.openSession();
				tx = session.beginTransaction();				
				getCrudDAO().setSession(session);
			
				//Borrar
				getCrudDAO().borrar(objeto);
				commit(tx);
			}
		}
		catch (Exception e)
		{
			rollback(tx);
			throw e;
		}
		finally
		{
			cerrarSesion(session);
		}		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List consultarTodos() throws Exception
	{
		List resultados = new ArrayList ();

		Session session = null;
		
		try
		{
			session = sessionFactory.openSession();				
			getCrudDAO().setSession(session);
			
			//Hacer la consulta
			resultados = getCrudDAO().consultarTodos();
			
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			cerrarSesion(session);
		}
		
		return resultados;
	}
	
	protected abstract ICrudDAO getCrudDAO();
}
