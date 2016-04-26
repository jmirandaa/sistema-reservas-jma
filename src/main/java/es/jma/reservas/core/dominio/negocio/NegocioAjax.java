/**
 * 
 */
package es.jma.reservas.core.dominio.negocio;

import java.io.Serializable;
import java.util.List;

import es.jma.reservas.core.dominio.usuarios.Empleado;

/**
 * @author tulon
 *
 */
public class NegocioAjax implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8035033193123577234L;
	private String nombre;
	private String direccion;
	private String telefono;
	private String url;
	private String tipo;
	private boolean slotsFijos;
	private List <Empleado> personas;
	
	public NegocioAjax()
	{
		
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the slotsFijos
	 */
	public boolean isSlotsFijos() {
		return slotsFijos;
	}

	/**
	 * @param slotsFijos the slotsFijos to set
	 */
	public void setSlotsFijos(boolean slotsFijos) {
		this.slotsFijos = slotsFijos;
	}

	/**
	 * @return the personas
	 */
	public List<Empleado> getPersonas() {
		return personas;
	}

	/**
	 * @param personas the personas to set
	 */
	public void setPersonas(List<Empleado> personas) {
		this.personas = personas;
	}
	
	
}
