/**
 * 
 */
package es.jma.reservas.core.dominio.usuarios;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tulon
 *
 */
@Entity
@Table(name = "CORE_USUARIOS")
public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2616763098034434580L;
	//Constantes asociadas
	public static final int NIVEL_ADMIN = 0;
	public static final int NIVEL_EMPLEADO = 1;
	public static final int NIVEL_CLIENTE = 2;
	
	//Atributos
	@Id
	@GeneratedValue
	private long id;
	private String nombreUsuario;
	private String password;
	private int nivel;
	
	//Constructores
	protected Usuario()
	{
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	
}
