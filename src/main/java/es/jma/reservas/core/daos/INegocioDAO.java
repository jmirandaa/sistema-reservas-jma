package es.jma.reservas.core.daos;


import es.jma.reservas.core.dominio.negocio.Negocio;

/**
 * Definición de operaciones particulares a Negocio
 * @author Jorge Miranda
 *
 */
public interface INegocioDAO extends ICrudDAO{

	/**
	 * Consultar negocios por id
	 * @param id
	 * @return
	 */
	public Negocio consultarNegocioId(int id);
}
