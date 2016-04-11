/**
 * 
 */
package es.jma.reservas.core.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import es.jma.reservas.core.daos.IHorarioDAO;
import es.jma.reservas.core.dominio.citas.Horario;
import es.jma.reservas.core.dominio.usuarios.Empleado;

/**
 * @author tulon
 *
 */
public class HorarioDAOHibImpl extends CrudDAOHibImpl implements IHorarioDAO {
	private static HorarioDAOHibImpl instance;
	
	//Singleton
	private HorarioDAOHibImpl()
	{
		super(Horario.class);
	}
	
	public static HorarioDAOHibImpl getInstance()
	{
		if (instance == null)
		{
			instance = new HorarioDAOHibImpl();
		}
		
		return instance;
	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHorarioDAO#guardarHorarioEmpleado(es.jma.reservas.core.dominio.citas.Horario, es.jma.reservas.core.dominio.usuarios.Empleado)
	 */
	@Override
	public void guardarHorarioEmpleado(Horario horario, Empleado empleado) throws Exception{
		try
		{
			List <Horario> horarios = empleado.getHorario();
			if (horarios == null)
			{
				horarios = new ArrayList<Horario> ();
				empleado.setHorario(horarios);
			}
			empleado.getHorario().add(horario);
			this.nuevo(empleado);
		}
		catch (Exception e)
		{
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see es.jma.reservas.core.daos.IHorarioDAO#consultarHorarioEmpleado(es.jma.reservas.core.dominio.usuarios.Empleado)
	 */
	@Override
	@Transactional
	public List<Horario> consultarHorarioEmpleado(Empleado empleado) throws Exception{
		List<Horario> horario = new ArrayList<Horario> ();
		
		try
		{
			//Consultar horario por el id del Empleado
			String hql = "SELECT empl.horario from Empleado empl where empl.id = :id";
			Query query = session.createQuery(hql);
			query.setLong("id", empleado.getId());
			@SuppressWarnings("unchecked")
			List<Horario> results = query.list();
			
			if ((results != null ) && (results.size() > 0))
			{
				horario = results;
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return horario;
	}

}
