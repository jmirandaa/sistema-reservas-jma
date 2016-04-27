/**
 * 
 */
package es.jma.reservas.core.controladores;

/**
 * Constantes para los jsp
 * @author Jorge Miranda
 *
 */
public final class KPaginas {
	public static final String INICIO = "index";
	public static final String PRIMERA_CARGA = "primeraCarga";
	public static final String PANEL_PRINCIPAL = "principal";
	public static final String PANEL_EMPLEADOS = "empleados";
	public static final String PANEL_CLIENTES = "clientes";
	public static final String DATOS_LOGIN_INCORRECTOS = "index";
	
	//Constantes de mensajes
	final class Mensajes
	{
		public static final String KEY_ERROR = "msgError";
		public static final String MSG_LOGIN_INCORRECTO = "Datos incorrectos";
	}
	
	/**
	 * Evitar instanciar
	 */
	private KPaginas()
	{
		
	}
}
