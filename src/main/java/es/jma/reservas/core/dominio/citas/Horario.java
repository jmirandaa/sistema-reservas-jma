/**
 * 
 */
package es.jma.reservas.core.dominio.citas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tulon
 *
 */
@Entity
@Table(name = "CORE_HORARIO")
public class Horario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8532090381747134627L;
	//Atributos
	@Id
	@GeneratedValue
	private long id;
	private Date horaInicio;
	private Date horaFin;
	private String descripcion;
	private int diaSemana;
	
	//Constructores
	public Horario()
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
	 * @return the horaInicio
	 */
	public Date getHoraInicio() {
		return horaInicio;
	}

	/**
	 * @param horaInicio the horaInicio to set
	 */
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * @return the horaFin
	 */
	public Date getHoraFin() {
		return horaFin;
	}

	/**
	 * @param horaFin the horaFin to set
	 */
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
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
	 * @return the diaSemana
	 */
	public int getDiaSemana() {
		return diaSemana;
	}

	/**
	 * @param diaSemana the diaSemana to set
	 */
	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	

}
