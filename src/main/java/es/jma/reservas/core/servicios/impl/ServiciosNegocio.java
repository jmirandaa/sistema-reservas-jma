/**
 * 
 */
package es.jma.reservas.core.servicios.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import es.jma.reservas.core.daos.ICrudDAO;
import es.jma.reservas.core.daos.INegocioDAO;
import es.jma.reservas.core.daos.impl.NegocioDAOHibImpl;
import es.jma.reservas.core.dominio.negocio.Negocio;
import es.jma.reservas.core.servicios.IServiciosNegocio;

/**
 * @author jmiranda
 *
 */
public class ServiciosNegocio extends ServiciosHibernate implements IServiciosNegocio {
	private INegocioDAO negocioDAO;
	private static ServiciosNegocio instance;
	
	//Singleton
	public static ServiciosNegocio getInstance(SessionFactory sessionFactory)
	{
		if (instance == null)
		{
			instance = new ServiciosNegocio(sessionFactory);
		}
		
		return instance;
	}
	
	private ServiciosNegocio (SessionFactory sessionFactory)
	{
		super(sessionFactory);
		this.negocioDAO = NegocioDAOHibImpl.getInstance();
	}
	
	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHibernateDAO#getSession()
	 */
	@Override
	public Session getSession() {
		return negocioDAO.getSession();
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHibernateDAO#setSession(org.hibernate.Session)
	 */
	@Override
	public void setSession(Session session) {
		negocioDAO.setSession(session);

	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.INegocioDAO#consultarNegocioId(int)
	 */
	@Override
	public Negocio consultarNegocioId(int id) {
		Negocio resultado = new Negocio ();
		Session session = null;
		
		try
		{
			session = sessionFactory.openSession();				
			getCrudDAO().setSession(session);
			
			//Hacer la consulta
			resultado = negocioDAO.consultarNegocioId(id);
			
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			cerrarSesion(session);
		}
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.servicios.impl.ServiciosHibernate#getCrudDAO()
	 */
	@Override
	protected ICrudDAO getCrudDAO() {
		return negocioDAO;
	}

}
