/**
 * 
 */
package es.jma.reservas.core.daos;

import java.util.List;

import es.jma.reservas.core.dominio.servicios.Servicio;
import es.jma.reservas.core.dominio.usuarios.Empleado;

/**
 * @author jmiranda
 *
 */
public interface IServicioDAO extends ICrudDAO {

	/**
	 * Consultar los servicios de un empleado
	 * @param empleado
	 * @return
	 */
	public List<Servicio> consultarServiciosEmpleado(Empleado empleado) throws Exception;
	
	/**
	 * Añadir un servicio a un empleado
	 * @param servicio
	 * @param empleado
	 * @throws Exception
	 */
	public void nuevoServicioEmpleado (Servicio servicio, Empleado empleado) throws Exception;
	
}
