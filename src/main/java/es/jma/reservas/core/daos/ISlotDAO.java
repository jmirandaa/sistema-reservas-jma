/**
 * 
 */
package es.jma.reservas.core.daos;

import java.util.List;

import es.jma.reservas.core.dominio.citas.Slot;
import es.jma.reservas.core.dominio.negocio.Negocio;

/**
 * @author jmiranda
 *
 */
public interface ISlotDAO extends ICrudDAO {
	
	/**
	 * Añadir nuevo slot a un negocio
	 * @param slot
	 * @param negocio
	 */
	public void nuevoSlotNegocio (Slot slot, Negocio negocio) throws Exception;
	
	/**
	 * Consultar slots de un negocio
	 * @param negocio
	 * @return
	 */
	public List<Slot> consultarSlotsNegocio(Negocio negocio) throws Exception;
}
