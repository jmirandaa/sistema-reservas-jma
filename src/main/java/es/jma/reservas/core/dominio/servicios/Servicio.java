/**
 * 
 */
package es.jma.reservas.core.dominio.servicios;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.jma.reservas.core.dominio.citas.Cita;

/**
 * @author tulon
 *
 */
@Entity
@Table(name = "CORE_SERVICIOS")
public class Servicio implements Serializable{
	private static final long serialVersionUID = -8128693233209881845L;
	//Atributos
	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	private int duracionMin;
	private double coste;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="servicio")
	private List<Cita> citas;
	
	//Constructores
	public Servicio()
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
	 * @return the duracionMin
	 */
	public int getDuracionMin() {
		return duracionMin;
	}

	/**
	 * @param duracionMin the duracionMin to set
	 */
	public void setDuracionMin(int duracionMin) {
		this.duracionMin = duracionMin;
	}

	/**
	 * @return the coste
	 */
	public double getCoste() {
		return coste;
	}

	/**
	 * @param coste the coste to set
	 */
	public void setCoste(double coste) {
		this.coste = coste;
	}
	
	
}
