/**
 * 
 */
package es.jma.reservas.core.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import es.jma.reservas.core.daos.ICrudDAO;
import es.jma.reservas.core.daos.IServicioDAO;
import es.jma.reservas.core.daos.impl.ServicioDAOHibImpl;
import es.jma.reservas.core.dominio.servicios.Servicio;
import es.jma.reservas.core.dominio.usuarios.Empleado;
import es.jma.reservas.core.servicios.IServiciosServicios;

/**
 * @author jmiranda
 *
 */
public class ServiciosServicio extends ServiciosHibernate implements
		IServiciosServicios {
	private IServicioDAO serviciosDAO;
	private static ServiciosServicio instance;
	
	//Singleton
	public static ServiciosServicio getInstance(SessionFactory sessionFactory)
	{
		if (instance == null)
		{
			instance = new ServiciosServicio(sessionFactory);
		}
		
		return instance;
	}
	
	public ServiciosServicio(SessionFactory sessionFactory)
	{
		super(sessionFactory);
		this.serviciosDAO = ServicioDAOHibImpl.getInstance();
	}
	
	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IServicioDAO#consultarServiciosEmpleado(es.jma.reservas.core.dominio.usuarios.Empleado)
	 */
	@Override
	public List<Servicio> consultarServiciosEmpleado(Empleado empleado) throws Exception{
		List <Servicio> resultado = new ArrayList <Servicio> ();
		Session session = null;
		
		try
		{
			session = sessionFactory.openSession();				
			getCrudDAO().setSession(session);
			
			//Hacer la consulta
			resultado = serviciosDAO.consultarServiciosEmpleado(empleado);			
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
		return serviciosDAO.getSession();
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHibernateDAO#setSession(org.hibernate.Session)
	 */
	@Override
	public void setSession(Session session) {
		serviciosDAO.setSession(session);
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.servicios.impl.ServiciosHibernate#getCrudDAO()
	 */
	@Override
	protected ICrudDAO getCrudDAO() {
		return serviciosDAO;
	}

}
