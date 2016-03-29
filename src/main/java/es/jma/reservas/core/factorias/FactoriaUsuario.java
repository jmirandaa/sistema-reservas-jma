/**
 * 
 */
package es.jma.reservas.core.factorias;

import es.jma.reservas.core.dominio.usuarios.Usuario;

/**
 * @author tulon
 *
 */
public class FactoriaUsuario extends Usuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1770666041949582954L;

	/**
	 * Constructor privado para no permitir instanciar
	 */
	private FactoriaUsuario()
	{
		
	}
	
	/**
	 * Crear administrador
	 * @return
	 */
	public static Usuario crearAdmin() {
		Usuario usuario = new FactoriaUsuario();
		usuario.setNivel(Usuario.NIVEL_ADMIN);		
		return usuario;
	}
	
	/**
	 * Crear empleado
	 * @return
	 */
	public static Usuario crearEmpleado() {
		Usuario usuario = new FactoriaUsuario();
		usuario.setNivel(Usuario.NIVEL_EMPLEADO);		
		return usuario;
	}
	
	/**
	 * Crear cliente
	 * @return
	 */
	public static Usuario crearCliente() {
		Usuario usuario = new FactoriaUsuario();
		usuario.setNivel(Usuario.NIVEL_CLIENTE);		
		return usuario;
	}

}
