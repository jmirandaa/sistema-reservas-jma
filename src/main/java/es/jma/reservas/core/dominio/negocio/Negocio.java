/**
 * 
 */
package es.jma.reservas.core.dominio.negocio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.jma.reservas.core.dominio.citas.Slot;
import es.jma.reservas.core.dominio.usuarios.AbstractPersona;

/**
 * @author tulon
 *
 */
@Entity
@Table(name = "CONF_NEGOCIOS")
public class Negocio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 934169647325412264L;
	//Atributos
	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	private String direccion;
	private String telefono;
	private String url;
	private String tipo;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List <AbstractPersona> personas;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List <Slot> slots;
	private boolean slotsFijos;
	
	//Constructores
	public Negocio()
	{
		
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the personas
	 */
	public List<AbstractPersona> getPersonas() {
		return personas;
	}

	/**
	 * @param personas the personas to set
	 */
	public void setPersonas(List<AbstractPersona> personas) {
		this.personas = personas;
	}

	/**
	 * @return the slots
	 */
	public List<Slot> getSlots() {
		return slots;
	}

	/**
	 * @param slots the slots to set
	 */
	public void setSlots(List<Slot> slots) {
		this.slots = slots;
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
	
	

}
