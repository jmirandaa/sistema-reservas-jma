/**
 * 
 */
package es.jma.reservas.core.daos;

import java.util.List;

import es.jma.reservas.core.dominio.usuarios.Usuario;

/**
 * Definición de DAO para Usuario
 * @author Jorge Miranda
 *
 */

public interface IUsuarioDAO extends IHibernateDAO {
	/**
	 * Añadir un usuario
	 * @param usuario
	 * @throws Exception
	 */
	public void nuevo (Usuario usuario) throws Exception;
	
	/**
	 * Actualizar un usuario
	 * @param usuario
	 * @throws Exception
	 */
	public void actualizar (Usuario usuario) throws Exception;
	
	/**
	 * Borrar un usuario
	 * @param usuario
	 * @throws Exception
	 */
	public void borrar (Usuario usuario) throws Exception;
	
	/**
	 * Consulta de todos los usuarios
	 * @return
	 * @throws Exception
	 */
	public List<Usuario> consultarTodosUsuarios() throws Exception;
	
	/**
	 * Consultar un usuario por su nombre de usuario
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public Usuario consultarUsuarioNombreUsuario(String nombreUsuario) throws Exception;
}
