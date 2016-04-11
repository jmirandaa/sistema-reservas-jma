/**
 * 
 */
package es.jma.reservas.core.daos;

import java.util.List;

import es.jma.reservas.core.dominio.citas.Horario;
import es.jma.reservas.core.dominio.usuarios.Empleado;

/**
 * @author tulon
 *
 */
public interface IHorarioDAO extends ICrudDAO {
	
	/**
	 * Añadir un horario a un empleado
	 * @param horario
	 * @param empleado
	 */
	public void guardarHorarioEmpleado(Horario horario, Empleado empleado) throws Exception;
	
	/**
	 * Consultar el horario de un empleado
	 * @param empleado
	 * @return
	 */
	public List<Horario> consultarHorarioEmpleado(Empleado empleado) throws Exception;
}
