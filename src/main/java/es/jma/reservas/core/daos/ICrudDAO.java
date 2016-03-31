/**
 * 
 */
package es.jma.reservas.core.daos;

import java.util.List;


/**
 * Definición de operaciones genéricas para todos los objetos
 * @author Jorge Miranda
 *
 */
public interface ICrudDAO extends IHibernateDAO{
	/**
	 * Añadir un objeto
	 * @param T
	 * @throws Exception
	 */
	public void nuevo (Object objeto) throws Exception;
	
	/**
	 * Actualizar un objeto
	 * @param T
	 * @throws Exception
	 */
	public void actualizar (Object objeto) throws Exception;
	
	/**
	 * Borrar un objeto
	 * @param T
	 * @throws Exception
	 */
	public void borrar (Object objeto) throws Exception;
	
	/**
	 * Consulta de todos los objetos
	 * @return List<T>
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List consultarTodos() throws Exception;
}
