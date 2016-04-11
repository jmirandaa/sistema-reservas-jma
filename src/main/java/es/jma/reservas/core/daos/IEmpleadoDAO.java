/**
 * 
 */
package es.jma.reservas.core.daos;

import java.util.List;

import es.jma.reservas.core.dominio.negocio.Negocio;
import es.jma.reservas.core.dominio.usuarios.Empleado;

/**
 * @author jmiranda
 *
 */
public interface IEmpleadoDAO extends ICrudDAO {
	/**
	 * Consultar los empleados de un negocio
	 * @param negocio
	 * @return
	 */
	public List<Empleado> consultarEmpleadosNegocio(Negocio negocio) throws Exception;
	
	/**
	 * Guardar un empleado en un negocio
	 * @param negocio
	 * @throws Exception
	 */
	public void guardarEmpleadoNegocio (Empleado empleado, Negocio negocio) throws Exception;
}
