/**
 * 
 */
package es.jma.reservas.core.daos;

import java.util.List;

import es.jma.reservas.core.dominio.negocio.Negocio;
import es.jma.reservas.core.dominio.usuarios.Cliente;

/**
 * @author jmiranda
 *
 */
public interface IClienteDAO extends ICrudDAO{
	/**
	 * Consultar los clientes de un negocio
	 * @param negocio
	 * @return
	 */
	public List<Cliente> consultarClientesNegocio(Negocio negocio) throws Exception;
	
	/**
	 * Guardar un cliente en un negocio
	 * @param negocio
	 * @throws Exception
	 */
	public void guardarClienteNegocio (Cliente cliente, Negocio negocio) throws Exception;
}
