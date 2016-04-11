/**
 * 
 */
package es.jma.reservas.core.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import es.jma.reservas.core.daos.ICitaDAO;
import es.jma.reservas.core.dominio.citas.Cita;
import es.jma.reservas.core.dominio.usuarios.Cliente;
import es.jma.reservas.core.dominio.usuarios.Empleado;

/**
 * @author tulon
 *
 */
public class CitaDAOHibImpl extends CrudDAOHibImpl implements ICitaDAO {
	private static CitaDAOHibImpl instance;
	
	//Singleton
	private CitaDAOHibImpl()
	{
		super(Cita.class);
	}
	
	public static CitaDAOHibImpl getInstance()
	{
		if (instance == null)
		{
			instance = new CitaDAOHibImpl();
		}
		
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.ICitaDAO#consultarCitasEmpleado(es.jma.reservas.core.dominio.usuarios.Empleado)
	 */
	@Override
	@Transactional
	public List<Cita> consultarCitasEmpleado(Empleado empleado) {
		List<Cita> citas = new ArrayList<Cita> ();
		
		try
		{
			//Consultar cita por el id del Empleado
			String hql = "SELECT empl.citas from Empleado empl where empl.id = :id";
			Query query = session.createQuery(hql);
			query.setLong("id", empleado.getId());
			@SuppressWarnings("unchecked")
			List<Cita> results = query.list();
			
			if ((results != null ) && (results.size() > 0))
			{
				citas = results;
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return citas;
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.ICitaDAO#consultarCitasCliente(es.jma.reservas.core.dominio.usuarios.Cliente)
	 */
	@Override
	@Transactional
	public List<Cita> consultarCitasCliente(Cliente cliente) {
		List<Cita> citas = new ArrayList<Cita> ();
		
		try
		{
			//Consultar cita por el id del Empleado
			String hql = "SELECT cli.citas from Cliente cli where cli.id = :id";
			Query query = session.createQuery(hql);
			query.setLong("id", cliente.getId());
			@SuppressWarnings("unchecked")
			List<Cita> results = query.list();
			
			if ((results != null ) && (results.size() > 0))
			{
				citas = results;
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return citas;
	}

}
