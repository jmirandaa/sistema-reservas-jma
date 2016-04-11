/**
 * 
 */
package es.jma.reservas.core.dominio.usuarios;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import es.jma.reservas.core.dominio.citas.Cita;
import es.jma.reservas.core.dominio.citas.Horario;
import es.jma.reservas.core.dominio.servicios.Servicio;

/**
 * @author tulon
 *
 */
@Entity
public class Empleado extends AbstractPersona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6497607736335993851L;
	//Atributos
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Servicio> servicios;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="empleado")
	private List <Cita> citas;
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List <Horario> horario;
	
	//Constructores
	public Empleado()
	{
		super();
	}
	
	public Empleado(Usuario usuario)
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
	 * @return the servicios
	 */
	public List<Servicio> getServicios() {
		return servicios;
	}

	/**
	 * @param servicios the servicios to set
	 */
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	/**
	 * @return the horario
	 */
	public List<Horario> getHorario() {
		return horario;
	}

	/**
	 * @param horario the horario to set
	 */
	public void setHorario(List<Horario> horario) {
		this.horario = horario;
	}
	
	
}
