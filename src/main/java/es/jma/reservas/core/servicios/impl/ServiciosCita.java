/**
 * 
 */
package es.jma.reservas.core.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import es.jma.reservas.core.daos.ICitaDAO;
import es.jma.reservas.core.daos.ICrudDAO;
import es.jma.reservas.core.daos.impl.CitaDAOHibImpl;
import es.jma.reservas.core.dominio.citas.Cita;
import es.jma.reservas.core.dominio.usuarios.Cliente;
import es.jma.reservas.core.dominio.usuarios.Empleado;
import es.jma.reservas.core.servicios.IServiciosCita;

/**
 * @author tulon
 *
 */
public class ServiciosCita extends ServiciosHibernate implements IServiciosCita {
	private static ServiciosCita instance;
	private ICitaDAO citaDAO;
	
	//Singleton
	private ServiciosCita(SessionFactory sessionFactory)
	{
		super(sessionFactory);
		citaDAO = CitaDAOHibImpl.getInstance();
	}
	
	public static ServiciosCita getInstance(SessionFactory sessionFactory)
	{
		if (instance == null)
		{
			instance = new ServiciosCita(sessionFactory);
		}
		
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.ICitaDAO#consultarCitasEmpleado(es.jma.reservas.core.dominio.usuarios.Empleado)
	 */
	@Override
	public List<Cita> consultarCitasEmpleado(Empleado empleado) {
		List <Cita> resultado = new ArrayList <Cita> ();
		Session session = null;
		
		try
		{
			session = sessionFactory.openSession();				
			getCrudDAO().setSession(session);
			
			//Hacer la consulta
			resultado = citaDAO.consultarCitasEmpleado(empleado);
		}
		catch(Exception e)
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
	 * @see es.jma.reservas.core.daos.ICitaDAO#consultarCitasCliente(es.jma.reservas.core.dominio.usuarios.Cliente)
	 */
	@Override
	public List<Cita> consultarCitasCliente(Cliente cliente) {
		List <Cita> resultado = new ArrayList <Cita> ();
		Session session = null;
		
		try
		{
			session = sessionFactory.openSession();				
			getCrudDAO().setSession(session);
			
			//Hacer la consulta
			resultado = citaDAO.consultarCitasCliente(cliente);
		}
		catch(Exception e)
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
	 * @see es.jma.reservas.core.daos.IHibernateDAO#getSession()
	 */
	@Override
	public Session getSession() {
		return citaDAO.getSession();
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHibernateDAO#setSession(org.hibernate.Session)
	 */
	@Override
	public void setSession(Session session) {
		citaDAO.setSession(session);
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.servicios.impl.ServiciosHibernate#getCrudDAO()
	 */
	@Override
	protected ICrudDAO getCrudDAO() {
		return citaDAO;
	}

}
