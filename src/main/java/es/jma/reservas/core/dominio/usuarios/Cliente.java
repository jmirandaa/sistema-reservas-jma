/**
 * 
 */
package es.jma.reservas.core.dominio.usuarios;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import es.jma.reservas.core.dominio.citas.Cita;

/**
 * @author tulon
 *
 */
@Entity
public class Cliente extends AbstractPersona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2305632930417270601L;
	//Atributos
	private String direccion;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="cliente")
	private List <Cita> citas;

	//Constructores
	public Cliente()
	{
		super();
	}
	
	public Cliente(Usuario usuario)
	{
		super();
		this.usuario = usuario;
	}
	
	/**
	 * @return the citas
	 */
	public List<Cita> getCitas() {
		return citas;
	}

	/**
	 * @param citas the citas to set
	 */
	public void setCitas(List<Cita> citas) {
		this.citas = citas;
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
	
	
}
