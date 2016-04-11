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
import es.jma.reservas.core.daos.IHorarioDAO;
import es.jma.reservas.core.daos.impl.HorarioDAOHibImpl;
import es.jma.reservas.core.dominio.citas.Horario;
import es.jma.reservas.core.dominio.usuarios.Empleado;
import es.jma.reservas.core.servicios.IServiciosHorario;

/**
 * @author tulon
 *
 */
public class ServiciosHorario extends ServiciosHibernate implements IServiciosHorario {
	private static ServiciosHorario instance;
	private IHorarioDAO horarioDAO;
	
	//Singleton
	private ServiciosHorario(SessionFactory sessionFactory)
	{
		super(sessionFactory);
		horarioDAO = HorarioDAOHibImpl.getInstance();
	}
	
	public static ServiciosHorario getInstance (SessionFactory sessionFactory)
	{
		if (instance == null)
		{
			instance = new ServiciosHorario(sessionFactory);
		}
		
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHorarioDAO#guardarHorarioEmpleado(es.jma.reservas.core.dominio.citas.Horario, es.jma.reservas.core.dominio.usuarios.Empleado)
	 */
	@Override
	public void guardarHorarioEmpleado(Horario horario, Empleado empleado) throws Exception {
		Session session = null;
    	Transaction tx = null;
    	
		try
		{
			if (horario != null)
			{
				if (txEnabled)
				{
					session = sessionFactory.openSession();
					tx = session.beginTransaction();
					horarioDAO.setSession(session);
				}
				else
				{
					if (horarioDAO.getSession() == null)
					{
						session = sessionFactory.openSession();
						tx = session.beginTransaction();
						horarioDAO.setSession(session);
					}
				}
				
				//Guardar
				horarioDAO.guardarHorarioEmpleado(horario, empleado);
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
	 * @see es.jma.reservas.core.daos.IHorarioDAO#consultarHorarioEmpleado(es.jma.reservas.core.dominio.usuarios.Empleado)
	 */
	@Override
	public List<Horario> consultarHorarioEmpleado(Empleado empleado) throws Exception {
		List <Horario> resultado = new ArrayList <Horario> ();
		Session session = null;
		
		try
		{
			session = sessionFactory.openSession();				
			getCrudDAO().setSession(session);
			
			//Hacer la consulta
			resultado = horarioDAO.consultarHorarioEmpleado(empleado);		
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
		return horarioDAO.getSession();
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHibernateDAO#setSession(org.hibernate.Session)
	 */
	@Override
	public void setSession(Session session) {
		horarioDAO.setSession(session);

	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.servicios.impl.ServiciosHibernate#getCrudDAO()
	 */
	@Override
	protected ICrudDAO getCrudDAO() {
		return horarioDAO;
	}

}
