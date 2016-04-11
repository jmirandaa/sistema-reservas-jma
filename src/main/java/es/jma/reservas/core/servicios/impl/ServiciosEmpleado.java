/**
 * 
 */
package es.jma.reservas.core.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import es.jma.reservas.core.daos.ICrudDAO;
import es.jma.reservas.core.daos.IEmpleadoDAO;
import es.jma.reservas.core.daos.impl.EmpleadoDAOHibImpl;
import es.jma.reservas.core.dominio.negocio.Negocio;
import es.jma.reservas.core.dominio.usuarios.Empleado;
import es.jma.reservas.core.servicios.IServiciosEmpleado;

/**
 * @author jmiranda
 *
 */
public class ServiciosEmpleado extends ServiciosHibernate implements
		IServiciosEmpleado {
	private static ServiciosEmpleado instance;
	private IEmpleadoDAO empleadoDAO;
	
	
	//Singleton
	private ServiciosEmpleado(SessionFactory sessionFactory)
	{
		super(sessionFactory);
		this.empleadoDAO = EmpleadoDAOHibImpl.getInstance();
	}
	
	public static ServiciosEmpleado getInstance(SessionFactory sessionFactory)
	{
		if (instance == null)
		{
			instance = new ServiciosEmpleado(sessionFactory);
		}
		
		return instance;
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IEmpleadoDAO#consultarEmpleadosNegocio(es.jma.reservas.core.dominio.negocio.Negocio)
	 */
	@Override
	public List<Empleado> consultarEmpleadosNegocio(Negocio negocio)
			throws Exception {
		Session session = null;
    	List<Empleado> empleados = new ArrayList<Empleado> ();
		
		try
		{
			session = sessionFactory.openSession();				
			empleadoDAO.setSession(session);
			
			//Hacer la consulta
			empleados = empleadoDAO.consultarEmpleadosNegocio(negocio);
			
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			cerrarSesion(session);
		}
		
		return empleados;
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IEmpleadoDAO#guardarEmpleadoNegocio(es.jma.reservas.core.dominio.usuarios.Empleado, es.jma.reservas.core.dominio.negocio.Negocio)
	 */
	@Override
	public void guardarEmpleadoNegocio(Empleado empleado, Negocio negocio)
			throws Exception {
		Session session = null;
    	Transaction tx = null;
    	
		try
		{
			if (empleado != null)
			{
				if (txEnabled)
				{
					session = sessionFactory.openSession();
					tx = session.beginTransaction();
					empleadoDAO.setSession(session);
				}
				else
				{
					if (empleadoDAO.getSession() == null)
					{
						session = sessionFactory.openSession();
						tx = session.beginTransaction();
						empleadoDAO.setSession(session);
					}
				}
				
				//Guardar
				empleadoDAO.guardarEmpleadoNegocio(empleado, negocio);
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

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHibernateDAO#getSession()
	 */
	@Override
	public Session getSession() {
		return empleadoDAO.getSession();
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHibernateDAO#setSession(org.hibernate.Session)
	 */
	@Override
	public void setSession(Session session) {
		empleadoDAO.setSession(session);
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.servicios.impl.ServiciosHibernate#getCrudDAO()
	 */
	@Override
	protected ICrudDAO getCrudDAO() {
		return empleadoDAO;
	}

}
