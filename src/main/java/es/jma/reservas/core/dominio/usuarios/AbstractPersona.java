/**
 * 
 */
package es.jma.reservas.core.dominio.usuarios;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author tulon
 *
 */
@Entity
@Table(name = "CORE_PERSONAS")
public abstract class AbstractPersona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6532485821127293346L;
	//Atributos
	@Id
	@GeneratedValue
	protected long id;
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	protected Usuario usuario;
	protected String nombre;
	protected String apellidos;
	protected String dni;
	protected Date fechaNacimiento;	
	
	//Constructores
	protected AbstractPersona()
	{
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	
	
}
