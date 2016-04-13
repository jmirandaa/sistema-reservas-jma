/**
 * 
 */
package es.jma.reservas.core.daos;

import java.util.Date;
import java.util.List;

import es.jma.reservas.core.dominio.citas.Cita;
import es.jma.reservas.core.dominio.citas.Franja;
import es.jma.reservas.core.dominio.citas.Slot;
import es.jma.reservas.core.dominio.servicios.Servicio;
import es.jma.reservas.core.dominio.usuarios.Cliente;
import es.jma.reservas.core.dominio.usuarios.Empleado;

/**
 * @author tulon
 *
 */
public interface ICitaDAO extends ICrudDAO {

	/**
	 * Consultar todas las citas de un empleado
	 * @param empleado
	 * @return
	 */
	public List<Cita> consultarCitasEmpleado(Empleado empleado);
	
	/**
	 * Consultar las citas de un empleado de un determinado día
	 * @param empleado
	 * @param dia
	 * @param servicio
	 * @return
	 */
	public List<Cita> consultarCitasEmpleado(Empleado empleado, Date dia);
	
	/**
	 * Consultar todas las citas de un cliente
	 * @param cliente
	 * @return
	 */
	public List<Cita> consultarCitasCliente(Cliente cliente);
	
}
