/**
 * 
 */
package es.jma.reservas.core.servicios.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import es.jma.reservas.core.daos.ICitaDAO;
import es.jma.reservas.core.daos.ICrudDAO;
import es.jma.reservas.core.daos.impl.CitaDAOHibImpl;
import es.jma.reservas.core.dominio.citas.Cita;
import es.jma.reservas.core.dominio.citas.Franja;
import es.jma.reservas.core.dominio.citas.Horario;
import es.jma.reservas.core.dominio.citas.Slot;
import es.jma.reservas.core.dominio.servicios.Servicio;
import es.jma.reservas.core.dominio.usuarios.Cliente;
import es.jma.reservas.core.dominio.usuarios.Empleado;
import es.jma.reservas.core.servicios.IServiciosCita;

/**
 * Servicio de Cita
 * @author Jorge Miranda
 *
 */
public class ServiciosCita extends ServiciosHibernate implements IServiciosCita {
	public static final long TAM_FRANJA = 15 * 60000;
	
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

	@Override
	public List<Franja> consultasFranjasLibresDia(Empleado empleado, Date dia,
			Servicio servicio, Slot slot) throws Exception{
		
		List<Franja> franjas = new ArrayList<Franja> ();
		Session session = null;
		
		try
		{
			session = sessionFactory.openSession();				
			getCrudDAO().setSession(session);
			
			//Si el empleado no presta el servicio, fin
			List <Servicio> servicios = empleado.getServicios();
			if (servicios.contains(servicio))
			{				
				//Obtener las citas del día
				List<Cita> citasEmpleado = this.consultarCitasEmpleado(empleado, dia);
				
				franjas = calcularHorarioEmpleado(empleado.getHorario(), dia, servicio, citasEmpleado);
				//Recuperar horario del empleado
							
			}
			

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			cerrarSesion(session);
		}
		
		return franjas;
	}

	@Override
	public List<Cita> consultarCitasEmpleado(Empleado empleado, Date dia) {
		List <Cita> resultado = new ArrayList <Cita> ();
		Session session = null;
		
		try
		{
			session = sessionFactory.openSession();				
			getCrudDAO().setSession(session);
			
			//Hacer la consulta
			resultado = citaDAO.consultarCitasEmpleado(empleado,dia);
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
	
	//Métodos privados
	/**
	 * Crear franjas de horas libres de un empleado
	 * @param horario
	 * @param fecha
	 * @param servicio
	 * @param citas
	 * @return
	 * @throws Exception
	 */
	private List<Franja> calcularHorarioEmpleado(List<Horario> horario, Date fecha, Servicio servicio, List<Cita> citas) throws Exception
	{
		List<Franja> franjas = new ArrayList<Franja> ();
	
		try
		{
			
		//Extraer número de día de la semana
		SimpleDateFormat sdfDia = new SimpleDateFormat("u");
		String diaSemanaString = sdfDia.format(fecha);
		
		//Iterar horario
		for (Horario horarioDia : horario)
		{
			//Buscar si el horario es del mismo día
			if (horarioDia.getDiaSemana() == Integer.parseInt(diaSemanaString))
			{	
				franjas = calcularFranjaHorario(horarioDia.getHoraInicio(), horarioDia.getHoraFin(), servicio,citas);
				
				/*//Convertir la hora de la cita a 1970
				SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
				String horaCitaString = sdfHora.format(fecha);
				Date horaCita = sdfHora.parse(horaCitaString);
				
				//Buscar si la hora está dentro del horario
				if ((horaCita.getTime() >= horarioDia.getHoraInicio().getTime()) && (horaCita.getTime() <= horarioDia.getHoraFin().getTime()))
				{
					//Comprobar duración servicio
					
					//Comprobar si hay otras citas que se solapen
					resultado = true;
				}*/
				
				break;
			}
		}
		
		}
		catch (Exception e)
		{
			throw e;
			
		}
		return franjas;
	}
	
	/**
	 * Crear franjas de horas libres
	 * @param horaIni
	 * @param horaFin
	 * @param servicio
	 * @param citas
	 * @return
	 * @throws Exception
	 */
	private List<Franja> calcularFranjaHorario(Date horaIni, Date horaFin, Servicio servicio,List<Cita> citas) throws Exception
	{
		List <Franja> franjas = new ArrayList<Franja> ();
		
		long horaIniMili = horaIni.getTime();
		long horaFinMili = horaFin.getTime();
		
		long horaIterador = horaIniMili;
		//Crear franjas durante el horario
		while (horaIterador < horaFinMili)
		{		
			Franja franja = new Franja();
			franja.setHoraIni(new Date(horaIterador));
			horaIterador += TAM_FRANJA;
			franja.setHoraFin(new Date(horaIterador));
			
			//Si horaInicio + duracion servicio > algo, no añadir
			long franjaIniMili = franja.getHoraIni().getTime();
			long duracionServicioMili = servicio.getDuracionMin()*60000;
			
			//Caso 1: Franjas cuya finalización sea mayor a horario empleado
			if ((!((franjaIniMili + duracionServicioMili) > horaFinMili))
				&&
				(!franjaEnCita(franjaIniMili,duracionServicioMili,citas)))
			{
				franjas.add(franja);
			}
		}
		
		return franjas;
	}
	
	/**
	 * Comprobar si es una franja hay solapamiento con alguna cita
	 * @param franjaIniMili
	 * @param duracionServicioMili
	 * @param citas
	 * @return
	 * @throws Exception
	 */
	private boolean franjaEnCita (long franjaIniMili, long duracionServicioMili, List<Cita> citas) throws Exception
	{
		boolean resultado = false;
		
		try
		{
			
			if ((citas != null) && (citas.size() > 0))
			{
				//Iterar citas
				for (Cita cita : citas)
				{
					//Convertir a 1970
					SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
					String horaCitaString = sdfHora.format(cita.getFechaCita());
					Date horaCita = sdfHora.parse(horaCitaString);
					
					//Pasar a milisegundos
					long horaIniCita = horaCita.getTime();
					long horaFinCita = horaIniCita + cita.getServicio().getDuracionMin()*60000;
					
					//Ver si la cita solapa la franja
					if ((((franjaIniMili+ duracionServicioMili) > horaIniCita) && ((franjaIniMili+duracionServicioMili)<=horaFinCita))
						||
						(((franjaIniMili) > horaIniCita) && ((franjaIniMili)<=horaFinCita)))
					{
						resultado = true;
						break;
					}
				}
			}
		
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return resultado;
	}
}
