/**
 * 
 */
package es.jma.reservas.core.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import es.jma.reservas.core.daos.IServicioDAO;
import es.jma.reservas.core.dominio.servicios.Servicio;
import es.jma.reservas.core.dominio.usuarios.Empleado;

/**
 * @author jmiranda
 *
 */
public class ServicioDAOHibImpl extends CrudDAOHibImpl implements IServicioDAO {
	private static ServicioDAOHibImpl instance;
	
	//Singleton
	private ServicioDAOHibImpl()
	{
		super(Servicio.class);
	}
	
	public static ServicioDAOHibImpl getInstance()
	{
		if (instance == null)
		{
			instance = new ServicioDAOHibImpl();
		}
		
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IServicioDAO#consultarServiciosEmpleado(es.jma.reservas.core.dominio.usuarios.Empleado)
	 */
	@Override
	@Transactional
	public List<Servicio> consultarServiciosEmpleado(Empleado empleado) {
		List<Servicio> servicios = new ArrayList<Servicio> ();
		
		try
		{
			//Consultar servicios de empleado
			//String hql = "from Negocio where id = :id";
			String hql = "SELECT empleado.servicios FROM Empleado empleado WHERE empleado.id = :id";
			Query query = session.createQuery(hql);
			query.setLong("id", empleado.getId());
			@SuppressWarnings("unchecked")
			List<Servicio> results = query.list();
			
			if ((results != null ) && (results.size() > 0))
			{
				servicios = results;
			}			
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return servicios;
	}

	@Override
	public void nuevoServicioEmpleado(Servicio servicio, Empleado empleado) throws Exception {
		try
		{
			List <Servicio> servicios = empleado.getServicios();
			if (servicios == null)
			{
				servicios = new ArrayList<Servicio> ();
				empleado.setServicios(servicios);
			}
			empleado.getServicios().add(servicio);
			this.nuevo(empleado);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

}
