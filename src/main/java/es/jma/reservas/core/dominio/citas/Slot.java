/**
 * 
 */
package es.jma.reservas.core.dominio.citas;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author tulon
 *
 */
@Entity
@Table(name = "CORE_SLOTS")
public class Slot implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4154998456159571729L;
	//Atributos
	@Id
	@GeneratedValue
	private long id;
	private String descripcion;
	private int capacidadMax;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="slot")
	private List <Cita> citas;
	
	//Constructores
	public Slot()
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the capacidadMax
	 */
	public int getCapacidadMax() {
		return capacidadMax;
	}

	/**
	 * @param capacidadMax the capacidadMax to set
	 */
	public void setCapacidadMax(int capacidadMax) {
		this.capacidadMax = capacidadMax;
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
	
	
}
