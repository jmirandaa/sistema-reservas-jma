/**
 * 
 */
package es.jma.reservas.core.servicios;

import java.util.Date;
import java.util.List;

import es.jma.reservas.core.daos.ICitaDAO;
import es.jma.reservas.core.dominio.citas.Franja;
import es.jma.reservas.core.dominio.citas.Slot;
import es.jma.reservas.core.dominio.servicios.Servicio;
import es.jma.reservas.core.dominio.usuarios.Empleado;

/**
 * @author tulon
 *
 */
public interface IServiciosCita extends ICitaDAO {

	/**
	 * Devolver franjas libres de un empleado para un servicio y slot de un día
	 * @param empleado
	 * @param dia
	 * @param servicio
	 * @param slot
	 * @return
	 */
	
	//Comprobar citas empleado dia
	
	public List<Franja> consultasFranjasLibresDia(Empleado empleado, Date dia, Servicio servicio, Slot slot) throws Exception;
}
