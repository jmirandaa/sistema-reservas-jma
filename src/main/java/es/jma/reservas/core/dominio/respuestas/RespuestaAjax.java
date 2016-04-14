/**
 * 
 */
package es.jma.reservas.core.dominio.respuestas;

import java.io.Serializable;

/**
 * Clase para encapsular las respuestas AJAX
 * @author Jorge Miranda
 *
 */
public class RespuestaAjax implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6366111784312147692L;
	//Atributos
	private int codRespuesta;
	private String respuesta;

	//Constructores
	public RespuestaAjax()
	{
		
	}

	/**
	 * @return the codRespuesta
	 */
	public int getCodRespuesta() {
		return codRespuesta;
	}

	/**
	 * @param codRespuesta the codRespuesta to set
	 */
	public void setCodRespuesta(int codRespuesta) {
		this.codRespuesta = codRespuesta;
	}

	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
}
