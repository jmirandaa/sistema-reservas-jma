/**
 * 
 */
package es.jma.reservas.core.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import es.jma.reservas.core.daos.IEmpleadoDAO;
import es.jma.reservas.core.dominio.negocio.Negocio;
import es.jma.reservas.core.dominio.usuarios.AbstractPersona;
import es.jma.reservas.core.dominio.usuarios.Empleado;

/**
 * @author jmiranda
 *
 */
public class EmpleadoDAOHibImpl extends CrudDAOHibImpl implements IEmpleadoDAO {
	public static EmpleadoDAOHibImpl instance;
	
	//Singleton
	private EmpleadoDAOHibImpl()
	{
		super(Empleado.class);
	}
	
	public static EmpleadoDAOHibImpl getInstance()
	{
		if (instance == null)
		{
			instance = new EmpleadoDAOHibImpl();
		}
		
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IEmpleadoDAO#consultarEmpleadosNegocio(es.jma.reservas.core.dominio.negocio.Negocio)
	 */
	@Override
	@Transactional
	public List<Empleado> consultarEmpleadosNegocio(Negocio negocio)
			throws Exception {
		List<Empleado> empleados = new ArrayList<Empleado> ();
		
		try
		{
			//Consultar clientes por el id del Negocio
			String hql = "SELECT neg.personas from Negocio neg where neg.id = :id";
			Query query = session.createQuery(hql);
			query.setLong("id", negocio.getId());
			@SuppressWarnings("unchecked")
			List<AbstractPersona> results = query.list();
			
			if ((results != null ) && (results.size() > 0))
			{
				for (AbstractPersona persona : results)
				{
					if (persona instanceof Empleado)
					{
						Empleado empleado = (Empleado) persona;
						empleados.add(empleado);
					}
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		return empleados;
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IEmpleadoDAO#guardarEmpleadoNegocio(es.jma.reservas.core.dominio.usuarios.Empleado, es.jma.reservas.core.dominio.negocio.Negocio)
	 */
	@Override
	@Transactional
	public void guardarEmpleadoNegocio(Empleado empleado, Negocio negocio)
			throws Exception {
		try
		{
			List <AbstractPersona> personas = negocio.getPersonas();
			if (personas == null)
			{
				personas = new ArrayList<AbstractPersona> ();
				negocio.setPersonas(personas);
			}
			negocio.getPersonas().add(empleado);
			this.nuevo(negocio);
		}
		catch (Exception e)
		{
			throw e;
		}

	}

}
